package com.example.ontapandroid.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class Note(
    @ColumnInfo(name = "title")var title: String ="",
    @ColumnInfo(name = "description")var description: String="",

) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0


}