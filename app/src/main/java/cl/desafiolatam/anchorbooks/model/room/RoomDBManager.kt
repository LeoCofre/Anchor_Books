package cl.desafiolatam.anchorbooks.model.room

import androidx.lifecycle.LiveData
import androidx.room.PrimaryKey
import cl.desafiolatam.anchorbooks.model.retrofit.RetrofitBooks
import cl.desafiolatam.anchorbooks.model.retrofit.RetrofitBooksDetail

class RoomDBManager(private val dao:RoomDao) {

    //request para insertar lista productos a base de datos
    suspend fun insertProductList(list: MutableList<RetrofitBooks>){
        var rList:MutableList<RoomBook> = mutableListOf()
        for(prod in list)
        rList.add(RoomBook(prod.author, prod.country,prod.id,prod.imageLink, prod.language,prod.title))
        dao.insertProductList(rList)
    }

    //request para insertar detalle de producto a base de datos
    suspend fun insertDetail(det: RetrofitBooksDetail){
        dao.insertDetail(RoomDetail(det.author, det.country, det.delivery, det.id, det.imageLink, det.language, det.lastPrice,
            det.link, det.pages, det.price, det.title, det.year))


    }

    //request para actualizar producto en base de datos
    suspend fun updateProduct(prod: RetrofitBooks){
        dao.updateProduct(RoomBook( prod.author, prod.country, prod.id, prod.imageLink,prod.language,prod.title))
    }

    //request para actualizar detalle en base de datos
    suspend fun updateDetail(det: RetrofitBooksDetail){
        dao.updateDetail(RoomDetail(det.author, det.country, det.delivery, det.id, det.imageLink, det.language, det.lastPrice,
            det.link, det.pages, det.price, det.title, det.year))
    }

    //request para obtener lista productos de room
    fun getProductsList(): LiveData<MutableList<RoomBook>> {
        return dao.getProductsList()
    }

    //request para obtener detalle producto de room
    fun getDetail(id:Int): LiveData<RoomDetail> {
        return dao.getDetail(id)
    }

}