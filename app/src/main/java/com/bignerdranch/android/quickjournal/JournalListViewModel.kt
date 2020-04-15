package com.bignerdranch.android.quickjournal

import androidx.lifecycle.ViewModel
import java.util.*

class JournalListViewModel: ViewModel() {
    val entries = mutableListOf<JournalEntry>()
    init {
        for (x in 1..3){
            for (n in 1..7){
                val entry = JournalEntry()
                entry.title = "Entry $x"
                var cal = Calendar.getInstance()
                cal.add(Calendar.DAY_OF_MONTH, n)
                entry.date = cal.time
                entry.writing = "This is entry number $x, trying to make sure that the code works. Hopefully this text shows up correctly, and if not, we've got some editing to do."
                entry.location = "Random place $n"
                entries+=entry
            }
        }
    }
}