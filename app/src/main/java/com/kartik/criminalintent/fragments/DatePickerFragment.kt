package com.kartik.criminalintent.fragments


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kartik.criminalintent.R


/**
 * A simple [Fragment] subclass.
 */
class DatePickerFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity).setTitle(R.string.date_picker_title).setPositiveButton(android.R.string.ok, null)
                .create()
    }

    companion object {

    }
}