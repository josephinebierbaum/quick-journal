package com.bignerdranch.android.quickjournal

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import java.io.File
import java.util.UUID

private const val ARG_ENTRY_ID = "entry_id"
private const val TAG = "EntryEditFragment"
private const val REQUEST_PHOTO1 = 1
private const val REQUEST_PHOTO2 = 1
class EntryEditFragment: Fragment() {
    private lateinit var entry:JournalEntry
    private lateinit var entryTitle: EditText
    private lateinit var entryDate: TextView
    private lateinit var entryWriting: EditText
    private lateinit var entryLocation: TextView
    private lateinit var entryPhoto1: ImageView
    private lateinit var entryPhoto2: ImageView
    private lateinit var addShortcutButton: ImageButton
    private lateinit var deleteEntryButton: Button
    private lateinit var photo1File: File
    private lateinit var photo2File: File
    private lateinit var photo1Uri: Uri
    private lateinit var photo2Uri: Uri
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
        deleteEntryButton = view.findViewById(R.id.delete_entry)
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
        addShortcutButton.setOnClickListener{
            callbacks?.onShortcutListSelected(entry.id)
        }
        deleteEntryButton.setOnClickListener {
            entryEditViewModel.deleteEntry(entry)
        }
        entryPhoto1.apply {
            val packageManager: PackageManager = requireActivity().packageManager
            val captureImage = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val resolvedActivity: ResolveInfo? =
                packageManager.resolveActivity(captureImage,
                    PackageManager.MATCH_DEFAULT_ONLY)
            if (resolvedActivity == null) {
                isEnabled = false
            }
            setOnClickListener {
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, photo1Uri)
                val cameraActivities: List<ResolveInfo> =
                    packageManager.queryIntentActivities(captureImage,
                        PackageManager.MATCH_DEFAULT_ONLY)
                for (cameraActivity in cameraActivities) {
                    requireActivity().grantUriPermission(
                        cameraActivity.activityInfo.packageName,
                        photo1Uri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                }
                startActivityForResult(captureImage, REQUEST_PHOTO1)
            }
        }
        entryPhoto2.apply {
            val packageManager: PackageManager = requireActivity().packageManager
            val captureImage = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val resolvedActivity: ResolveInfo? =
                packageManager.resolveActivity(captureImage,
                    PackageManager.MATCH_DEFAULT_ONLY)
            if (resolvedActivity == null) {
                isEnabled = false
            }
            setOnClickListener {
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, photo2Uri)
                val cameraActivities: List<ResolveInfo> =
                    packageManager.queryIntentActivities(captureImage,
                        PackageManager.MATCH_DEFAULT_ONLY)
                for (cameraActivity in cameraActivities) {
                    requireActivity().grantUriPermission(
                        cameraActivity.activityInfo.packageName,
                        photo2Uri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                }
                startActivityForResult(captureImage, REQUEST_PHOTO2)
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        entryEditViewModel.entryLiveData.observe(
            viewLifecycleOwner,
            Observer { entry ->
                entry?.let {
                    this.entry = entry
                    photo1File = entryEditViewModel.getPhoto1File(entry)
                    photo2File = entryEditViewModel.getPhoto2File(entry)
                    photo1Uri = FileProvider.getUriForFile(requireActivity(),
                        "com.bignerdranch.android.quickjournal.fileprovider",
                        photo1File)
                    photo2Uri = FileProvider.getUriForFile(requireActivity(),
                        "com.bignerdranch.android.quickjournal.fileprovider",
                        photo2File)
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
        updatePhotoView()
    }
    private fun updatePhotoView() {
        if (photo1File.exists()) {
            val bitmap = getScaledBitmap(photo1File.path, requireActivity())
            entryPhoto1.setImageBitmap(bitmap)
        } else {
            entryPhoto1.setImageDrawable(null)
        }
        if (photo2File.exists()) {
            val bitmap = getScaledBitmap(photo2File.path, requireActivity())
            entryPhoto2.setImageBitmap(bitmap)
        } else {
            entryPhoto2.setImageDrawable(null)
        }
    }
    override fun onStop() {
        super.onStop()
        entryEditViewModel.saveEntry(entry)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when {
            resultCode != Activity.RESULT_OK -> return
            requestCode == REQUEST_PHOTO1 -> {
                requireActivity().revokeUriPermission(photo1Uri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                updatePhotoView()
            }
            requestCode == REQUEST_PHOTO2 -> {
                requireActivity().revokeUriPermission(photo2Uri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                updatePhotoView()
            }
        }
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
    interface Callbacks {
        fun onShortcutListSelected(entryId: UUID)
    }
    private var callbacks: Callbacks? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }
    override fun onDetach() {
        super.onDetach()
        requireActivity().revokeUriPermission(photo1Uri,
            Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        requireActivity().revokeUriPermission(photo2Uri,
            Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        callbacks = null
    }
}