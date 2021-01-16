package cl.desafiolatam.anchorbooks.model.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductList(list: MutableList<RoomBook>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(det:RoomDetail)
    @Update
    suspend fun updateProduct(prod:RoomBook)
    @Update
    suspend fun updateDetail(det:RoomDetail)
    @Query("SELECT * FROM books_table")
    fun getProductsList(): LiveData<MutableList<RoomBook>>
    @Query("SELECT * FROM details_table WHERE `id`=:id")
    fun getDetail(id:Int): LiveData<RoomDetail>
}