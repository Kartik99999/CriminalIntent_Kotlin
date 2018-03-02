package com.kartik.criminalintent.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import android.widget.Toast
import com.bignerdranch.android.criminalintent.CrimeLab
import com.kartik.criminalintent.R

import com.kartik.criminalintent.dataClass.Crime

class CrimeListFragment : Fragment() {
    private lateinit var mCrimeRecyclerView: RecyclerView
    private lateinit var mAdapter: CrimeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)

        mCrimeRecyclerView= view.findViewById(R.id.crimeRecyclerView)
        mCrimeRecyclerView.layoutManager=LinearLayoutManager(activity)
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

        private var mCrime: Crime? = null

        private val mTitleTextView: TextView
        private val mDateTextView: TextView

        init {
            itemView.setOnClickListener(this)

            mTitleTextView = itemView.findViewById(R.id.crime_title)
            mDateTextView = itemView.findViewById(R.id.crime_date)
        }

        fun bind(crime: Crime) {
            mCrime = crime
            mTitleTextView.text = mCrime!!.title
            mDateTextView.text = mCrime!!.date.toString()
        }

        override fun onClick(view: View) {
            Toast.makeText(activity,
                    mCrime!!.title + " clicked!", Toast.LENGTH_SHORT)
                    .show()
        }
    }
}

/*package com.kartik.criminalintent.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kartik.criminalintent.R
import com.kartik.criminalintent.dataClass.Crime
import com.kartik.criminalintent.dataClass.CrimeLab
import kotlinx.android.synthetic.main.fragment_crime_list.*


class CrimeListFragment : Fragment() {

    private lateinit var adapter:CrimeAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        updateUI()
        return inflater.inflate(R.layout.fragment_crime_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        crimeRecyclerView.layoutManager = LinearLayoutManager(activity)

    }


    private fun updateUI() {
        val crimeLab=CrimeLab.get(activity?.applicationContext)
        val crimes:ArrayList<Crime> = crimeLab.crimes

        adapter=CrimeAdapter(crimes)
        crimeRecyclerView.adapter=adapter
    }

    private inner class CrimeHolder(inflater: LayoutInflater, parent: ViewGroup?) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.supporting_list_item_crime, parent, false))

    private inner class CrimeAdapter : RecyclerView.Adapter<CrimeHolder> {
        private lateinit var mCrimes:List<Crime>
        constructor(crimes: List<Crime>){
            mCrimes=crimes
        }
        override fun onBindViewHolder(holder: CrimeHolder?, position: Int) {

        }

        override fun getItemCount(): Int {
            return mCrimes.size
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CrimeHolder {
            val layoutInflater=LayoutInflater.from(activity)
            return CrimeHolder(layoutInflater,parent)
        }

    }

}
*/