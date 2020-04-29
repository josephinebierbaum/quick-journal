package com.bignerdranch.android.quickjournal

import android.app.Application

class QuickJournalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        EntryRepository.initialize(this)
        ShortcutRepository.initialize(this)
    }
}