package com.yasinsenel.izmireczaneuygulamasi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yasinsenel.izmireczaneuygulamasi.databinding.FragmentSecondBinding
import kotlinx.android.synthetic.main.fragment_view_pager.*


class SecondFragment : Fragment() {
    private lateinit var binding:FragmentSecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater,container,false)
        val view = binding.root
        val pager = activity?.viewPager2
        binding.apply {
            buttonSkip.setOnClickListener {
                pager?.currentItem = 2
            }
            buttonBack.setOnClickListener {
                pager?.currentItem = 0
            }
        }
        return view
    }
}