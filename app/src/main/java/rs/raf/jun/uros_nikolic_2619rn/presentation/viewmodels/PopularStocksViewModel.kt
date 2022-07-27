package rs.raf.jun.uros_nikolic_2619rn.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.jun.uros_nikolic_2619rn.data.repositories.PopularStocksRepository
import rs.raf.jun.uros_nikolic_2619rn.presentation.contract.PopularStocksContract
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.states.*
import timber.log.Timber

class PopularStocksViewModel(private val popularStocksRepository: PopularStocksRepository
): ViewModel(), PopularStocksContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val stocksState: MutableLiveData<PopularStocksState> = MutableLiveData()
    override val searchState: MutableLiveData<SearchState> = MutableLiveData()

    override fun fetchAllStocks() {
        val subscription = popularStocksRepository
            .fetchAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    stocksState.value = PopularStocksState.Success(it)
                },
                {
                    stocksState.value = PopularStocksState.Error("Error happened while fetching data from the server")
                }
            )
        subscriptions.add(subscription)
    }

    override fun detealjanPrikaz() {
        val subscription = popularStocksRepository
            .detaljanPrikaz()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    searchState.value = SearchState.Success(it)
                },
                {
                    searchState.value = SearchState.Error("Error happened while fetching data from the server")
                }
            )
        subscriptions.add(subscription)
    }
}