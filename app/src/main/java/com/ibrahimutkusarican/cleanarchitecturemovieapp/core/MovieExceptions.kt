package com.ibrahimutkusarican.cleanarchitecturemovieapp.core

sealed class MovieExceptions(message : String) : Exception(message){
    class NoInternetException(message: String) : MovieExceptions(message)
    class ApiException(message: String) : MovieExceptions(message)
}