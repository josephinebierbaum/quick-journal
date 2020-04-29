package com.bignerdranch.android.quickjournal.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bignerdranch.android.quickjournal.Shortcut

@Database(entities = [ Shortcut::class ], version=1, exportSchema = false)
@TypeConverters(ShortcutTypeConverters::class)
abstract class ShortcutDatabase : RoomDatabase() {
    abstract fun shortcutDao(): ShortcutDao
}