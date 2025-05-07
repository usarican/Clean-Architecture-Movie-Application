package com.iusarican.common.base

interface ResponseMapper<E,R> {
    fun mapResponseToEntity(response : R) : E
}

interface EntityMapper<E,M>{
    fun mapEntityToModel(entity : E) : M
}
