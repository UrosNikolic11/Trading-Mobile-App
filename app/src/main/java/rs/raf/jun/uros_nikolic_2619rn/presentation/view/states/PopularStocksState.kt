package rs.raf.jun.uros_nikolic_2619rn.presentation.view.states

import rs.raf.jun.uros_nikolic_2619rn.data.models.PopularStocks

sealed class PopularStocksState {
    object Loading: PopularStocksState()
    object DataFetched: PopularStocksState()
    data class Success(val popularstocks: List<PopularStocks>): PopularStocksState()
    data class Error(val message: String): PopularStocksState()
}