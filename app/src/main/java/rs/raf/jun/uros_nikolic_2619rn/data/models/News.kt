package rs.raf.jun.uros_nikolic_2619rn.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class News(
    val title: String,
    val content: String,
    val link: String,
    val date: String,
    val image: String
)
