package com.kz.dagdy.ui.custom_view.piechart.customView

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Typeface
import android.os.Build
import android.os.Parcelable
import android.text.Layout
import android.text.StaticLayout
import android.text.TextDirectionHeuristic
import android.text.TextDirectionHeuristics
import android.text.TextPaint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.annotation.RequiresApi
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.kz.dagdy.R
import com.kz.dagdy.ui.custom_view.piechart.extension.dpToPx
import com.kz.dagdy.ui.custom_view.piechart.extension.spToPx
import com.kz.dagdy.ui.custom_view.piechart.model.PieChartModel
import com.kz.dagdy.ui.custom_view.piechart.model.PieChartState

class PieChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), PieChartInterface {
    companion object {
        private const val DEFAULT_MARGIN_TEXT_1 = 2
        private const val DEFAULT_MARGIN_TEXT_2 = 10
        private const val DEFAULT_MARGIN_TEXT_3 = 2
        private const val DEFAULT_MARGIN_SMALL_CIRCLE = 12
        private const val ANALYTICAL_PIE_CHART_KEY = "PieChartArrayData"
        private const val TEXT_WIDTH_PERCENT = 0.40
        private const val CIRCLE_WIDTH_PERCENT = 0.60
        const val DEFAULT_VIEW_SIZE_HEIGHT = 303
        const val DEFAULT_VIEW_SIZE_WIDTH = 250
    }

    private var marginTextFirst: Float = context.dpToPx(DEFAULT_MARGIN_TEXT_1)
    private var marginTextSecond: Float = context.dpToPx(DEFAULT_MARGIN_TEXT_2)
    private var marginTextThird: Float = context.dpToPx(DEFAULT_MARGIN_TEXT_3)
    private var marginSmallCircle: Float = context.dpToPx(DEFAULT_MARGIN_SMALL_CIRCLE)
    private val marginText: Float = marginTextFirst + marginTextSecond
    private val circleRect = RectF()
    private var circleStrokeWidth: Float = context.dpToPx(6)
    private var circleRadius: Float = 0F
    private var circlePadding: Float = context.dpToPx(70)
    private var circlePaintRoundSize: Boolean = true
    private var circleSectionSpace: Float = 3F
    private var circleCenterX: Float = 0F
    private var circleCenterY: Float = 0F
    private var numberTextPaint: TextPaint = TextPaint()
    private var descriptionTextPain: TextPaint = TextPaint()
    private var descriptionTextPainAll: TextPaint = TextPaint()
    private var descriptionTextPainIncome: TextPaint = TextPaint()
    private var descriptionTextPainExpense: TextPaint = TextPaint()
    private var amountTextPaint: TextPaint = TextPaint()
    private var textStartX: Float = 0F
    private var textStartY: Float = 0F
    private var textHeight: Int = 0
    private var textCircleRadius: Float = context.dpToPx(4)
    private var textAmountStr: String = ""
    private var textAmountY: Float = 0F
    private var textAmountXNumber: Float = 0F
    private var textAmountXDescription: Float = 0F
    private var textAmountYDescription: Float = 0F
    private var totalAmount: Int = 0
    var pieChartColors: List<String> = listOf()
    private var percentageCircleList: List<PieChartModel> = listOf()
    private var textRowList: MutableList<StaticLayout> = mutableListOf()
    private var dataList: List<Pair<Int, String>> = listOf()
    private var animationSweepAngle: Int = 0
    var percentIncome = 0
    var percentExpense = 0
    var directionChart = true
    var nameTypeChart = ""
    var amountChart = ""

