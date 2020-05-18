package com.bignerdranch.android.quickjournal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class ShortcutEditViewModel: ViewModel() {
    private val shortcutRepository = ShortcutRepository.get()
    private val shortcutIdLiveData = MutableLiveData<UUID>()
    var shortcutLiveData: LiveData<Shortcut?> =
        Transformations.switchMap(shortcutIdLiveData) { shortcutId ->
            shortcutRepository.getShortcut(shortcutId)
        }
    fun loadShortcut(shortcutId: UUID) {
        shortcutIdLiveData.value = shortcutId
    }
    fun saveShortcut(shortcut: Shortcut) {
        shortcutRepository.updateShortcut(shortcut)
    }
    fun deleteShortcut(shortcut: Shortcut){
        shortcutRepository.deleteShortcut(shortcut)
    }
}