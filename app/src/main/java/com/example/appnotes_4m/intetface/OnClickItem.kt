package com.example.appnotes_4m.intetface

import com.example.appnotes_4m.data.model.Note

interface OnClickItem {
    fun onLongClick(noteModel: Note)

    fun onClick(noteModel: Note)
}