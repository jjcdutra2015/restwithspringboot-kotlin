package jjcdutra2015.com.git.data.vo

import java.util.*

data class BookVO(
    val id: Long,
    var author: String,
    var launchDate: Date,
    var price: Double,
    var title: String
)
