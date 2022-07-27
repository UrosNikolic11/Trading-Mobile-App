package rs.raf.jun.uros_nikolic_2619rn.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import rs.raf.jun.uros_nikolic_2619rn.R
import rs.raf.jun.uros_nikolic_2619rn.databinding.FragmentMainBinding
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.adapters.MainPagerAdapter
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.adapters.MainPagerAdapter.Companion.FRAGMENT1

class MainFragment: Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        initViewPager()
        initNavigation()
    }

    private fun initViewPager(){
        viewPager = binding.bottomNavViewPager
        viewPager.adapter = MainPagerAdapter(childFragmentManager)
    }

    private fun initNavigation(){
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            if (item.itemId == R.id.navigation_1) {
                viewPager.setCurrentItem(MainPagerAdapter.FRAGMENT1, false)
            }else if(item.itemId == R.id.navigation_2){
                viewPager.setCurrentItem(MainPagerAdapter.FRAGMENT2, false)
            }else if(item.itemId == R.id.navigation_3){
                viewPager.setCurrentItem(MainPagerAdapter.FRAGMENT3, false)
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}