    init {
        var textAmountSize: Float = context.spToPx(22)
        var textNumberSize: Float = context.spToPx(20)
        var textDescriptionSize: Float = context.spToPx(14)
        var textAmountColor: Int = Color.WHITE
        var textNumberColor: Int = Color.WHITE
        var textDescriptionColor: Int = Color.GRAY

        if (attrs != null) {
            val typeArray = context.obtainStyledAttributes(attrs, R.styleable.AnalyticalPieChart)

            marginTextFirst = typeArray.getDimension(
                R.styleable.AnalyticalPieChart_pieChartMarginTextFirst,
                marginTextFirst
            )
            marginTextSecond = typeArray.getDimension(
                R.styleable.AnalyticalPieChart_pieChartMarginTextSecond,
                marginTextSecond
            )
            marginTextThird = typeArray.getDimension(
                R.styleable.AnalyticalPieChart_pieChartMarginTextThird,
                marginTextThird
            )
            marginSmallCircle = typeArray.getDimension(
                R.styleable.AnalyticalPieChart_pieChartMarginSmallCircle,
                marginSmallCircle
            )

            circleStrokeWidth = typeArray.getDimension(
                R.styleable.AnalyticalPieChart_pieChartCircleStrokeWidth,
                circleStrokeWidth
            )
            circlePadding = typeArray.getDimension(
                R.styleable.AnalyticalPieChart_pieChartCirclePadding,
                circlePadding
            )
            circlePaintRoundSize = typeArray.getBoolean(
                R.styleable.AnalyticalPieChart_pieChartCirclePaintRoundSize,
                circlePaintRoundSize
            )
            circleSectionSpace = typeArray.getFloat(
                R.styleable.AnalyticalPieChart_pieChartCircleSectionSpace,
                circleSectionSpace
            )


            textCircleRadius = typeArray.getDimension(
                R.styleable.AnalyticalPieChart_pieChartTextCircleRadius,
                textCircleRadius
            )
            textAmountSize = typeArray.getDimension(
                R.styleable.AnalyticalPieChart_pieChartTextAmountSize,
                textAmountSize
            )
            textNumberSize = typeArray.getDimension(
                R.styleable.AnalyticalPieChart_pieChartTextNumberSize,
                textNumberSize
            )
            textDescriptionSize = typeArray.getDimension(
                R.styleable.AnalyticalPieChart_pieChartTextDescriptionSize,
                textDescriptionSize
            )
            textAmountColor = typeArray.getColor(
                R.styleable.AnalyticalPieChart_pieChartTextAmountColor,
                textAmountColor
            )
            textNumberColor = typeArray.getColor(
                R.styleable.AnalyticalPieChart_pieChartTextNumberColor,
                textNumberColor
            )
            textDescriptionColor = typeArray.getColor(
                R.styleable.AnalyticalPieChart_pieChartTextDescriptionColor,
                textDescriptionColor
            )
            textAmountStr =
                typeArray.getString(R.styleable.AnalyticalPieChart_pieChartTextAmount) ?: ""

            typeArray.recycle()
        }

        circlePadding += circleStrokeWidth

        initPains(amountTextPaint, textAmountSize, textAmountColor)
        initPains(numberTextPaint, textNumberSize, textNumberColor)
        initPains(descriptionTextPain, textDescriptionSize, textDescriptionColor, true)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        textRowList.clear()

        val initSizeWidth = resolveDefaultSize(widthMeasureSpec, DEFAULT_VIEW_SIZE_WIDTH)

        val textTextWidth = (initSizeWidth * TEXT_WIDTH_PERCENT)
        val initSizeHeight = calculateViewHeight(heightMeasureSpec, textTextWidth.toInt())

        textStartX = initSizeWidth - textTextWidth.toFloat()
        textStartY = initSizeHeight.toFloat() / 2 - textHeight / 2

        calculateCircleRadius(initSizeWidth, initSizeHeight)

        setMeasuredDimension(initSizeWidth, initSizeHeight)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawCircle(canvas)
        drawText(canvas)
    }

    private fun drawText(canvas: Canvas) {
        if (!directionChart) {
            drawAllAmount(canvas)
        } else {
            drawIncome(canvas)
            drawExpense(canvas)
        }
    }

    private fun drawAllAmount(canvas: Canvas) {
        val textAll = nameTypeChart
        val sizeTextAll = getWidthOfAmountText(
            textAll.toString(),
            numberTextPaint
        )
        val textAllX = circleCenterX - sizeTextAll.width() / 2
        val textIncomeX = textAllX
        val textIncomeY = circleCenterY - textCircleRadius - marginTextFirst - 15

        descriptionTextPainAll.color = Color.parseColor("#979797")
        descriptionTextPainAll.textSize = context.spToPx(14)

        canvas.drawText(textAll, textIncomeX, textIncomeY, descriptionTextPainAll)

        val textPercent = amountChart
        val sizeTextAmount = getWidthOfAmountText(
            textPercent,
            numberTextPaint
        )

        val textAmountX = circleCenterX - sizeTextAmount.width() / 2
        val textPercentX = textAmountX - sizeTextAmount.width() / 2
        val textPercentY = circleCenterY - textCircleRadius + 80

        descriptionTextPainAll.color = Color.parseColor("#000000")
        descriptionTextPainAll.textSize = context.spToPx(30)
        descriptionTextPainAll.typeface = Typeface.DEFAULT_BOLD
        descriptionTextPainExpense.textAlign = Paint.Align.CENTER
        canvas.drawText(textPercent, textPercentX, textPercentY, descriptionTextPainAll)
    }

