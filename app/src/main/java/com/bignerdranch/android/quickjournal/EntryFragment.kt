package com.bignerdranch.android.quickjournal

import android.content.Context
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import java.util.UUID
import java.util.Date
import android.text.format.DateFormat

private const val ARG_ENTRY_ID = "entry_id"
private const val TAG = "EntryFragment"
class EntryFragment: Fragment() {
    private lateinit var entry: JournalEntry
    private lateinit var entryTitle: TextView
    private lateinit var entryDate: TextView
    private lateinit var entryPhoto1: ImageView
    private lateinit var entryPhoto2: ImageView
    private lateinit var entryWriting: TextView
    private lateinit var entryLocation: TextView
    private lateinit var editEntryButton: ImageButton
    private val entryViewModel: EntryViewModel by lazy {
        ViewModelProviders.of(this).get(EntryViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        entry = JournalEntry()
        val entryId: UUID = arguments?.getSerializable(ARG_ENTRY_ID) as UUID
        Log.d(TAG, "args bundle entry ID: $entryId")
        entryViewModel.loadEntry(entryId)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.view_journal_fragment, container, false)
        entryTitle = view.findViewById(R.id.entry_title)
        entryDate = view.findViewById(R.id.entry_date)
        entryPhoto1 = view.findViewById(R.id.entry_photo1)
        entryPhoto2 = view.findViewById(R.id.entry_photo2)
        entryWriting = view.findViewById(R.id.entry_writing)
        entryLocation = view.findViewById(R.id.entry_location)
        editEntryButton = view.findViewById(R.id.edit_journal)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        entryViewModel.entryLiveData.observe(
            viewLifecycleOwner,
            Observer { entry ->
                entry?.let {
                    this.entry = entry
                    updateUI()
                }
            })
    }
    override fun onStart(){
        super.onStart()
        editEntryButton.setOnClickListener{
            callbacks?.onEditEntrySelected(entry.id)
        }
    }
    private fun updateUI() {
        entryTitle.setText(entry.title)
        var date = DateFormat.format("EEEE, LLLL dd, yyyy",entry.date)
        entryDate.setText(date)
        entryWriting.setText(entry.writing)
        entryLocation.setText(entry.location)
    }
    companion object {
        fun newInstance(entryId: UUID): EntryFragment {
            val args = Bundle().apply {
                putSerializable(ARG_ENTRY_ID, entryId)
            }
            return EntryFragment().apply {
                arguments = args
            }
        }
    }
    /**
     * Required interface for hosting activities
     */
    interface Callbacks {
        fun onEditEntrySelected(entryId: UUID)
    }
    private var callbacks: Callbacks? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }
    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }
}