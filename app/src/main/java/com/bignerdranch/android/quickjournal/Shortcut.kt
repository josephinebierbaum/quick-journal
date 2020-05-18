package com.bignerdranch.android.quickjournal

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class Shortcut(@PrimaryKey val id: UUID = UUID.randomUUID(),
                    var title: String = "",
                    //var field: MutableList<String> = mutableListOf<String>(),
                    var field1:String = "",
                    var field2:String = "",
                    var field3:String = "",
                    var field4:String = "",
                    var field5:String = "",
                    var field6:String = "",
                    var result: String = "")