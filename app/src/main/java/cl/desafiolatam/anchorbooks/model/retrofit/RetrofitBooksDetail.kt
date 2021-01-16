package cl.desafiolatam.anchorbooks.model.retrofit

data class RetrofitBooksDetail(
    val author: String,
    val country: String,
    val delivery: Boolean,
    val id: Int,
    val imageLink: String,
    val language: String,
    val lastPrice: Int,
    val link: String,
    val pages: Int,
    val price: Int,
    val title: String,
    val year: Int
)