package com.musiclibrary.crud.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "request")
class Request(
    val Name:String,
    val Telephone: String,
    val Problem: String,
    val Solve: String,
    @PrimaryKey(autoGenerate = true)
var RequestId: Int = 0,
) : Serializable