package com.bignerdranch.android.quickjournal

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "ShortcutListFragment"
class ShortcutListFragment : Fragment() {
    private lateinit var shortcutRecyclerView: RecyclerView
    private lateinit var createShortcutButton: ImageButton
    private var adapter: ShortcutAdapter? = ShortcutAdapter(emptyList())
    private val shortcutListViewModel: ShortcutListViewModel by lazy {
        ViewModelProviders.of(this).get(ShortcutListViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.view_shortcuts_fragment, container, false)
        shortcutRecyclerView =
            view.findViewById(R.id.fragment_shortcut_container) as RecyclerView
        shortcutRecyclerView.layoutManager = LinearLayoutManager(context)
        createShortcutButton = view.findViewById(R.id.create_shortcut)
        shortcutRecyclerView.adapter = adapter
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shortcutListViewModel.shortcutListLiveData.observe(
            viewLifecycleOwner,
            Observer { shortcuts ->
                shortcuts?.let {
                    Log.i(TAG, "Got shortcuts ${shortcuts.size}")
                    updateUI(shortcuts)
                }
            })
    }
    private fun updateUI(shortcuts:List<Shortcut>) {
        adapter = ShortcutAdapter(shortcuts)
        shortcutRecyclerView.adapter = adapter
    }
    private inner class ShortcutHolder(view: View)
        : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var shortcut:Shortcut
        private val titleTextView: TextView = itemView.findViewById(R.id.title)
        private val fieldTextView: TextView = itemView.findViewById(R.id.fields_preview)
        private val resultTextView: TextView = itemView.findViewById(R.id.result_preview)
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(shortcut:Shortcut){
            this.shortcut = shortcut
            titleTextView.text = this.shortcut.title
            fieldTextView.text = this.shortcut.fields.toString()
            resultTextView.text = this.shortcut.result
        }
        override fun onClick(v: View) {
            //change to go to fill out shortcut here
            Toast.makeText(context, "${shortcut.title} pressed!", Toast.LENGTH_SHORT)
                .show()
        }
    }
    private inner class ShortcutAdapter(var shortcuts: List<Shortcut>)
        : RecyclerView.Adapter<ShortcutHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : ShortcutHolder {
            val view = layoutInflater.inflate(R.layout.list_shortcut_fragment, parent, false)
            return ShortcutHolder(view)
        }
        override fun getItemCount() = shortcuts.size
        override fun onBindViewHolder(holder: ShortcutHolder, position: Int) {
            val shortcut = shortcuts[position]
            holder.bind(shortcut)
        }
    }
    companion object {
        fun newInstance(): ShortcutListFragment {
            return ShortcutListFragment()
        }
    }
}