    private fun drawIncome(canvas: Canvas) {
        val textAll = context.getString(R.string.kiris) + ":"
        val textIncomeX = circleCenterX + circleRadius - 290
        val textIncomeY = circleCenterY - textCircleRadius - marginTextFirst - 10

        descriptionTextPainIncome.color = Color.parseColor("#979797")
        descriptionTextPainIncome.textSize = context.spToPx(14)

        canvas.drawText(textAll, textIncomeX, textIncomeY, descriptionTextPainIncome)

        val textPercent = "$percentIncome%"
        val textPercentX = circleCenterX + circleRadius - 290
        val textPercentY = circleCenterY - textCircleRadius + 80

        descriptionTextPainIncome.color = Color.parseColor("#000000")
        descriptionTextPainIncome.textSize = context.spToPx(30)
        descriptionTextPainIncome.typeface = Typeface.DEFAULT_BOLD

        canvas.drawText(textPercent, textPercentX, textPercentY, descriptionTextPainIncome)
    }

    private fun drawExpense(canvas: Canvas) {
        val textExpense = context.getString(R.string.shygys) + ":"
        val textExpenseX = circleCenterX - circleRadius + 290 // Изменил знак тут
        val textExpenseY = circleCenterY - textCircleRadius - marginTextFirst - 10

        descriptionTextPainExpense.color = Color.parseColor("#979797")
        descriptionTextPainExpense.textSize = context.spToPx(14)
        descriptionTextPainExpense.textAlign = Paint.Align.RIGHT

        canvas.drawText(textExpense, textExpenseX, textExpenseY, descriptionTextPainExpense)

        val textPercent = "$percentExpense%"
        val textPercentX = circleCenterX - circleRadius + 290
        val textPercentY = circleCenterY - textCircleRadius + 80

        descriptionTextPainExpense.color = Color.parseColor("#000000")
        descriptionTextPainExpense.textSize = context.spToPx(30)
        descriptionTextPainExpense.typeface = Typeface.DEFAULT_BOLD
        descriptionTextPainExpense.textAlign = Paint.Align.RIGHT

        canvas.drawText(textPercent, textPercentX, textPercentY, descriptionTextPainExpense)

    }

