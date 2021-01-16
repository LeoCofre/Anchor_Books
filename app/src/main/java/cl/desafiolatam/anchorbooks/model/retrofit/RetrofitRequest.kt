package cl.desafiolatam.anchorbooks.model.retrofit

import retrofit2.Call
import retrofit2.Callback

class RetrofitRequest {


    private lateinit var onRetroListener: RetrofitBookAPI

    init {
        onRetroListener = RetrofitClient.getRetro().create(RetrofitBookAPI::class.java)
    }

    //Request para conseguir la lista de productos
    fun getBooks(callback: Callback<MutableList<RetrofitBooks>>) {
        val call: Call<MutableList<RetrofitBooks>> = onRetroListener.getBooks()
        call.enqueue(callback)
    }

    //Request para conseguir detalles de producto
    fun getBookDetail(callback: Callback<RetrofitBooksDetail>, id: Int) {
        val call: Call<RetrofitBooksDetail> = onRetroListener.getBookDetail(id)
        call.enqueue(callback)
    }
}