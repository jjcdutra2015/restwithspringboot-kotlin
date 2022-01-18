package jjcdutra2015.com.git.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "books")
data class Book(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "author", nullable = false, length = 180)
    var author: String,

    @Column(name = "launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    var launchDate: Date,

    @Column(nullable = false)
    var price: Double,

    @Column(nullable = false, length = 250)
    var title: String
)
