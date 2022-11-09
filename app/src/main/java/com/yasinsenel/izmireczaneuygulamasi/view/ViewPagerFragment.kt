package com.yasinsenel.izmireczaneuygulamasi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yasinsenel.izmireczaneuygulamasi.R
import com.yasinsenel.izmireczaneuygulamasi.adapter.ViewPagerAdapter
import com.yasinsenel.izmireczaneuygulamasi.databinding.FragmentViewPagerBinding
import com.yasinsenel.izmireczaneuygulamasi.fragment.FirstFragment
import com.yasinsenel.izmireczaneuygulamasi.fragment.SecondFragment
import com.yasinsenel.izmireczaneuygulamasi.fragment.ThirdFragment
import kotlinx.android.synthetic.main.fragment_view_pager.*


class ViewPagerFragment : Fragment() {
    private lateinit var binding : FragmentViewPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewPagerBinding.inflate(inflater,container,false)

        val fragmentList = arrayListOf<Fragment>(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )
        binding.apply {
            val adapter = ViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)
            viewPager2.adapter = adapter
        }
        return binding.root
    }
}