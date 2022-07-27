package rs.raf.jun.uros_nikolic_2619rn.presentation.view.states

import rs.raf.jun.uros_nikolic_2619rn.data.models.Search

sealed class SearchState {
    data class Success(val search: Search): SearchState()
    data class Error(val message: String): SearchState()
}