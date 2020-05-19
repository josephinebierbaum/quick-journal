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
    private lateinit var field2: EditText
    private lateinit var field3: EditText
    private lateinit var field4: EditText
    private lateinit var field5: EditText
    private lateinit var field6: EditText
    private lateinit var result: TextView
    private lateinit var fillFields: Array<String>
    private lateinit var previewButton: Button
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
        field2 = view.findViewById(R.id.field2)
        field3 = view.findViewById(R.id.field3)
        field4 = view.findViewById(R.id.field4)
        field5 = view.findViewById(R.id.field5)
        field6 = view.findViewById(R.id.field6)
        result = view.findViewById(R.id.result)
        previewButton = view.findViewById(R.id.preview_result)
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
        val field2Watcher = object : TextWatcher {
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
                fillFields[1] = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) {
// This one too
            }
        }
        val field3Watcher = object : TextWatcher {
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
                fillFields[2] = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) {
// This one too
            }
        }
        val field4Watcher = object : TextWatcher {
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
                fillFields[3] = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) {
// This one too
            }
        }
        val field5Watcher = object : TextWatcher {
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
                fillFields[4] = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) {
// This one too
            }
        }
        val field6Watcher = object : TextWatcher {
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
                fillFields[5] = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) {
// This one too
            }
        }
        field1.addTextChangedListener(field1Watcher)
        field2.addTextChangedListener(field2Watcher)
        field3.addTextChangedListener(field3Watcher)
        field4.addTextChangedListener(field4Watcher)
        field5.addTextChangedListener(field5Watcher)
        field6.addTextChangedListener(field6Watcher)
        editShortcutButton.setOnClickListener{
            callbacks?.onEditShortcutSelected(entry.id,shortcut.id)
        }
        previewButton.setOnClickListener{
            var resultText = shortcut.result
            Log.d(TAG, "Result text: $resultText")
            if (resultText.contains("/1/")){
                Log.d(TAG, "Contains /1/")
                resultText = resultText.replace("/1/","${fillFields[0]}")
            }
            if (resultText.contains("/2/")){
                Log.d(TAG, "Contains /2/")
                resultText = resultText.replace("/2/","${fillFields[1]}")
            }
            if (resultText.contains("/3/")){
                Log.d(TAG, "Contains /3/")
                resultText = resultText.replace("/3/","${fillFields[2]}")
            }
            if (resultText.contains("/4/")){
                Log.d(TAG, "Contains /4/")
                resultText = resultText.replace("/4/","${fillFields[3]}")
            }
            if (resultText.contains("/5/")){
                Log.d(TAG, "Contains /5/")
                resultText = resultText.replace("/5/","${fillFields[4]}")
            }
            if (resultText.contains("/6/")){
                Log.d(TAG, "Contains /6/")
                resultText = resultText.replace("/6/","${fillFields[5]}")
            }
            Log.d(TAG, "Result text: $resultText")
            result.setText(resultText)
        }
        publishShortcutButton.setOnClickListener {
            entry.writing+=result.getText()
            callbacks?.onReturnEntrySelected(entry.id)
        }
    }
    private fun updateUI() {
        shortcutTitle.setText(shortcut.title)
        field1.setHint(shortcut.field1)
        field1.setText(fillFields[0])
        field2.setHint(shortcut.field2)
        field2.setText(fillFields[1])
        field3.setHint(shortcut.field3)
        field3.setText(fillFields[2])
        field4.setHint(shortcut.field4)
        field4.setText(fillFields[3])
        field5.setHint(shortcut.field5)
        field5.setText(fillFields[4])
        field6.setHint(shortcut.field6)
        field6.setText(fillFields[5])
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