package com.kartik.criminalintent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.kartik.criminalintent.fragments.CrimeFragment
import java.util.*

class CriminalActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment? {
        return CrimeFragment()
    }

    companion object {
        const val EXTRA_CRIME_ID="crime_id from CriminalActivity"
        fun newIntent(context: Context, crimeID: UUID):Intent {
            val intent=Intent(context,CriminalActivity::class.java)
            intent.putExtra(EXTRA_CRIME_ID,crimeID)
            return intent
        }
    }

}
