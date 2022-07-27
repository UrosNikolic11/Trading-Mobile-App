package rs.raf.jun.uros_nikolic_2619rn.data.repositories

import io.reactivex.Observable
import rs.raf.jun.uros_nikolic_2619rn.data.models.News
import rs.raf.jun.uros_nikolic_2619rn.data.models.Resource
interface NewsRepository {

    fun fetchAll(): Observable<List<News>>
}