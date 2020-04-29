package com.bignerdranch.android.quickjournal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class EntryEditViewModel() : ViewModel() {
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