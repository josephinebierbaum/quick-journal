package com.bignerdranch.android.quickjournal

import androidx.lifecycle.ViewModel

class ShortcutListViewModel: ViewModel() {
    private val shortcutRepository = ShortcutRepository.get()
    val shortcutListLiveData = shortcutRepository.getShortcuts()
//    val shortcuts = mutableListOf<Shortcut>()
//    init {
//        for (x in 0 until 5) {
//            val shortcut = Shortcut()
//            shortcut.title = "Shortcut #$x"
//            for (n in 0..x)
//            shortcut.fields.add("$n field")
//            shortcut.result = "We'll figure out result stuff later"
//            shortcuts += shortcut
//        }
//    }
}