package com.example.mybank

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mybank.db.StartViewData
import com.example.mybank.fragments.StartFragment

class StartAdapter(
    fragment: FragmentActivity,
    private val startViewsList: MutableList<StartViewData>,
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return startViewsList.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = StartFragment()
        fragment.arguments = bundleOf("start" to startViewsList[position])
        return fragment
    }
}