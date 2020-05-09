package com.bignerdranch.android.quickjournal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class ShortcutInsertViewModel: ViewModel() {
    private val shortcutRepository = ShortcutRepository.get()
    private val shortcutIdLiveData = MutableLiveData<UUID>()
    var shortcutLiveData: LiveData<Shortcut?> =
        Transformations.switchMap(shortcutIdLiveData) { shortcutId ->
            shortcutRepository.getShortcut(shortcutId)
        }
    fun loadShortcut(shortcutId: UUID) {
        shortcutIdLiveData.value = shortcutId
    }
    private val entryRepository = EntryRepository.get()
    private val entryIdLiveData = MutableLiveData<UUID>()
    var entryLiveData: LiveData<JournalEntry?> =
        Transformations.switchMap(entryIdLiveData) { entryId ->
            entryRepository.getEntry(entryId)
        }
    fun loadEntry(entryId: UUID) {
        entryIdLiveData.value = entryId
    }
    fun saveEntry(entry: JournalEntry) {
        entryRepository.updateEntry(entry)
    }
}