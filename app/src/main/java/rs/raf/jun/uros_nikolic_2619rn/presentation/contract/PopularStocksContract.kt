package rs.raf.jun.uros_nikolic_2619rn.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.states.*

interface PopularStocksContract {

    interface ViewModel {
        val stocksState: LiveData<PopularStocksState>
        val searchState: LiveData<SearchState>
        fun fetchAllStocks()
        fun detealjanPrikaz()
    }
}