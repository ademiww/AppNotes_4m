package com.example.appnotes_4m

import android.app.Application
import androidx.room.Room
import com.example.appnotes_4m.data.db.DataBase

class App : Application() {

    companion object {
        var appDataBase: DataBase? = null
    }

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceHelper()
        sharedPreferences.init(this)
        getInstance()
    }

    private fun getInstance(): DataBase? {
        if (appDataBase == null) {
            appDataBase = applicationContext?.let { context ->
                Room.databaseBuilder(
                    context,
                    DataBase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDataBase
    }
}