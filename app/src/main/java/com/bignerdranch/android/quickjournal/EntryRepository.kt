package com.bignerdranch.android.quickjournal

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.bignerdranch.android.quickjournal.database.EntryDatabase
import java.io.File
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
    private val filesDir = context.applicationContext.filesDir
    fun getPhoto1File(entry:JournalEntry): File = File(filesDir, entry.photo1)
    fun getPhoto2File(entry:JournalEntry): File = File(filesDir, entry.photo2)
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
    fun deleteEntry(entry: JournalEntry){
        executor.execute {
            entryDao.deleteEntry(entry)
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