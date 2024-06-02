package com.kz.dagdy.data.repository.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kz.dagdy.data.models.auth.login.LoginRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import com.kz.dagdy.data.models.auth.login.LoginResponse
import com.kz.dagdy.data.models.auth.registration.RegisterResponse
import com.kz.dagdy.data.models.auth.registration.RegistrationRequest
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
    override fun register(body: RegistrationRequest): LiveData<Event<Resource<RegisterResponse>>> {
        val liveData = MutableLiveData<Event<Resource<RegisterResponse>>>()

        api
            .register(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { liveData.postValue(Event(Resource.loading(null))) }
            .subscribeWith(object : DisposableSingleObserver<RegisterResponse>() {

                override fun onSuccess(t: RegisterResponse) {
                    liveData.postValue(Event(Resource.success(t)))
                }

                override fun onError(e: Throwable) {
                    liveData.postValue(Event(errorHandler.getError(e)))
                }
            })

        return liveData
    }

    override fun login(body: LoginRequest): LiveData<Event<Resource<LoginResponse>>> {
        val liveData = MutableLiveData<Event<Resource<LoginResponse>>>()

        api
            .login(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { liveData.postValue(Event(Resource.loading(null))) }
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