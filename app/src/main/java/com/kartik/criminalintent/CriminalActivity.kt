package com.kartik.criminalintent

import android.support.v4.app.Fragment
import com.kartik.criminalintent.Fragments.CrimeFragment

class CriminalActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment? {
        return CrimeFragment()
    }

}
