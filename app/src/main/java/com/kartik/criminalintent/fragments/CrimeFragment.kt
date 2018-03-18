package com.kartik.criminalintent.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kartik.criminalintent.CriminalActivity
import kotlinx.android.synthetic.main.fragment_crime.*
import com.kartik.criminalintent.R
import com.kartik.criminalintent.dataClass.Crime
import com.kartik.criminalintent.dataClass.CrimeLab
import java.util.*

class CrimeFragment : Fragment() {

    private lateinit var crime: Crime

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val crimeID = arguments?.getSerializable(ARG_CRIME_ID)
        crime = CrimeLab.get(context!!).getCrime(crimeID as UUID)!!
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crime, container, false)
    }

    override fun onPause() {
        super.onPause()
        CrimeLab.get(activity).updateCrime(crime)
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

        updateDate()
        //crimeDateButton.isEnabled = false
        //now button will be implemented to set date
        crimeDateButton.setOnClickListener {
            val manager = fragmentManager
            val dialog = DatePickerFragment.newInstance(crime.date)
//            With activities, you call startActivityForResult(…), and the ActivityManager keeps track of the
//            parent-child activity relationship. When the child activity dies, the ActivityManager knows which
//            activity should receive the result.

            /*You can create a similar connection by making CrimeFragment the target fragment of
            DatePickerFragment. This connection is automatically reestablished after both CrimeFragment and
            DatePickerFragment are destroyed and re-created by the OS. To create this relationship, you call the
            following Fragment method:
                public void setTargetFragment(Fragment fragment, int requestCode)*/
            //The target fragment can use the request code later to identify which fragment is reporting back.

            /*The FragmentManager keeps track of the target fragment and request code. You can retrieve them by
            calling getTargetFragment() and getTargetRequestCode() on the fragment that has set the target.*/
            dialog.setTargetFragment(this@CrimeFragment, REQUEST_DATE)
            dialog.show(manager, DIALOG_DATE)
        }

        crimeSolvedCheckBox.isChecked = crime.solved
        crimeSolvedCheckBox.setOnCheckedChangeListener { compoundButton, b -> crime.solved = b }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode!= Activity.RESULT_OK)
            return
        if (requestCode == REQUEST_DATE) {
            val date=data?.getSerializableExtra(DatePickerFragment.EXTRA_DATE) as Date
            crime.date=date
            updateDate()

        }
    }

    private fun updateDate() {
        crimeDateButton.text = crime.date.toString()
    }

    companion object {
        private const val REQUEST_DATE = 1
        private const val ARG_CRIME_ID = "crime_id from CrimeFragment"
        private const val DIALOG_DATE = "DialogDate"
        fun newInstance(crimeID: UUID): CrimeFragment {
            val args = Bundle()
            args.putSerializable(ARG_CRIME_ID, crimeID)

            val fragment = CrimeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}