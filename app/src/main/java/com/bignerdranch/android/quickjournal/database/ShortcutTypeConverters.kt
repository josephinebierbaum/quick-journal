package com.bignerdranch.android.quickjournal.database

import androidx.room.TypeConverter
import java.util.*

class ShortcutTypeConverters {
    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }
    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }
    @TypeConverter
    fun toList(stringListString: String): List<String> {
        return stringListString.split("|").map { it }
    }

    @TypeConverter
    fun fromList(stringList: List<String>): String {
        return stringList.joinToString(separator = "|")
    }
}