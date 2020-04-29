package com.bignerdranch.android.quickjournal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import java.util.*

class ShortcutEditViewModel {
    private val shortcutRepository = ShortcutRepository.get()
    private val shortcutIdLiveData = MutableLiveData<UUID>()
    var shortcutLiveData: LiveData<Shortcut?> =
        Transformations.switchMap(shortcutIdLiveData) { shortcutId ->
            shortcutRepository.getShortcut(shortcutId)
        }
    fun loadEntry(shortcutId: UUID) {
        shortcutIdLiveData.value = shortcutId
    }
}