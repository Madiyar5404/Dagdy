package com.kz.dagdy.data.repository.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import com.kz.dagdy.data.models.auth.LoginResponse
import com.kz.dagdy.data.models.network.Resource
import com.kz.dagdy.data.repository.error_handler.RepositoryErrorHandler
import com.kz.dagdy.network.api.AuthApi
import com.kz.dagdy.utils.live_data.Event
import javax.inject.Inject

class AuthRepositoryImpl
@Inject constructor(
    private val errorHandler: RepositoryErrorHandler,
    private val api: AuthApi
) : AuthRepository {


    override fun loginUser(email: String, password: String): LiveData<Event<Resource<LoginResponse>>> {
        val liveData = MutableLiveData<Event<Resource<LoginResponse>>>()

        val params = mutableMapOf<String, String>()
        params[AuthApi.EMAIL_KEY] = email
        params[AuthApi.PASSWORD_KEY] = password

        api
            .login(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{liveData.postValue(Event(Resource.loading(null)))}
            .subscribeWith(object : DisposableSingleObserver<LoginResponse>() {
                override fun onSuccess(t: LoginResponse) {
                    liveData.postValue(Event(Resource.success(t)))
                }

                override fun onError(e: Throwable) {
                    liveData.postValue(Event(errorHandler.getError(e)))
                }
            })

        return liveData
    }
}