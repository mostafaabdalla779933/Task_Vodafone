package com.example.task_vodafone.ui.detials

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.task_vodafone.databinding.FragmentDetialsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetialsFragment : Fragment() {


    lateinit var binding : FragmentDetialsBinding
    lateinit var url :String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetialsBinding.inflate(layoutInflater)

        arguments.let {
            binding.apply {
                tvName.text = it?.get("name").toString()
                tvCountry.text = it?.get("country").toString()
                tvSlogon.text =it?.get("slogon").toString()
                tvHead.text = it?.get("head").toString()
                url = it?.get("website").toString()
            }
        }

        binding.btnBack.setOnClickListener {
            Navigation.findNavController(this.requireView()).popBackStack()
        }

        binding.btnVisit.setOnClickListener {

            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$url"))
                startActivity(browserIntent)
            }catch (e: Exception){

            }
        }

        return binding.root
    }


}