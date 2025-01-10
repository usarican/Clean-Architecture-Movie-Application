package com.ibrahimutkusarican.cleanarchitecturemovieapp.core

sealed class MovieExceptions(message : String?) : Throwable(message){
    class NoInternetException(message: String?) : MovieExceptions(message)
    class CoilHttpException(message: String?) : MovieExceptions(message)
    class UnauthorizedException(message: String?) : MovieExceptions(message)
    class NotFoundException(message: String?) : MovieExceptions(message)
    class InternalServerErrorException(message: String?) : MovieExceptions(message)
    class GeneralHttpException(val code: Int, message: String?) : MovieExceptions(message)
    class GeneralException(message: String?) : MovieExceptions(message)
}