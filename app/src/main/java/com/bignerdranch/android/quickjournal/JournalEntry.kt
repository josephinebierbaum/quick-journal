package com.bignerdranch.android.quickjournal

import android.text.format.DateFormat
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
//date in string to prevent errors when finding entries to show in RecyclerView
@Entity
data class JournalEntry (@PrimaryKey val id: UUID = UUID.randomUUID(),
                         var title: String = "",
                         var writing: String = "",
                         //var date: String = ""+DateFormat.format("EEEE, LLLL dd, yyyy",Date()),
                         var date:Date = Date(),
                         var location: String = ""){
    //photo1 will also be the thumbnail
    val photo1
        get() = "IMG_$id.jpg"
    val photo2
        get() = "IMG_$id 2.jpg"
}