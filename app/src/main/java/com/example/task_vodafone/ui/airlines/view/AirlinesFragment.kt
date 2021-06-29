package com.example.task_vodafone.ui.airlines.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.task_vodafone.R
import com.example.task_vodafone.databinding.FragmentAirlinesBinding
import com.example.task_vodafone.ui.AirLineAdapte
import com.example.task_vodafone.ui.airlines.AirLineViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AirlinesFragment : Fragment() {

    lateinit var binding: FragmentAirlinesBinding
    lateinit var viewModel: AirLineViewModel
    lateinit var myAdapter : AirLineAdapte

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAirlinesBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[AirLineViewModel::class.java]

        myAdapter = AirLineAdapte{
            val bundle = bundleOf("name" to it.name,
                                        "id"   to it.id,
                                        "slogon" to it.slogan,
                                        "country" to it.country,
                                        "head" to it.headQuaters,
                                        "website" to it.website)
            Navigation.findNavController(this.requireView()).navigate(R.id.detialsFragment,bundle)
        }
        binding.rv.apply {
            adapter =myAdapter
        }
        viewModel.getAirLines()

        viewModel.airlineList.observe(viewLifecycleOwner){
            myAdapter.submitList(it)

        }


        binding.etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.filter(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.faBtn.setOnClickListener {
            AddBottomSheet{
                viewModel.addItem(it)
            }.show(parentFragmentManager, "tag")
        }

        return binding.root
    }
}