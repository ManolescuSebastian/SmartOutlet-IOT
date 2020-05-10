package com.tekydevelop.android.smartoutletiot.data.mapper

open class NetworkException(error: Throwable) : RuntimeException(error)

class NoNetworkException(error: Throwable) : NetworkException(error)

class ServerUnreachableException(error: Throwable) : NetworkException(error)

class ConnectionException(error: Throwable) : NetworkException(error)

class HttpCallFailureException(error: Throwable) : NetworkException(error)
