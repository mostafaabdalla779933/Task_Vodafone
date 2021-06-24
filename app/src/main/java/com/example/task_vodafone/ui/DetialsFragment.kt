package com.example.task_vodafone.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.task_vodafone.R
import com.example.task_vodafone.databinding.FragmentDetialsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetialsFragment : Fragment() {


    lateinit var binding : FragmentDetialsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentDetialsBinding.inflate(layoutInflater)

        return inflater.inflate(R.layout.fragment_detials, container, false)
    }


}