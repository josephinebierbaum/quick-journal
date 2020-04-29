package com.bignerdranch.android.quickjournal

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class Shortcut(@PrimaryKey val id: UUID = UUID.randomUUID(),
                    var title: String = "",
                    var fields: MutableList<String> = mutableListOf<String>(),
                    var result: String = "")