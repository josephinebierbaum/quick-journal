package com.bignerdranch.android.quickjournal.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bignerdranch.android.quickjournal.JournalEntry
import java.util.*

@Dao
interface EntryDao {
    @Query("SELECT * FROM journalEntry")
    fun getEntries(): LiveData<List<JournalEntry>>
    @Query("SELECT * FROM journalEntry WHERE id=(:id)")
    fun getEntry(id: UUID): LiveData<JournalEntry?>
    @Update
    fun updateEntry(entry: JournalEntry)
    @Insert
    fun addEntry(entry: JournalEntry)
    @Delete
    fun deleteEntry(entry: JournalEntry)
}