package com.kartik.criminalintent.fragments


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker

import com.kartik.criminalintent.R
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class DatePickerFragment : DialogFragment() {
    private lateinit var datePicker: DatePicker
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = arguments?.getSerializable(ARG_DATE) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_date, null)
        datePicker = view.findViewById(R.id.dialog_date_picker)
        datePicker.init(year,month,day,null)

        return AlertDialog.Builder(activity).setView(view).setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, null)
                .create()
    }

    companion object {
        private const val ARG_DATE = "date"
        public fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle()
            args.putSerializable(ARG_DATE, date)
            val fragment = DatePickerFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
/*What method will you use to send this intent to the target fragment? Oddly enough, you will have
DatePickerFragment pass it into CrimeFragment.onActivityResult(int, int, Intent)*/