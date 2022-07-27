package rs.raf.jun.uros_nikolic_2619rn.data.repositories

import io.reactivex.Observable
import rs.raf.jun.uros_nikolic_2619rn.data.datasources.remote.NewsService
import rs.raf.jun.uros_nikolic_2619rn.data.models.News
import timber.log.Timber

class NewsRepositoryImpl(
    private val remoteDataSource: NewsService
) : NewsRepository {

    override fun fetchAll(): Observable<List<News>> {
        return remoteDataSource
            .getAll()
            .map {
                it.data.newsItems
            }
    }

}