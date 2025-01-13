package com.ibrahimutkusarican.cleanarchitecturemovieapp.core

sealed class MovieException(message : String?) : Throwable(message){
    class NoInternetException(message: String?) : MovieException(message)
    class CoilHttpException(message: String?) : MovieException(message)
    class UnauthorizedException(message: String?) : MovieException(message)
    class NotFoundException(message: String?) : MovieException(message)
    class InternalServerErrorException(message: String?) : MovieException(message)
    class GeneralHttpException(val code: Int, message: String?) : MovieException(message)
    class GeneralException(message: String?) : MovieException(message)
}