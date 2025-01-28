package com.example.appnotes_4m.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appnotes_4m.data.model.Note


@Database(entities = [Note::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}