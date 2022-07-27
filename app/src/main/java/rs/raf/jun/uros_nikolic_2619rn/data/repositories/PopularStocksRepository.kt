package rs.raf.jun.uros_nikolic_2619rn.data.repositories

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import rs.raf.jun.uros_nikolic_2619rn.data.models.*

interface PopularStocksRepository {

    fun fetchAll(): Observable<List<PopularStocks>>
    fun detaljanPrikaz(): Observable<Search>
}