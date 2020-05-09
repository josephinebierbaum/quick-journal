package com.bignerdranch.android.quickjournal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import java.util.*

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(),JournalListFragment.Callbacks, EntryFragment.Callbacks, EntryEditFragment.Callbacks, ShortcutListFragment.Callbacks,ShortcutInsertFragment.Callbacks {
    private lateinit var homePageRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val fragment = JournalListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }
    override fun onEntrySelected(entryId: UUID) {
        Log.d(TAG, "MainActivity.onEntrySelected: $entryId")
        val fragment = EntryFragment.newInstance(entryId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
    override fun onEditEntrySelected(entryId: UUID) {
        Log.d(TAG, "MainActivity.onEditEntrySelected: $entryId")
        val fragment = EntryEditFragment.newInstance(entryId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
    override fun onShortcutListSelected(entryId: UUID) {
        Log.d(TAG, "MainActivity.onShortcutListSelected: $entryId")
        val fragment = ShortcutListFragment.newInstance(entryId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
    override fun onShortcutSelected(entryId:UUID,shortcutId: UUID) {
        Log.d(TAG, "MainActivity.onInsertShortcutSelected: $shortcutId")
        val fragment = ShortcutInsertFragment.newInstance(entryId,shortcutId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
    override fun onEditShortcutSelected(entryId:UUID,shortcutId: UUID) {
        Log.d(TAG, "MainActivity.onEditShortcutSelected: $shortcutId")
        val fragment = ShortcutEditFragment.newInstance(entryId,shortcutId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
    override fun onReturnEntrySelected(entryId: UUID) {
        Log.d(TAG, "MainActivity.ReturnEntrySelected: $entryId")
        val fragment = EntryEditFragment.newInstance(entryId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
