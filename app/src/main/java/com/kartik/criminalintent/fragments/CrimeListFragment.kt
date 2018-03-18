package com.kartik.criminalintent.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.TextView


import com.kartik.criminalintent.CrimePagerActivity
import com.kartik.criminalintent.R

import com.kartik.criminalintent.dataClass.Crime
import com.kartik.criminalintent.dataClass.CrimeLab

class CrimeListFragment : Fragment() {
    private lateinit var mCrimeRecyclerView: RecyclerView
    private var subtitlesVisible = false
    private var mAdapter: CrimeAdapter? = null
    private var lastUpdatedPosition = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null)
            subtitlesVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)

        mCrimeRecyclerView = view.findViewById(R.id.crimeRecyclerView)
        mCrimeRecyclerView.layoutManager = LinearLayoutManager(this!!.activity)
        updateUI()

        return view
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private fun updateUI() {
        updateSubtitle()
        val crimeLab = CrimeLab.get(context)
        val crimes = crimeLab.crimes

        if (mAdapter == null) {
            mAdapter = CrimeAdapter(crimes)
            mCrimeRecyclerView.adapter = mAdapter
        } else {
            if (lastUpdatedPosition > -1) {
                mAdapter?.notifyItemChanged(lastUpdatedPosition)
                lastUpdatedPosition = -1
            } else
                mAdapter?.notifyDataSetChanged()
        }
    }

    private inner class CrimeAdapter(private val mCrimes: List<Crime>) : RecyclerView.Adapter<CrimeHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val layoutInflater = LayoutInflater.from(activity)
            return CrimeHolder(layoutInflater, parent)
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = mCrimes[position]
            holder.bind(crime)
        }

        override fun getItemCount(): Int {
            return mCrimes.size
        }
    }

    private inner class CrimeHolder(inflater: LayoutInflater, parent: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_crime, parent, false)), View.OnClickListener {

        private lateinit var singleCrime: Crime
        private val titleTV: TextView = itemView.findViewById(R.id.crime_title)
        private val dateTV: TextView = itemView.findViewById(R.id.crime_date)
        private val crimeSolvedIV: ImageView = itemView.findViewById(R.id.crime_solved)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(crime: Crime) {
            singleCrime = crime
            titleTV.text = singleCrime.title
            dateTV.text = singleCrime.date.toString()
            crimeSolvedIV.visibility = when (singleCrime.solved) {
                true -> View.VISIBLE
                false -> View.GONE
            }

        }

        override fun onClick(view: View) {
            lastUpdatedPosition = this@CrimeHolder.adapterPosition

            val intent = CrimePagerActivity.newIntent(activity!!, singleCrime.id)
            startActivity(intent)
        }
    }

    private fun updateSubtitle() {
        val crimeLab = CrimeLab.get(activity)
        val crimeCount = crimeLab.crimes.size
        var subtitle: String? = getString(R.string.subtitle_format, crimeCount)
        if (!subtitlesVisible) {
            subtitle = null
        }

        val activity = activity as AppCompatActivity
        activity.supportActionBar?.title = subtitle
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.fragment_crime_list, menu)
        val subtitleItem = menu?.findItem(R.menu.fragment_crime_list)
        if (subtitlesVisible) {
            subtitleItem?.setTitle(R.string.hide_subtitle)
        } else {
            subtitleItem?.setTitle(R.string.show_subtitle)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.new_crime -> {
            val crime = Crime()
            CrimeLab.get(activity).addCrime(crime)
            val intent = CrimePagerActivity.newIntent(activity!!, crime.id)
            startActivity(intent)
            true
        }
        R.id.show_subtitle -> {
            subtitlesVisible = !subtitlesVisible
            activity?.invalidateOptionsMenu()
            updateSubtitle()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, subtitlesVisible)
    }

    companion object {
        private const val SAVED_SUBTITLE_VISIBLE = "subtitle"
    }
}

/*If no crime in recylcer view show some text which will help user to add more crimes to it
* If crime is added then change accordingly*/

/*Plural string resource pg 268 for 1 crime and 2.. crimes*/

/*Delete crimes by going in CrimeFragment or use long press to delete it from zoo app*/