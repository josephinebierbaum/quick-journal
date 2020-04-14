package com.bignerdranch.android.quickjournal

import java.util.*

data class JournalEntry (val id: UUID = UUID.randomUUID(),
                         var title: String = "",
                         var writing: String = "",
                         var date: Date,
                         var location: String = ""){
    val photo1
        get() = "IMG_$id.jpg"
    val photo2
        get() = "IMG_$id.jpg"
}