package com.tekydevelop.android.smartoutletiot.data.mapper

import io.reactivex.Single
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


fun <T : DomainMappable<R>, R> Single<T>.mapToDomain(): Single<R> = this.map { it.asDomain() }


fun <T> Single<T>.mapNetworkErrors(): Single<T> =
    this.onErrorResumeNext { error ->
        when (error) {
            is SocketTimeoutException -> Single.error(
                NoNetworkException(
                    error
                )
            )
            is UnknownHostException -> Single.error(
                ServerUnreachableException(
                    error
                )
            )
            is ConnectionException -> Single.error(
                ConnectionException(
                    error
                )
            )
            is HttpException -> Single.error(
                HttpCallFailureException(
                    error
                )
            )
            else -> Single.error(error)
        }
    }