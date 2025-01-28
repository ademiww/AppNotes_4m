package com.example.appnotes_4m.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    val title: String,
    val description: String,
    val data: String,
    val color: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}