package com.bignerdranch.android.quickjournal

import android.text.format.DateFormat
import androidx.lifecycle.ViewModel
import java.util.*

class JournalListViewModel: ViewModel() {
    val entries = mutableListOf<JournalEntry>()
    init {
        for (x in 1..20){
            for (n in 1..3){
                val entry = JournalEntry()
                entry.title = "Entry $n"
                var cal = Calendar.getInstance()
                cal.add(Calendar.DAY_OF_MONTH, x-1)
                //entry.date = ""+DateFormat.format("EEEE, LLLL dd, yyyy",(cal.time))
                entry.date = cal.time
                entry.writing = "This is entry number $n. " +entry.date +" Trying to make sure that the code works. Hopefully this text shows up correctly, and if not, we've got some editing to do."
                entry.location = "Random place $x"
                entries+=entry
            }
        }
    }
}