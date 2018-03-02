package com.kartik.criminalintent.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_crime.*
import com.kartik.criminalintent.R
import com.kartik.criminalintent.dataClass.Crime

class CrimeFragment : Fragment() {

    private lateinit var crime: Crime

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        crime= Crime()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crime, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        crimeTitleEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                crime.title=p0.toString()
            }
        })

        crimeDateButton.text=crime.date.toString()
        crimeDateButton.isEnabled=false

        crimeSolvedCheckBox.setOnCheckedChangeListener { compoundButton, b -> crime.solved=b }
    }
//    companion object {
//
//    }
}