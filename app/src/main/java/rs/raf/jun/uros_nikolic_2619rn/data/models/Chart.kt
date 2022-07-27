package rs.raf.jun.uros_nikolic_2619rn.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Chart(
    val bars: List<Bar>
)