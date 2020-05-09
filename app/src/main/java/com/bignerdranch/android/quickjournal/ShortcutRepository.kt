package com.bignerdranch.android.quickjournal

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.bignerdranch.android.quickjournal.database.ShortcutDatabase
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "shortcut-database"
class ShortcutRepository private constructor(context: Context) {
    private val database : ShortcutDatabase = Room.databaseBuilder(
        context.applicationContext,
        ShortcutDatabase::class.java,
        DATABASE_NAME
    ).build()
    private val shortcutDao = database.shortcutDao()
    private val executor = Executors.newSingleThreadExecutor()
    fun getShortcuts(): LiveData<List<Shortcut>> = shortcutDao.getShortcuts()
    fun getShortcut(id: UUID): LiveData<Shortcut?> = shortcutDao.getShortcut(id)
    fun updateShortcut(shortcut: Shortcut) {
        executor.execute {
            shortcutDao.updateShortcut(shortcut)
        }
    }
    fun addShortcut(shortcut: Shortcut) {
        executor.execute {
            shortcutDao.addShortcut(shortcut)
        }
    }
    companion object {
        private var INSTANCE: ShortcutRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ShortcutRepository(context)
            }
        }
        fun get(): ShortcutRepository {
            return INSTANCE ?:
            throw IllegalStateException("ShortcutRepository must be initialized")
        }
    }
}