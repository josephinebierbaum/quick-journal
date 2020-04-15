package com.bignerdranch.android.quickjournal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "JournalListFragment"
class JournalListFragment: Fragment(){
    private lateinit var journalRecyclerView:RecyclerView
    private var adapter: EntryAdapter? = null
    private val journalListViewModel:JournalListViewModel by lazy{
        ViewModelProviders.of(this).get(JournalListViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total entries: ${journalListViewModel.entries.size}")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_page_fragment, container, false)
        journalRecyclerView =
            view.findViewById(R.id.fragment_journal_container) as RecyclerView
        journalRecyclerView.layoutManager = LinearLayoutManager(context)
        updateUI()
        return view
    }
    private fun updateUI() {
        val entries = journalListViewModel.entries
        adapter = EntryAdapter(entries)
        journalRecyclerView.adapter = adapter
    }
    companion object {
        fun newInstance(): JournalListFragment {
            return JournalListFragment()
        }
    }
    private inner class EntryHolder(view: View)
        : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = itemView.findViewById(R.id.entry_title)
        val previewTextView: TextView = itemView.findViewById(R.id.entry_writing_preview)
    }
    private inner class EntryAdapter(var entries: List<JournalEntry>)
        : RecyclerView.Adapter<EntryHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : EntryHolder {
            val view = layoutInflater.inflate(R.layout.list_entry_fragment, parent, false)
            return EntryHolder(view)
        }
        override fun getItemCount() = entries.size
        override fun onBindViewHolder(holder: EntryHolder, position: Int) {
            val entry = entries[position]
            holder.apply {
                titleTextView.text = entry.title
                previewTextView.text = entry.writing.toString()
            }
        }
    }
}