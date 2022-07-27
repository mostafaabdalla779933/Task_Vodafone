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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.entity.AirLineEntity
import com.example.task_vodafone.R
import com.example.task_vodafone.databinding.FragmentAirlinesBinding
import com.example.task_vodafone.ui.MainActivity
import com.example.task_vodafone.ui.airlines.AirLineViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AirlinesFragment : Fragment() ,Communicate{

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
           findNavController().navigate(R.id.action_airlinesFragment_to_detialsFragment,bundle)
        }
        binding.rv.apply {
            adapter =myAdapter
        }
        if (viewModel.airlineListFilter.isEmpty())
             viewModel.getAirLines()

        lifecycleScope.launch {
            viewModel.airlineList.collect { myAdapter.submitList(it) }
        }


        addListener()


        return binding.root
    }

    private fun addListener(){

        binding.etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.filter(s.toString().trim())
            }
            override fun afterTextChanged(s: Editable?) {}
        })


        (requireActivity() as MainActivity).binding.faBtn.setOnClickListener {
            try {
                AddBottomSheet().show(childFragmentManager, "tag")
            }catch (e:Exception){ }
        }

    }

    override fun onItemSelect(airLineEntity: AirLineEntity) {
        viewModel.addItem(airLineEntity)
    }
    companion object{
        const val CITY = "city"
    }
}