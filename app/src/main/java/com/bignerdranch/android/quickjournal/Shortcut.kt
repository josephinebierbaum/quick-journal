package com.bignerdranch.android.quickjournal

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class Shortcut(@PrimaryKey val id: UUID = UUID.randomUUID(),
                    var title: String = "",
                    //var field: MutableList<String> = mutableListOf<String>(),
                    var field1:String = "",
                    var result: String = "")