package cl.desafiolatam.anchorbooks.model.retrofit

import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitBookAPI {
    @GET("books")
     fun getBooks(): Call<MutableList<RetrofitBooks>>

    @GET("bookDetail/{id}")
     fun getBookDetail(@Path("id") id: Int):Call <RetrofitBooksDetail>

}
