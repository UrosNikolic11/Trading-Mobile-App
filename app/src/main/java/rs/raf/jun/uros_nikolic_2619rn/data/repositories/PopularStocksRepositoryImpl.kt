package rs.raf.jun.uros_nikolic_2619rn.data.repositories

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import rs.raf.jun.uros_nikolic_2619rn.data.datasources.remote.PopularStocksService
import rs.raf.jun.uros_nikolic_2619rn.data.models.*

class PopularStocksRepositoryImpl(
    private val remoteDataSource: PopularStocksService): PopularStocksRepository {

    override fun fetchAll(): Observable<List<PopularStocks>> {
       return remoteDataSource
           .getAll()
           .map {
               it.data.quotes
           }
    }

    override fun detaljanPrikaz(): Observable<Search> {
        return remoteDataSource
            .detaljanPrikaz()
            .map {
                it.data
            }
    }
}