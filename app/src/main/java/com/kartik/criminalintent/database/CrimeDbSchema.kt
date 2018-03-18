package com.kartik.criminalintent.database

import java.util.*

/**
 * Created by Kartik Sethi on 06-03-2018.
 */
class CrimeDbSchema {

    object CrimeTable {
        const val NAME="crimes"

        object Cols {
            const val UUID="uuid"
            const val TITLE="title"
            const val DATE="date"
            const val SOLVED="solved"
        }
    }
}