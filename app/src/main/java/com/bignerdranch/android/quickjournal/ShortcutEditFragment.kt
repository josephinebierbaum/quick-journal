package com.bignerdranch.android.quickjournal

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class ShortcutEditFragment: Fragment() {
    private lateinit var shortcut: Shortcut
    private lateinit var shortcutTitle: EditText
    private lateinit var field1: EditText
    private lateinit var addFieldButton: ImageButton
    private lateinit var result: EditText
    private lateinit var publishShortcutButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shortcut = Shortcut()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.edit_shortcut_fragment, container, false)
        shortcutTitle = view.findViewById(R.id.shortcut_title)
        field1 = view.findViewById(R.id.field1)
        addFieldButton = view.findViewById(R.id.add_field)
        result = view.findViewById(R.id.result)
        publishShortcutButton = view.findViewById(R.id.publish_shortcut)
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
                shortcut.title = sequence.toString()
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
        result.addTextChangedListener(resultWatcher)
        publishShortcutButton.setOnClickListener {
            //add code to save later
        }
    }
}