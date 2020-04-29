package com.bignerdranch.android.quickjournal

import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.NoSuchElementException

private const val TAG = "JournalListFragment"

class JournalListFragment: Fragment(){
    private lateinit var journalRecyclerView:RecyclerView
    private lateinit var sundayButton:TextView
    private lateinit var mondayButton:TextView
    private lateinit var tuesdayButton:TextView
    private lateinit var wednesdayButton:TextView
    private lateinit var thursdayButton:TextView
    private lateinit var fridayButton:TextView
    private lateinit var saturdayButton:TextView
    private lateinit var sunDayNum:TextView
    private lateinit var monDayNum:TextView
    private lateinit var tueDayNum:TextView
    private lateinit var wedDayNum:TextView
    private lateinit var thuDayNum:TextView
    private lateinit var friDayNum:TextView
    private lateinit var satDayNum:TextView
    private lateinit var prevWeekButton:ImageButton
    private lateinit var nextWeekButton:ImageButton
    private lateinit var currentMonth: TextView
    private lateinit var createEntryButton: ImageButton
    private var adapter: EntryAdapter? = EntryAdapter(emptyList())
    //private var currentDayViewing = ""+DateFormat.format("EEEE, LLLL dd, yyyy",Date())
    private var currentDayViewing = Date()
    private val journalListViewModel:JournalListViewModel by lazy{
        ViewModelProviders.of(this).get(JournalListViewModel::class.java)
    }
    interface Callbacks {
        fun onEntrySelected(entryId: UUID)
        fun onEditEntrySelected(entryId: UUID)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }
    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }
    private var callbacks: Callbacks? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_page_fragment, container, false)
        sundayButton = view.findViewById(R.id.sunday)
        mondayButton = view.findViewById(R.id.monday)
        tuesdayButton = view.findViewById(R.id.tuesday)
        wednesdayButton = view.findViewById(R.id.wednesday)
        thursdayButton = view.findViewById(R.id.thursday)
        fridayButton = view.findViewById(R.id.friday)
        saturdayButton = view.findViewById(R.id.saturday)
        sunDayNum = view.findViewById(R.id.sunday_day)
        monDayNum = view.findViewById(R.id.monday_day)
        tueDayNum = view.findViewById(R.id.tuesday_day)
        wedDayNum = view.findViewById(R.id.wednesday_day)
        thuDayNum = view.findViewById(R.id.thursday_day)
        friDayNum = view.findViewById(R.id.friday_day)
        satDayNum = view.findViewById(R.id.saturday_day)
        prevWeekButton = view.findViewById(R.id.previous_week)
        nextWeekButton = view.findViewById(R.id.next_week)
        currentMonth = view.findViewById(R.id.current_month)
        createEntryButton = view.findViewById(R.id.create_entry)
        journalRecyclerView =
            view.findViewById(R.id.fragment_journal_container) as RecyclerView
        journalRecyclerView.layoutManager = LinearLayoutManager(context)
        journalRecyclerView.adapter = adapter
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        journalListViewModel.entryListLiveData.observe(
            viewLifecycleOwner,
            Observer { entries ->
                entries?.let {
                    Log.i(TAG, "Got entries ${entries.size}")
                    updateUI(entries)
                }
            })
    }
    override fun onStart(){
        super.onStart()
        sundayButton.setOnClickListener{
            currentDayViewing = determineDate(currentDayViewing, 1)
            journalListViewModel.entryListLiveData.observe(
                viewLifecycleOwner,
                Observer { entries ->
                    entries?.let {
                        Log.i(TAG, "Got entries ${entries.size}")
                        updateUI(entries)
                    }
                })
        }
        mondayButton.setOnClickListener{
            currentDayViewing = determineDate(currentDayViewing, 2)
            journalListViewModel.entryListLiveData.observe(
                viewLifecycleOwner,
                Observer { entries ->
                    entries?.let {
                        Log.i(TAG, "Got entries ${entries.size}")
                        updateUI(entries)
                    }
                })
        }
        tuesdayButton.setOnClickListener{
            currentDayViewing = determineDate(currentDayViewing, 3)
            journalListViewModel.entryListLiveData.observe(
                viewLifecycleOwner,
                Observer { entries ->
                    entries?.let {
                        Log.i(TAG, "Got entries ${entries.size}")
                        updateUI(entries)
                    }
                })
        }
        wednesdayButton.setOnClickListener{
            currentDayViewing = determineDate(currentDayViewing, 4)
            journalListViewModel.entryListLiveData.observe(
                viewLifecycleOwner,
                Observer { entries ->
                    entries?.let {
                        Log.i(TAG, "Got entries ${entries.size}")
                        updateUI(entries)
                    }
                })
        }
        thursdayButton.setOnClickListener{
            currentDayViewing = determineDate(currentDayViewing, 5)
            journalListViewModel.entryListLiveData.observe(
                viewLifecycleOwner,
                Observer { entries ->
                    entries?.let {
                        Log.i(TAG, "Got entries ${entries.size}")
                        updateUI(entries)
                    }
                })
        }
        fridayButton.setOnClickListener{
            currentDayViewing = determineDate(currentDayViewing, 6)
            journalListViewModel.entryListLiveData.observe(
                viewLifecycleOwner,
                Observer { entries ->
                    entries?.let {
                        Log.i(TAG, "Got entries ${entries.size}")
                        updateUI(entries)
                    }
                })
        }
        saturdayButton.setOnClickListener{
            currentDayViewing = determineDate(currentDayViewing, 7)
            journalListViewModel.entryListLiveData.observe(
                viewLifecycleOwner,
                Observer { entries ->
                    entries?.let {
                        Log.i(TAG, "Got entries ${entries.size}")
                        updateUI(entries)
                    }
                })
        }
        prevWeekButton.setOnClickListener{
            currentDayViewing = determineDate(currentDayViewing, 0, false)
            journalListViewModel.entryListLiveData.observe(
                viewLifecycleOwner,
                Observer { entries ->
                    entries?.let {
                        Log.i(TAG, "Got entries ${entries.size}")
                        updateUI(entries)
                    }
                })
        }
        nextWeekButton.setOnClickListener{
            currentDayViewing = determineDate(currentDayViewing, 0, true)
            journalListViewModel.entryListLiveData.observe(
                viewLifecycleOwner,
                Observer { entries ->
                    entries?.let {
                        Log.i(TAG, "Got entries ${entries.size}")
                        updateUI(entries)
                    }
                })
        }
        createEntryButton.setOnClickListener{
            val entry = JournalEntry()
            journalListViewModel.addEntry(entry)
            callbacks?.onEditEntrySelected(entry.id)
        }
        //possibly add updateUI here
    }
    //dir is direction of movement, true for forward and false for backward
    //remember for Calendar.DAY_OF_WEEK, starts with Sunday at 1
    private fun determineDate(currentDate:Date, change:Int, dir:Boolean = true):Date{
        var cal = Calendar.getInstance()
        cal.time = currentDate
        if (change == 0){
            if (dir)
                cal.add(Calendar.DATE, 7)
            else
                cal.add(Calendar.DATE, -7)
        }
        else{
            var numDayChange = change - cal.get(Calendar.DAY_OF_WEEK)
            cal.add(Calendar.DATE, numDayChange)
        }
        var logmes = DateFormat.format("EEEE, LLLL dd, yyyy",cal.time)
        Log.d(TAG, "Returning $logmes")
        return cal.time
    }
    //once app starts, uses this version of updateUI to update
    private fun updateUI(){
        val entries = journalListViewModel.entries
        var index = -1
        val dayEntries:List<JournalEntry>
        try{
            var indexToGet = entries.first{DateFormat.format("EEEE, LLLL dd, yyyy",it.date)== DateFormat.format("EEEE, LLLL dd, yyyy",currentDayViewing)}
            index = entries.indexOf(indexToGet)
        }
        catch(e: NoSuchElementException){
            index = -1
        }
        if (index != -1)
            dayEntries = entries.subList(index,entries.lastIndex).takeWhile {DateFormat.format("EEEE, LLLL dd, yyyy",it.date)== DateFormat.format("EEEE, LLLL dd, yyyy",currentDayViewing)}
        else
            dayEntries = emptyList()
        Log.d(TAG, "Returning $dayEntries.size")
        adapter = EntryAdapter(dayEntries)
        journalRecyclerView.adapter = adapter
    }
    private fun updateUI(entries:List<JournalEntry>) {
        var index = -1
        val dayEntries:List<JournalEntry>
        try{
            var indexToGet = entries.first{DateFormat.format("EEEE, LLLL dd, yyyy",it.date)== DateFormat.format("EEEE, LLLL dd, yyyy",currentDayViewing)}
            index = entries.indexOf(indexToGet)
        }
        catch(e: NoSuchElementException){
            index = -1
        }
        if (index != -1)
            dayEntries = entries.subList(index,entries.lastIndex).takeWhile {DateFormat.format("EEEE, LLLL dd, yyyy",it.date)== DateFormat.format("EEEE, LLLL dd, yyyy",currentDayViewing)}
        else
            dayEntries = emptyList()
        adapter = EntryAdapter(dayEntries)
        journalRecyclerView.adapter = adapter
    }
    companion object {
        fun newInstance(): JournalListFragment {
            return JournalListFragment()
        }
    }
    private inner class EntryHolder(view: View)
        : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var entry:JournalEntry
        private val titleTextView: TextView = itemView.findViewById(R.id.entry_title)
        private val previewTextView: TextView = itemView.findViewById(R.id.entry_writing_preview)
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(entry: JournalEntry) {
            this.entry = entry
            titleTextView.text = this.entry.title
            previewTextView.text = this.entry.writing
        }

        override fun onClick(v: View?) {
            //add code to go to edit_journal_fragment here
            callbacks?.onEntrySelected(entry.id)
        }
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
            holder.bind(entry)
        }
    }
}