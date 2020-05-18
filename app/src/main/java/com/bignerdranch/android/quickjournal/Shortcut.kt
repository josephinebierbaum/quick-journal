package com.bignerdranch.android.quickjournal

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class Shortcut(@PrimaryKey val id: UUID = UUID.randomUUID(),
                    var title: String = "",
                    //var field: MutableList<String> = mutableListOf<String>(),
                    var field1:String = "N/A",
                    var field2:String = "N/A",
                    var field3:String = "N/A",
                    var field4:String = "N/A",
                    var field5:String = "N/A",
                    var field6:String = "N/A",
                    var result: String = "")