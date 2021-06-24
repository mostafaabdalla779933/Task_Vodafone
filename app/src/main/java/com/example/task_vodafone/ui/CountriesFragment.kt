package com.example.task_vodafone.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.task_vodafone.CountryViewModel
import com.example.task_vodafone.databinding.FragmentCountriesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CountriesFragment : Fragment() {

    lateinit var binding: FragmentCountriesBinding
    lateinit var viewModel: CountryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Log.i("main", "onCreateView: ")
        binding = FragmentCountriesBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[CountryViewModel::class.java]


        viewModel.getCountries().observe(viewLifecycleOwner){

            //Log.i("main", "onCreateView: "+it)
        }

        return binding.root
    }
}