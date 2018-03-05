package com.bignerdranch.android.criminalintent

import android.content.Context

import java.util.ArrayList
import java.util.UUID

import com.kartik.criminalintent.dataClass.Crime

class CrimeLab private constructor(context: Context) {

    private val mCrimes= mutableListOf<Crime>()
//            : MutableList<Crime>

    val crimes: List<Crime>
        get() = mCrimes


    fun addCrime(crime: Crime) {
        mCrimes.add(crime)
    }
    fun getCrime(id: UUID): Crime? {
        for (crime in mCrimes) {
            if (crime.id == id) {
                return crime
            }
        }

        return null
    }

    companion object {
        private var sCrimeLab: CrimeLab? = null

        operator fun get(context: Context): CrimeLab {
            if (sCrimeLab == null) {
                sCrimeLab = CrimeLab(context)
            }

            return sCrimeLab as CrimeLab
        }
    }
}
