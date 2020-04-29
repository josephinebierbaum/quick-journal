package com.bignerdranch.android.quickjournal

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import java.util.UUID

private const val ARG_ENTRY_ID = "entry_id"
private const val TAG = "EntryEditFragment"
class EntryEditFragment: Fragment() {
    private lateinit var entry:JournalEntry
    private lateinit var entryTitle: EditText
    private lateinit var entryDate: TextView
    private lateinit var entryWriting: EditText
    private lateinit var entryLocation: TextView
    private lateinit var entryPhoto1: ImageView
    private lateinit var entryPhoto2: ImageView
    private lateinit var addShortcutButton: ImageButton
    private lateinit var publishEntryButton: Button
    private val entryEditViewModel: EntryEditViewModel by lazy {
        ViewModelProviders.of(this).get(EntryEditViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        entry = JournalEntry()
        val entryId: UUID = arguments?.getSerializable(ARG_ENTRY_ID) as UUID
        Log.d(TAG, "args bundle entry ID: $entryId")
        entryEditViewModel.loadEntry(entryId)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.edit_journal_fragment, container, false)
        entryTitle = view.findViewById(R.id.entry_title)
        entryDate = view.findViewById(R.id.entry_date)
        entryWriting = view.findViewById(R.id.entry_writing)
        entryLocation = view.findViewById(R.id.entry_location)
        entryPhoto1 = view.findViewById(R.id.entry_photo1)
        entryPhoto2 = view.findViewById(R.id.entry_photo2)
        addShortcutButton = view.findViewById(R.id.add_shortcut)
        publishEntryButton = view.findViewById(R.id.publish_entry)
        return view
    }
    override fun onStart() {
        super.onStart()
        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
// This space intentionally left blank
            }
            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                entry.title = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) {
// This one too
            }
        }
        val writingWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
// This space intentionally left blank
            }
            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                entry.writing = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) {
// This one too
            }
        }
        entryTitle.addTextChangedListener(titleWatcher)
        entryWriting.addTextChangedListener(writingWatcher)
        publishEntryButton.setOnClickListener {
            entryEditViewModel.saveEntry(entry)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        entryEditViewModel.entryLiveData.observe(
            viewLifecycleOwner,
            Observer { entry ->
                entry?.let {
                    this.entry = entry
                    updateUI()
                }
            })
    }
    private fun updateUI() {
        entryTitle.setText(entry.title)
        var date = DateFormat.format("EEEE, LLLL dd, yyyy",entry.date)
        entryDate.setText(date)
        entryWriting.setText(entry.writing)
        entryLocation.setText(entry.location)
    }
    override fun onStop() {
        super.onStop()
        entryEditViewModel.saveEntry(entry)
    }
    companion object {
        fun newInstance(entryId: UUID): EntryEditFragment {
            val args = Bundle().apply {
                putSerializable(ARG_ENTRY_ID, entryId)
            }
            return EntryEditFragment().apply {
                arguments = args
            }
        }
    }
}