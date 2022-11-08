package com.yasinsenel.izmireczaneuygulamasi.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.yasinsenel.izmireczaneuygulamasi.R
import com.yasinsenel.izmireczaneuygulamasi.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
    private lateinit var binding:FragmentSplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
        },3000)
        binding = FragmentSplashBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }
}