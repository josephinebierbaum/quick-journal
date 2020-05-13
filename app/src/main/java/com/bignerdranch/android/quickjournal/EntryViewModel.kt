package com.bignerdranch.android.quickjournal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.io.File
import java.util.*

class EntryViewModel() : ViewModel() {
    private val entryRepository = EntryRepository.get()
    private val entryIdLiveData = MutableLiveData<UUID>()
    var entryLiveData: LiveData<JournalEntry?> =
        Transformations.switchMap(entryIdLiveData) { entryId ->
            entryRepository.getEntry(entryId)
        }
    fun loadEntry(entryId: UUID) {
        entryIdLiveData.value = entryId
    }
    fun getPhoto1File(entry: JournalEntry): File {
        return entryRepository.getPhoto1File(entry)
    }
    fun getPhoto2File(entry: JournalEntry): File {
        return entryRepository.getPhoto2File(entry)
    }
}