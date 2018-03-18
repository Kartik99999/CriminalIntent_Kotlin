package com.bignerdranch.android.criminalintent

import android.content.Context
import android.database.sqlite.SQLiteDatabase

import java.util.ArrayList
import java.util.UUID

import com.kartik.criminalintent.dataClass.Crime
import com.kartik.criminalintent.database.CrimeBaseHelper

class CrimeLab private constructor(var context: Context) {

    private val mCrimes = mutableListOf<Crime>()
private var database:SQLiteDatabase
    val crimes: List<Crime>
        get() = mCrimes

    init {
        context = context.applicationContext
        database=CrimeBaseHelper(context).writableDatabase
    }

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
