package com.example.ontapandroid.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ontapandroid.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDataBase : RoomDatabase(){
    abstract fun noteDao() : NoteDao

    companion object{
        @Volatile
        private var INSTANCE : NoteDataBase? = null

        fun getInstance(context : Context): NoteDataBase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDataBase::class.java,
                        "note_database"
                    ).build()
                }
                return instance
            }
        }

    }
}