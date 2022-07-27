package rs.raf.jun.uros_nikolic_2619rn.presentation.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.fragments.DiscoverFragment
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.fragments.PortfolioFragment
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.fragments.ProfileFragment

class MainPagerAdapter(
    fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val itemCount = 3

    companion object{
        const val FRAGMENT1 = 0
        const val FRAGMENT2 = 1
        const val FRAGMENT3 = 2
    }

    override fun getCount(): Int {
        return itemCount
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            FRAGMENT1 -> DiscoverFragment()
            FRAGMENT2 -> PortfolioFragment()
            else -> ProfileFragment()
        }
    }

}