    fun Context.spToPx(sp: Float): Float {
        val metrics = resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, metrics)
    }


    override fun onRestoreInstanceState(state: Parcelable?) {
        val analyticalPieChartState = state as? PieChartState
        super.onRestoreInstanceState(analyticalPieChartState?.superState ?: state)

        dataList = analyticalPieChartState?.dataList ?: listOf()
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        return PieChartState(superState, dataList)
    }

    override fun setDataChart(list: List<Pair<Int, String>>) {
        dataList = list
        calculatePercentageOfData()
    }

    override fun startAnimation() {
        // Проход значений от 0 до 360 (целый круг), с длительностью - 1.5 секунды
        val animator = ValueAnimator.ofInt(0, 360).apply {
            duration = 1500
            interpolator = FastOutSlowInInterpolator()
            addUpdateListener { valueAnimator ->
                animationSweepAngle = valueAnimator.animatedValue as Int
                invalidate()
            }
        }
        animator.start()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun drawCircle(canvas: Canvas) {
        for (percent in percentageCircleList) {
            if (animationSweepAngle > percent.percentToStartAt + percent.percentOfCircle) {
                canvas.drawArc(
                    circleRect,
                    percent.percentToStartAt,
                    percent.percentOfCircle,
                    false,
                    percent.paint
                )
            } else if (animationSweepAngle > percent.percentToStartAt) {
                canvas.drawArc(
                    circleRect,
                    percent.percentToStartAt,
                    animationSweepAngle - percent.percentToStartAt,
                    false,
                    percent.paint
                )
            }
        }

        val secondCirclePaint = Paint().apply {
            color = Color.TRANSPARENT // Прозрачная внутренность
            style = Paint.Style.STROKE // Только граница
            color = context.getColor(R.color.colorTitleDayXiaomi) // Черная граница
            strokeWidth = 12f // Толщина границы
        }
        canvas.drawCircle(circleCenterX, circleCenterY, circleRadius - 30f, secondCirclePaint)
    }

    private fun initPains(
        textPaint: TextPaint,
        textSize: Float,
        textColor: Int,
        isDescription: Boolean = false
    ) {
        textPaint.color = textColor
        textPaint.textSize = textSize
        textPaint.isAntiAlias = true

        if (!isDescription) textPaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }

    private fun resolveDefaultSize(spec: Int, defValue: Int): Int {
        return when (MeasureSpec.getMode(spec)) {
            MeasureSpec.UNSPECIFIED -> context.dpToPx(defValue).toInt()
            else -> MeasureSpec.getSize(spec)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun calculateViewHeight(heightMeasureSpec: Int, textWidth: Int): Int {
        val initSizeHeight = resolveDefaultSize(heightMeasureSpec, DEFAULT_VIEW_SIZE_HEIGHT)
        textHeight = (dataList.size * marginText + getTextViewHeight(textWidth)).toInt()
        textHeight = (dataList.size * marginText + getTextViewHeight(textWidth)).toInt()

        val textHeightWithPadding = textHeight + paddingTop + paddingBottom
        return if (textHeightWithPadding > initSizeHeight) textHeightWithPadding else initSizeHeight
    }

    private fun calculateCircleRadius(width: Int, height: Int) {
        val circleViewWidth = (width * CIRCLE_WIDTH_PERCENT)
        circleRadius = if (circleViewWidth > height) {
            (height.toFloat() - circlePadding) / 2
        } else {
            circleViewWidth.toFloat() / 2
        }

        with(circleRect) {
            left = circlePadding
            top = height / 2 - circleRadius
            right = circleRadius * 2 + circlePadding
            bottom = height / 2 + circleRadius
        }

        circleCenterX = (circleRadius * 2 + circlePadding + circlePadding) / 2
        circleCenterY = (height / 2 + circleRadius + (height / 2 - circleRadius)) / 2

        textAmountY = circleCenterY

        val sizeTextAmountNumber = getWidthOfAmountText(
            totalAmount.toString(),
            amountTextPaint
        )

        textAmountXNumber = circleCenterX - sizeTextAmountNumber.width() / 2
        textAmountXDescription =
            circleCenterX - getWidthOfAmountText(textAmountStr, descriptionTextPain).width() / 2
        textAmountYDescription = circleCenterY + sizeTextAmountNumber.height() + marginTextThird
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getTextViewHeight(maxWidth: Int): Int {
        var textHeight = 0
        dataList.forEach {
            val textLayoutNumber = getMultilineText(
                text = it.first.toString(),
                textPaint = numberTextPaint,
                width = maxWidth
            )
            val textLayoutDescription = getMultilineText(
                text = it.second,
                textPaint = descriptionTextPain,
                width = maxWidth
            )
            textRowList.apply {
                add(textLayoutNumber)
                add(textLayoutDescription)
            }
            textHeight += textLayoutNumber.height + textLayoutDescription.height
        }

        return textHeight
    }

    private fun calculatePercentageOfData() {
        totalAmount = dataList.fold(0) { res, value -> res + value.first }

        var startAt = circleSectionSpace
        percentageCircleList = dataList.mapIndexed { index, pair ->
            var percent = pair.first * 100 / totalAmount.toFloat() - circleSectionSpace
            percent = if (percent < 0F) 0F else percent

            val resultModel = PieChartModel(
                percentOfCircle = percent,
                percentToStartAt = startAt,
                colorOfLine = Color.parseColor(pieChartColors[index % pieChartColors.size]),
                stroke = circleStrokeWidth,
                paintRound = circlePaintRoundSize
            )
            if (percent != 0F) startAt += percent + circleSectionSpace
            resultModel
        }
    }

    private fun getWidthOfAmountText(text: String, textPaint: TextPaint): Rect {
        val bounds = Rect()
        textPaint.getTextBounds(text, 0, text.length, bounds)
        return bounds
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getMultilineText(
        text: CharSequence,
        textPaint: TextPaint,
        width: Int,
        start: Int = 0,
        end: Int = text.length,
        alignment: Layout.Alignment = Layout.Alignment.ALIGN_NORMAL,
        textDir: TextDirectionHeuristic = TextDirectionHeuristics.LTR,
        spacingMult: Float = 1f,
        spacingAdd: Float = 0f
    ): StaticLayout {

        return StaticLayout.Builder
            .obtain(text, start, end, textPaint, width)
            .setAlignment(alignment)
            .setTextDirection(textDir)
            .setLineSpacing(spacingAdd, spacingMult)
            .build()
    }
}