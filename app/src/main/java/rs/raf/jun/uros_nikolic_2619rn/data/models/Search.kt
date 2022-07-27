package rs.raf.jun.uros_nikolic_2619rn.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Search(
   val instrumentId: String,
   val symbol: String,
   val name: String,
   val currency: String,
   val last: Float,
   val open: Float,
   val close: Float,
   val bid: Float,
   val ask: Float,
   val metrics: Metrika,
   val chart: Chart
)
