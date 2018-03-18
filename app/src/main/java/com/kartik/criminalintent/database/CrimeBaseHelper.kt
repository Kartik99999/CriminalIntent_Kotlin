package com.kartik.criminalintent.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.kartik.criminalintent.database.CrimeDbSchema.CrimeTable as CrimeTable
import com.kartik.criminalintent.database.CrimeDbSchema.CrimeTable.Cols as Cols
/**
 * Created by Kartik Sethi on 06-03-2018.
 */
class CrimeBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table ${CrimeTable.NAME} (_id integer primary key autoincrement, " +
                "${Cols.UUID}, ${Cols.TITLE}, ${Cols.DATE}, ${Cols.SOLVED})")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        const val VERSION = 1
        const val DATABASE_NAME = "crimeBase.db"
    }
}