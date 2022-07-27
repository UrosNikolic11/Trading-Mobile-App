package rs.raf.jun.uros_nikolic_2619rn.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import rs.raf.jun.uros_nikolic_2619rn.data.models.DataResponse

interface NewsService {

    @GET("getNews")
    fun getAll(): Observable<DataResponse>
}