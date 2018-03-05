package com.kartik.criminalintent

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import com.bignerdranch.android.criminalintent.CrimeLab
import com.kartik.criminalintent.dataClass.Crime
import com.kartik.criminalintent.fragments.CrimeFragment
import kotlinx.android.synthetic.main.activity_crime_pager.*
import java.util.*

class CrimePagerActivity : AppCompatActivity() {
    private lateinit var crimes: List<Crime>
//    It is not required as can use Kotlin Extension
//    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime_pager)
        //FragmentStatePagerAdapter will be used for simplicity and fragment use
        //It will take care of many details
        //FragmentStatePagerAdapter
        // will boil down the conversation to two simple methods: getCount() and
        //getItem(int). When your getItem(int) method is called for a position in your array of crimes, it
        //will return a CrimeFragment configured to display the crime at that position.
        val crimeID=intent.getSerializableExtra(EXTRA_CRIME_ID) as UUID
        crimes = CrimeLab[this@CrimePagerActivity].crimes
        val fm=supportFragmentManager
        viewPager.adapter= object : FragmentStatePagerAdapter(fm) {
            override fun getItem(position: Int): Fragment {
                val crime = crimes[position]
                return CrimeFragment.newInstance(crime.id)
            }

            override fun getCount(): Int {
                return crimes.size
            }
        }
        for (i in 0..crimes.size) {
            if (crimes[i].id == crimeID) {
                viewPager.currentItem = i
                break
            }
        }
    }
    companion object {
        private const val EXTRA_CRIME_ID="crimeID from CrimePagerActivity"

        fun newIntent(context: Context, crimeID: UUID):Intent {
            val intent=Intent(context,CrimePagerActivity::class.java)
            intent.putExtra(EXTRA_CRIME_ID,crimeID)
            return intent
        }
    }
}
/*Difference between FragmentStatePagerAdapter and FragmentPagerAdapter
* FragmentStatePagerAdapter uses remove method for fragment internally
* but FragmentPagerAdapter uses detach method
* So FragmentStatePagerAdapter doesn't have fragement when not in use but FragmentPagerAdapter will have fragments is memory
*
* So in short if less pages uses FragmentPagerAdapter it will take more memory but works good as no need to create Fragement again
* But if too many fragments use FragmentStatePagerAdapter it will take less memory and create fragment as required*/

/*When dealing with two fragments hosted by the same activity, you can borrow
Fragment.onActivityResult(…) and call it directly on the target fragment to pass back data. It has
exactly what you need:
• a request code that matches the code passed into setTargetFragment(…) to tell the target what is
returning the result
• a result code to determine what action to take
• an Intent that can have extra data*/