package cl.desafiolatam.anchorbooks.model.retrofit

data class RetrofitBooks(
    val author: String,
    val country: String,
    val id: Int,
    val imageLink: String,
    val language: String,
    val title: String
)