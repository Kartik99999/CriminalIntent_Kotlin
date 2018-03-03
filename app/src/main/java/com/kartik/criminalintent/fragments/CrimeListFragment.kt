package com.kartik.criminalintent.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import android.widget.Toast
import com.bignerdranch.android.criminalintent.CrimeLab
import com.kartik.criminalintent.CriminalActivity
import com.kartik.criminalintent.R

import com.kartik.criminalintent.dataClass.Crime

class CrimeListFragment : Fragment() {
    private lateinit var mCrimeRecyclerView: RecyclerView
    private lateinit var mAdapter: CrimeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)

        mCrimeRecyclerView = view.findViewById(R.id.crimeRecyclerView)
        mCrimeRecyclerView.layoutManager = LinearLayoutManager(activity)
        updateUI()

        return view
    }

    private fun updateUI() {
        val crimeLab = CrimeLab[context!!]
        val crimes = crimeLab.crimes

        mAdapter = CrimeAdapter(crimes)
        mCrimeRecyclerView.adapter = mAdapter
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
            crimeSolvedIV.visibility=when (singleCrime.solved) {
                true->View.VISIBLE
                false->View.GONE
            }

        }

        override fun onClick(view: View) {
            val intent=CriminalActivity.newIntent(activity!!,singleCrime.id)
            startActivity(intent)
        }
    }
}