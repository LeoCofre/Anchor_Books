package cl.desafiolatam.anchorbooks.model.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    companion object {
        private const val PATH: String ="https://my-json-server.typicode.com/Himuravidal/anchorBooks/"
        private var retro: Retrofit? = null

        fun getRetro(): Retrofit {
            if (retro == null)
                synchronized(this) {
                    retro = Retrofit.Builder().baseUrl(PATH)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                }
            return retro!!
        }
    }
}