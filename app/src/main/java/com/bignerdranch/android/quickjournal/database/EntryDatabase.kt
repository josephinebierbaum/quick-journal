package com.bignerdranch.android.quickjournal.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bignerdranch.android.quickjournal.JournalEntry

@Database(entities = [ JournalEntry::class ], version=1, exportSchema = false)
@TypeConverters(EntryTypeConverters::class)
abstract class EntryDatabase : RoomDatabase() {
    abstract fun entryDao(): EntryDao
}