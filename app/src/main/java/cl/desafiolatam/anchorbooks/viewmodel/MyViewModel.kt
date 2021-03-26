package cl.desafiolatam.anchorbooks.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import cl.desafiolatam.anchorbooks.model.retrofit.RetrofitBooks
import cl.desafiolatam.anchorbooks.model.retrofit.RetrofitBooksDetail
import cl.desafiolatam.anchorbooks.model.retrofit.RetrofitRequest
import cl.desafiolatam.anchorbooks.model.room.RoomBook
import cl.desafiolatam.anchorbooks.model.room.RoomDBManager
import cl.desafiolatam.anchorbooks.model.room.RoomDataBase
import cl.desafiolatam.anchorbooks.model.room.RoomDetail
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val dbManager: RoomDBManager = RoomDBManager(RoomDataBase.getDB(application).getDao())
    val productList: LiveData<MutableList<RoomBook>>
    lateinit var detail: LiveData<RoomDetail>

    init {
        productList = dbManager.getProductsList()
        if (productList.value.isNullOrEmpty())
            getProdListRetro()
    }

    //obtiene detalles de room, de lo contrario lo obtiene de retrofit
    fun getDetails(id: Int) {
        detail = dbManager.getDetail(id)

        if (detail.value == null || detail.value!!.title.isEmpty())
            getDetailsRetro(id)
    }

    fun insertProductsList(list: MutableList<RetrofitBooks>) =
        viewModelScope.launch { dbManager.insertProductList(list) }

    fun insertDetail(det: RetrofitBooksDetail) =
        viewModelScope.launch { dbManager.insertDetail(det) }

    fun updateProduct(prod: RetrofitBooks) = viewModelScope.launch { dbManager.updateProduct(prod) }

    fun updateDetail(det: RetrofitBooksDetail) =
        viewModelScope.launch { dbManager.updateDetail(det) }

    fun getProdListRetro() {
        RetrofitRequest().getBooks(object :
            Callback<MutableList<RetrofitBooks>> {
            override fun onResponse(
                call: Call<MutableList<RetrofitBooks>>,
                response: Response<MutableList<RetrofitBooks>>
            ) {
                if (!response.body().isNullOrEmpty())
                    insertProductsList(response.body()!!)
            }

            override fun onFailure(call: Call<MutableList<RetrofitBooks>>, t: Throwable) {
                Toast.makeText(
                    getApplication(),
                    "Error, couldn't get products list",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    fun getDetailsRetro(id: Int) {
        RetrofitRequest().getBookDetail(object : Callback<RetrofitBooksDetail> {
            override fun onResponse(
                call: Call<RetrofitBooksDetail>,
                response: Response<RetrofitBooksDetail>
            ) {
                if (response.body() != null)
                    insertDetail(response.body()!!)
            }

            override fun onFailure(call: Call<RetrofitBooksDetail>, t: Throwable) {
                Toast.makeText(
                    getApplication(),
                    "Error, no se pudieron obtener detalles",
                    Toast.LENGTH_LONG
                ).show()
            }
        }, id)
    }

}