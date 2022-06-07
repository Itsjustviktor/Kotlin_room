package com.musiclibrary.crud.db
import androidx.lifecycle.LiveData
import androidx.room.*
import com.musiclibrary.crud.model.Request

@Dao
interface RequestQuery {
    @Query("SELECT * FROM request")
    fun getAll(): LiveData<List<Request>>

    @Query("SELECT * FROM request WHERE RequestId = :id")
    fun get(id: Int): LiveData<Request>

    @Insert
    fun insertAll(vararg request: Request)

    @Update
    fun update(request: Request)

    @Delete
    fun delete(request: Request)
}