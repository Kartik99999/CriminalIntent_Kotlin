package com.kartik.criminalintent

import android.support.v4.app.Fragment
import com.kartik.criminalintent.fragments.CrimeListFragment

class CrimeListActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment? {
        return CrimeListFragment()
    }
}

