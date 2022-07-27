package rs.raf.jun.uros_nikolic_2619rn.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.states.NewsState

interface NewsContract {

    interface ViewModel {
        val newsState: LiveData<NewsState>

        fun fetchAllNews()
    }
}