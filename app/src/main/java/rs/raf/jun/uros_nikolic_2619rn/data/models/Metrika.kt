package rs.raf.jun.uros_nikolic_2619rn.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Metrika(
   val alpha: Float,
   val beta: Float,
   val sharp: Float,
   val marketCup: Float,
   val eps: Float,
   val ebit: Float
)
