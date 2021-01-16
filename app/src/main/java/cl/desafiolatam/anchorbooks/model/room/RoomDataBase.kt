package cl.desafiolatam.anchorbooks.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomBook ::class,RoomDetail ::class], version = 1, exportSchema = false)

abstract class RoomDataBase : RoomDatabase() {
    abstract fun getDao():RoomDao

    companion object{
        private const val DB_NAME:String = "ensayo_db"
        private var ensayoDB:RoomDataBase? = null

        fun getDB(context: Context):RoomDataBase{
            if(ensayoDB == null)
                synchronized(this){
                    ensayoDB = Room.databaseBuilder(context, RoomDataBase::class.java, DB_NAME).build()
                }
            return ensayoDB!!
        }
    }
}