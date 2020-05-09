package com.bignerdranch.android.quickjournal.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bignerdranch.android.quickjournal.Shortcut
import java.util.*

@Dao
interface ShortcutDao {
    @Query("SELECT * FROM shortcut")
    fun getShortcuts(): LiveData<List<Shortcut>>
    @Query("SELECT * FROM shortcut WHERE id=(:id)")
    fun getShortcut(id: UUID): LiveData<Shortcut?>
    @Update
    fun updateShortcut(shortcut: Shortcut)
    @Insert
    fun addShortcut(shortcut: Shortcut)
}