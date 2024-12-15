package com.ibrahimutkusarican.cleanarchitecturemovieapp.core

interface ResponseMapper<E : MovieEntity, R : MovieResponse> {
    fun mapResponseToEntity(response : R) : E
}

interface EntityMapper<E : MovieEntity, M : MovieModel>{
    fun mapEntityToModel(entity : E) : M
}

interface MovieEntity
interface MovieResponse
interface MovieModel