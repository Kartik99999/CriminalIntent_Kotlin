package com.bignerdranch.android.criminalintent

import android.content.Context

import java.util.ArrayList
import java.util.UUID

import com.kartik.criminalintent.dataClass.Crime

class CrimeLab private constructor(context: Context) {

    private val mCrimes: MutableList<Crime>

    val crimes: List<Crime>
        get() = mCrimes

    init {
        mCrimes = ArrayList()
        for (i in 0..99) {
            val crime = Crime()
            crime.title = "Crime #" + i
            crime.solved = i % 2 == 0
            mCrimes.add(crime)
        }
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
