package com.kz.dagdy.ui.qosu.adet_qosu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kz.dagdy.R
import com.kz.dagdy.data.models.adet.type.AdetType
import com.kz.dagdy.ui_common.callbacks.RecyclerViewItemClickCallback
import javax.inject.Inject

class AdetQosuViewModel
@Inject
constructor(
    private val app: Application
) : AndroidViewModel(app) {
    private val _sportList = MutableLiveData<List<AdetType>>()
    val sportList: LiveData<List<AdetType>>
        get() = _sportList

    private fun initSportList() {
        val temp = mutableListOf<AdetType>()

        temp.add(
            AdetType(
                id = "1",
                image = R.drawable.ic_juru,
                title = app.getString(R.string.juru)
            )
        )
        temp.add(
            AdetType(
                id = "2",
                image = R.drawable.ic_jugiru,
                title = app.getString(R.string.jugiru)
            )
        )
        temp.add(
            AdetType(
                id = "3",
                image = R.drawable.ic_velosiped,
                title = app.getString(R.string.velosiped)
            )
        )
        temp.add(
            AdetType(
                id = "4",
                image = R.drawable.ic_ioga,
                title = app.getString(R.string.ioga)
            )
        )
        temp.add(
            AdetType(
                id = "5",
                image = R.drawable.ic_football,
                title = app.getString(R.string.futbol)
            )
        )
        temp.add(
            AdetType(
                id = "6",
                image = R.drawable.ic_swim,
                title = app.getString(R.string.juzu)
            )
        )
        _sportList.value = temp
    }

    init {
        initSportList()
    }

    private val _lifeBastauList = MutableLiveData<List<AdetType>>()
    val lifeBastauList: LiveData<List<AdetType>>
        get() = _lifeBastauList

    private fun initLifeBastauList() {
        val temp = mutableListOf<AdetType>()

        temp.add(
            AdetType(
                id = "7",
                image = R.drawable.ic_call_family,
                title = app.getString(R.string.call_with_family)
            )
        )
        temp.add(
            AdetType(
                id = "8",
                image = R.drawable.ic_save_money,
                title = app.getString(R.string.save_money)
            )
        )
        temp.add(
            AdetType(
                id = "9",
                image = R.drawable.ic_call_friend,
                title = app.getString(R.string.call_with_friends)
            )
        )
        temp.add(
            AdetType(
                id = "10",
                image = R.drawable.ic_listen_podcats,
                title = app.getString(R.string.listen_podcast)
            )
        )
        _lifeBastauList.value = temp
    }

    init {
        initLifeBastauList()
    }

    private val _lifeTastauList = MutableLiveData<List<AdetType>>()
    val lifeTastauList: LiveData<List<AdetType>>
        get() = _lifeTastauList

    private fun initLifeTastauList() {
        val temp = mutableListOf<AdetType>()

        temp.add(
            AdetType(
                id = "11",
                image = R.drawable.ic_temeki,
                title = app.getString(R.string.temeki_tastau)
            )
        )
        temp.add(
            AdetType(
                id = "11",
                image = R.drawable.ic_alcagol,
                title = app.getString(R.string.alkagol)
            )
        )
        temp.add(
            AdetType(
                id = "12",
                image = R.drawable.ic_qant,
                title = app.getString(R.string.qant)
            )
        )
        temp.add(
            AdetType(
                id = "13",
                image = R.drawable.ic_uiyktau,
                title = app.getString(R.string.kop_uiqtamau)
            )
        )
        temp.add(
            AdetType(
                id = "14",
                image = R.drawable.ic_ashulanbau,
                title = app.getString(R.string.ashulanbau)
            )
        )
        temp.add(
            AdetType(
                id = "15",
                image = R.drawable.ic_doreki,
                title = app.getString(R.string.doreki_soilemeu)
            )
        )
        temp.add(
            AdetType(
                id = "16",
                image = R.drawable.ic_youtube,
                title = app.getString(R.string.youtube_kormeu)
            )
        )
        temp.add(
            AdetType(
                id = "17",
                image = R.drawable.ic_galamtor,
                title = app.getString(R.string.galamtorda_otyrmau)
            )
        )
        temp.add(
            AdetType(
                id = "18",
                image = R.drawable.ic_aksha,
                title = app.getString(R.string.aqsha_joiu)
            )
        )
        temp.add(
            AdetType(
                id = "19",
                image = R.drawable.ic_azart,
                title = app.getString(R.string.azart_oiyan)
            )
        )
        _lifeTastauList.value = temp
    }

    init {
        initLifeTastauList()
    }

    val recyclerViewItemClickCallback = object :
        RecyclerViewItemClickCallback {
        override fun onRecyclerViewItemClick(any: Any) {
            when (any) {

            }
        }
    }

    private var issueType: Int = 1

    fun onTabSelected(index: Int) {
        issueType = index + 1
    }

}