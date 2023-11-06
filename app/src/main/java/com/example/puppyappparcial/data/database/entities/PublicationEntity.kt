package com.example.puppyappparcial.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PublicationTable")
data class PublicationEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id:Int=0,
    @ColumnInfo(name = "breed") val breed:String,
    @ColumnInfo(name = "subBreed") val subBreed:String,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "age") val age:Int,
    @ColumnInfo(name = "sex") val sex:String,
    @ColumnInfo(name = "description") val description:String,
    @ColumnInfo(name = "weigth") val weigth:Float,
    //TODO cambiar a enum ubicaciones
    @ColumnInfo(name = "location") val location:String,
    @ColumnInfo(name = "imgs") val imgs: String,
    @ColumnInfo(name = "owner") val owner:String,
    @ColumnInfo(name = "favorite") val favorite:Boolean,
    @ColumnInfo(name = "adopted") val adopted:Boolean
)
