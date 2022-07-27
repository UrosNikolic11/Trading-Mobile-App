package rs.raf.jun.uros_nikolic_2619rn.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.jun.uros_nikolic_2619rn.data.repositories.NewsRepository
import rs.raf.jun.uros_nikolic_2619rn.presentation.contract.NewsContract
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.states.NewsState
import timber.log.Timber

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel(), NewsContract.ViewModel{

    private val subscriptions = CompositeDisposable()
    override val newsState: MutableLiveData<NewsState> = MutableLiveData()

    override fun fetchAllNews() {
        val subscription = newsRepository
            .fetchAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    newsState.value = NewsState.Success(it)
                },
                {
                    newsState.value = NewsState.Error("Error happened while fetching data from the server")
                }
            )
        subscriptions.add(subscription)
    }

}