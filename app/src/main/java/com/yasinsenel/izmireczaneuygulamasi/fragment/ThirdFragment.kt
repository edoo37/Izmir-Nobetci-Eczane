package com.yasinsenel.izmireczaneuygulamasi.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.yasinsenel.izmireczaneuygulamasi.R
import com.yasinsenel.izmireczaneuygulamasi.databinding.FragmentThirdBinding
import com.yasinsenel.izmireczaneuygulamasi.view.MainActivity
import kotlinx.android.synthetic.main.fragment_view_pager.*


class ThirdFragment : Fragment() {
    private lateinit var binding:FragmentThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThirdBinding.inflate(inflater,container,false)
        val view = binding.root
        val pager = activity?.viewPager2
        binding.apply {
            buttonFinish.setOnClickListener {
                findNavController().navigate(R.id.action_viewPagerFragment_to_mainActivity)
                onBoardingFinish()
            }
            buttonBack.setOnClickListener {
                pager?.currentItem = 1
            }
        }
        return view
    }
    private fun onBoardingFinish(){
        val sharedPref = requireActivity().getSharedPreferences("", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished",true)
        editor.apply()
    }
}