package com.kartik.criminalintent.dataClass
import java.util.*

/**
 * Created by Kartik Sethi on 10-01-2018.
 */
class Crime {
    val id: UUID = UUID.randomUUID()
    var date= Date()
    var title = ""
    var solved = false
}