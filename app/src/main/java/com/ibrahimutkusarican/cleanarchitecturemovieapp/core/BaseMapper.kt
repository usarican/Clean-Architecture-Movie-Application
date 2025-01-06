package com.ibrahimutkusarican.cleanarchitecturemovieapp.core

interface ResponseMapper<E,R> {
    fun mapResponseToEntity(response : R) : E
}

interface EntityMapper<E,M>{
    fun mapEntityToModel(entity : E) : M
}
