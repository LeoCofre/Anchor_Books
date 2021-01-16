package cl.desafiolatam.anchorbooks.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity ( tableName = "books_table")
data class RoomBook(
    val author: String,
    val country: String,
    @PrimaryKey val id: Int,
    val imageLink: String,
    val language: String,
    val title: String
)
