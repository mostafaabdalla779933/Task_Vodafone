package com.example.task_vodafone.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.entity.AirLineEntity
import com.example.task_vodafone.CountryViewModel
import com.example.task_vodafone.databinding.FragmentAirlinesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AirlinesFragment : Fragment() {

    lateinit var binding: FragmentAirlinesBinding
    lateinit var viewModel: CountryViewModel
    lateinit var myAdapter : AirLineAdapte

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        myAdapter = AirLineAdapte{

        }

        binding = FragmentAirlinesBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[CountryViewModel::class.java]


        binding.rv.apply {

            adapter =myAdapter
        }


        binding.etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable?) {}
        })

        viewModel.getAirLines().observe(viewLifecycleOwner){
            myAdapter.submitList(AirLineEntity.toEntityList(it.take(50)))
        }

        viewModel.getCountries().observe(viewLifecycleOwner){

           // myAdapter.submitList()
            //Log.i("main", "onCreateView: "+it)
        }

        binding.faBtn.setOnClickListener {



        }

        return binding.root
    }
}