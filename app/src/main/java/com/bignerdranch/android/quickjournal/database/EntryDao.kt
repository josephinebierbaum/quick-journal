package database

import androidx.room.Dao
import androidx.room.Query
import com.bignerdranch.android.quickjournal.JournalEntry
import java.util.*

@Dao
interface EntryDao {
    @Query("SELECT * FROM journalEntry")
    fun getEntries(): List<JournalEntry>
    @Query("SELECT * FROM journalEntry WHERE id=(:id)")
    fun getEntry(id: UUID): JournalEntry?
}