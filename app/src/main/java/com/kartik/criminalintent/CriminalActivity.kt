package com.kartik.criminalintent

import android.content.Context
import android.content.Intent

import android.support.v4.app.Fragment
import com.kartik.criminalintent.fragments.CrimeFragment
import java.util.*

class CriminalActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment? {
        val crimeID=intent.getSerializableExtra(EXTRA_CRIME_ID) as UUID
        return CrimeFragment.newInstance(crimeID)
    }

    companion object {
        private const val EXTRA_CRIME_ID="crime_id from CriminalActivity"
        fun newIntent(context: Context, crimeID: UUID):Intent {
            val intent=Intent(context,CriminalActivity::class.java)
            intent.putExtra(EXTRA_CRIME_ID,crimeID)
            return intent
        }
    }

}
//Important design concept for Activities and Fragments
/*Hosting activities should know the specifics fo how to host their fragments
, but fragments should not have to know specifics about their activities */