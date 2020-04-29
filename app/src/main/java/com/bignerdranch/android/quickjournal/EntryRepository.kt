package com.bignerdranch.android.quickjournal

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.bignerdranch.android.quickjournal.database.EntryDatabase
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "entry-com.bignerdranch.android.quickjournal.database"
class EntryRepository private constructor(context: Context) {
    private val database : EntryDatabase = Room.databaseBuilder(
        context.applicationContext,
        EntryDatabase::class.java,
        DATABASE_NAME
    ).build()
    private val entryDao = database.entryDao()
    private val executor = Executors.newSingleThreadExecutor()
    fun getEntries(): LiveData<List<JournalEntry>> = entryDao.getEntries()
    fun getEntry(id: UUID): LiveData<JournalEntry?> = entryDao.getEntry(id)
    fun updateEntry(entry: JournalEntry) {
        executor.execute {
            entryDao.updateEntry(entry)
        }
    }
    fun addEntry(entry: JournalEntry) {
        executor.execute {
            entryDao.addEntry(entry)
        }
    }
    companion object {
        private var INSTANCE: EntryRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = EntryRepository(context)
            }
        }
        fun get(): EntryRepository {
            return INSTANCE ?:
            throw IllegalStateException("EntryRepository must be initialized")
        }
    }
}