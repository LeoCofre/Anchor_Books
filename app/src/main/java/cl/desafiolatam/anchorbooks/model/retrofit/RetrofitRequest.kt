package cl.desafiolatam.anchorbooks.model.retrofit

import retrofit2.Call
import retrofit2.Callback

class RetrofitRequest {


    private var onRetroListener: RetrofitBookAPI = RetrofitClient.getRetro().create(RetrofitBookAPI::class.java)

    //Request para conseguir la lista de libros
    fun getBooks(callback: Callback<MutableList<RetrofitBooks>>) {
        val call: Call<MutableList<RetrofitBooks>> = onRetroListener.getBooks()
        call.enqueue(callback)
    }

    //Request para conseguir detalles de libro
    fun getBookDetail(callback: Callback<RetrofitBooksDetail>, id: Int) {
        val call: Call<RetrofitBooksDetail> = onRetroListener.getBookDetail(id)
        call.enqueue(callback)
    }
}