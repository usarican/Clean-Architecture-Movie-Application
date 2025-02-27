package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.mapper

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local.entities.LastSearchEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchItemModel
import javax.inject.Inject

class SearchItemModelMapper @Inject constructor() {
    fun entityToModel(lastSearchEntity: LastSearchEntity) =
        with(lastSearchEntity) {
            SearchItemModel(
                itemId = id,
                searchText = searchText
            )
        }

    fun modelToEntity(searchItemModel: SearchItemModel) =
        with(searchItemModel) {
            LastSearchEntity(
                id = itemId,
                searchText = searchText
            )
        }

    fun searchTextToEntity(searchText: String) =
        LastSearchEntity(
            searchText = searchText
        )
}