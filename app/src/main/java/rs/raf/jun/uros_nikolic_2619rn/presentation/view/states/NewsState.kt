package rs.raf.jun.uros_nikolic_2619rn.presentation.view.states

import rs.raf.jun.uros_nikolic_2619rn.data.models.News

sealed class NewsState {
    object Loading: NewsState()
    object DataFetched: NewsState()
    data class Success(val news: List<News>): NewsState()
    data class Error(val message: String): NewsState()
}