package rs.raf.jun.uros_nikolic_2619rn.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rs.raf.jun.uros_nikolic_2619rn.data.models.DataSearchResponse
import rs.raf.jun.uros_nikolic_2619rn.data.models.DataStockResponse

interface PopularStocksService {

    @GET("getIndexes")
    fun getAll(): Observable<DataStockResponse>

//    Ovako bi valjda trebalo da se radi da je API aktivan, posto nije koristio sam svoj link
//    @GET("searchQuote/{symbol}")
//    fun detaljanPrikaz(@Path("symbol") symbol:String): Observable<DataSearchResponse>

// ovako sam uzeo podatke za detaljan prikaz deonice
    @GET("searchQuote")
    fun detaljanPrikaz(): Observable<DataSearchResponse>
}