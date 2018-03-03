package com.kartik.criminalintent.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.criminalintent.CrimeLab
import com.kartik.criminalintent.CriminalActivity
import kotlinx.android.synthetic.main.fragment_crime.*
import com.kartik.criminalintent.R
import com.kartik.criminalintent.dataClass.Crime
import java.util.*

class CrimeFragment : Fragment() {

    private lateinit var crime: Crime

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val crimeID =arguments?.getSerializable(ARG_CRIME_ID)
        crime = CrimeLab[activity!!].getCrime(crimeID as UUID)!!
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crime, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        crimeTitleEditText.setText(crime.title)
        crimeTitleEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                crime.title = p0.toString()
            }
        })

        crimeDateButton.text = crime.date.toString()
        crimeDateButton.isEnabled = false

        crimeSolvedCheckBox.isChecked = crime.solved
        crimeSolvedCheckBox.setOnCheckedChangeListener { compoundButton, b -> crime.solved = b }
    }
    companion object {
        private const val ARG_CRIME_ID = "crime_id from CrimeFragment"
        fun newInstance(crimeID: UUID): CrimeFragment {
            val args = Bundle()
            args.putSerializable(ARG_CRIME_ID, crimeID)

            val fragment = CrimeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}