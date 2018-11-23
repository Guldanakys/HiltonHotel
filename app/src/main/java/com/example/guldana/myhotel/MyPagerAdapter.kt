package com.example.guldana.myhotel


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentManager

class MyPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                return NewsFragment()
            }
            1 -> {
                return RoomsFragment()
            }
            2 -> {
                return ProfileFragment()
            }
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "News"
            1 -> return "Rooms"
            2 -> return "Profile"
        }
        return null
    }

}