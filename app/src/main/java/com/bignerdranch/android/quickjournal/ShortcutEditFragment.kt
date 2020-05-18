package com.bignerdranch.android.quickjournal

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
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import java.util.UUID

private const val ARG_SHORTCUT_ID = "shortcut_id"
private const val ARG_ENTRY_ID = "entry_id"
private const val TAG = "ShortcutEditFragment"
class ShortcutEditFragment: Fragment() {
    private lateinit var entryId: UUID
    private lateinit var shortcut: Shortcut
    private lateinit var shortcutTitle: EditText
    private lateinit var field1: EditText
    private lateinit var field2: EditText
    private lateinit var field3: EditText
    private lateinit var field4: EditText
    private lateinit var field5: EditText
    private lateinit var field6: EditText
    private lateinit var result: EditText
    private lateinit var deleteShortcutButton: Button
    private val shortcutEditViewModel: ShortcutEditViewModel by lazy {
        ViewModelProviders.of(this).get(ShortcutEditViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shortcut = Shortcut()
        val shortcutId: UUID = arguments?.getSerializable(ARG_SHORTCUT_ID) as UUID
        shortcutEditViewModel.loadShortcut(shortcutId)
        entryId = arguments?.getSerializable(ARG_ENTRY_ID) as UUID
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.edit_shortcut_fragment, container, false)
        shortcutTitle = view.findViewById(R.id.shortcut_title)
        field1 = view.findViewById(R.id.field1)
        field2 = view.findViewById(R.id.field2)
        field3 = view.findViewById(R.id.field3)
        field4 = view.findViewById(R.id.field4)
        field5 = view.findViewById(R.id.field5)
        field6 = view.findViewById(R.id.field6)
        result = view.findViewById(R.id.result)
        deleteShortcutButton = view.findViewById(R.id.delete_shortcut)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shortcutEditViewModel.shortcutLiveData.observe(
            viewLifecycleOwner,
            Observer { shortcut ->
                shortcut?.let {
                    this.shortcut = shortcut
                    updateUI()
                }
            })
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
                shortcut.title = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) {
// This one too
            }
        }
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
                shortcut.field1 = sequence.toString()
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
                shortcut.field2 = sequence.toString()
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
                shortcut.field3 = sequence.toString()
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
                shortcut.field4 = sequence.toString()
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
                shortcut.field5 = sequence.toString()
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
                shortcut.field6 = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) {
// This one too
            }
        }
        val resultWatcher = object : TextWatcher {
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
                shortcut.result = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) {
// This one too
            }
        }
        shortcutTitle.addTextChangedListener(titleWatcher)
        field1.addTextChangedListener(field1Watcher)
        field2.addTextChangedListener(field2Watcher)
        field3.addTextChangedListener(field3Watcher)
        field4.addTextChangedListener(field4Watcher)
        field5.addTextChangedListener(field5Watcher)
        field6.addTextChangedListener(field6Watcher)
        result.addTextChangedListener(resultWatcher)
        deleteShortcutButton.setOnClickListener {
            shortcutEditViewModel.deleteShortcut(shortcut)
        }
    }
    override fun onStop() {
        super.onStop()
        shortcutEditViewModel.saveShortcut(shortcut)
    }
    private fun updateUI() {
        shortcutTitle.setText(shortcut.title)
        field1.setText(shortcut.field1)
        field2.setText(shortcut.field2)
        field3.setText(shortcut.field3)
        field4.setText(shortcut.field4)
        field5.setText(shortcut.field5)
        field6.setText(shortcut.field6)
        result.setText(shortcut.result)

    }
    companion object {
        fun newInstance(entryId:UUID, shortcutId: UUID): ShortcutEditFragment {
            val args = Bundle().apply {
                putSerializable(ARG_ENTRY_ID, entryId)
                putSerializable(ARG_SHORTCUT_ID, shortcutId)
            }
            return ShortcutEditFragment().apply {
                arguments = args
            }
        }
    }
}