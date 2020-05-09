package com.bignerdranch.android.quickjournal

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import java.util.*

private const val ARG_SHORTCUT_ID = "shortcut_id"
private const val ARG_ENTRY_ID = "entry_id"
private const val TAG = "ShortcutInsertFragment"
class ShortcutInsertFragment: Fragment() {
    private lateinit var shortcut: Shortcut
    private lateinit var entry: JournalEntry
    private lateinit var shortcutTitle: TextView
    private lateinit var editShortcutButton: ImageButton
    private lateinit var field1: EditText
    private lateinit var result: TextView
    private lateinit var fillFields: Array<String>
    private lateinit var publishShortcutButton: Button
    private val shortcutInsertViewModel: ShortcutInsertViewModel by lazy {
        ViewModelProviders.of(this).get(ShortcutInsertViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        entry = JournalEntry()
        val entryId: UUID = arguments?.getSerializable(ARG_ENTRY_ID) as UUID
        shortcutInsertViewModel.loadEntry(entryId)
        shortcut = Shortcut()
        val shortcutId: UUID = arguments?.getSerializable(ARG_SHORTCUT_ID) as UUID
        shortcutInsertViewModel.loadShortcut(shortcutId)
        Log.d(TAG, "this is field1: ${shortcut.field1}")
        fillFields= Array<String>(6){""}
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.insert_shortcut_fragment, container, false)
        shortcutTitle = view.findViewById(R.id.shortcut_title)
        editShortcutButton = view.findViewById(R.id.edit_shortcut)
        field1 = view.findViewById(R.id.field1)
        result = view.findViewById(R.id.result)
        publishShortcutButton = view.findViewById(R.id.publish_shortcut)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shortcutInsertViewModel.shortcutLiveData.observe(
            viewLifecycleOwner,
            Observer { shortcut ->
                shortcut?.let {
                    this.shortcut = shortcut
                    updateUI()
                }
            })
        shortcutInsertViewModel.entryLiveData.observe(
            viewLifecycleOwner,
            Observer { entry ->
                entry?.let {
                    this.entry = entry
                    updateUI()
                }
            })
    }
    override fun onStart() {
        super.onStart()
        val field1Watcher = object : TextWatcher {
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
                fillFields[0] = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) {
// This one too
            }
        }
        field1.addTextChangedListener(field1Watcher)
        editShortcutButton.setOnClickListener{
            callbacks?.onEditShortcutSelected(entry.id,shortcut.id)
        }
        publishShortcutButton.setOnClickListener {
            entry.writing+=shortcut.result
            callbacks?.onReturnEntrySelected(entry.id)
        }
    }
    private fun updateUI() {
        shortcutTitle.setText(shortcut.title)
        field1.setHint(shortcut.field1)
        field1.setText(fillFields[0])
        result.setText(shortcut.result)
    }
    override fun onStop() {
        super.onStop()
        shortcutInsertViewModel.saveEntry(entry)
    }
    companion object {
        fun newInstance(entryId:UUID, shortcutId: UUID): ShortcutInsertFragment {
            val args = Bundle().apply {
                putSerializable(ARG_ENTRY_ID,entryId)
                putSerializable(ARG_SHORTCUT_ID, shortcutId)
            }
            return ShortcutInsertFragment().apply {
                arguments = args
            }
        }
    }
    interface Callbacks {
        fun onEditShortcutSelected(entryId: UUID,shortcutId: UUID)
        fun onReturnEntrySelected(entryId: UUID)
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