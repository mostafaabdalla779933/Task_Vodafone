package com.example.task_vodafone.ui.airlines.view

import android.app.Dialog
import android.content.pm.ActivityInfo
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.entity.AirLineEntity
import com.example.task_vodafone.R
import com.example.task_vodafone.databinding.FragmentAddAirlineBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import java.util.*


class AddBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding : FragmentAddAirlineBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddAirlineBinding.inflate(layoutInflater)
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val sheetContainer = requireView().parent as? ViewGroup ?: return
        sheetContainer.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            behavior.isFitToContents = false
            behavior.expandedOffset = 56.dp
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConfirm.setOnClickListener {
            if (vaild()){
                binding.apply {
                    (parentFragment as Communicate).onItemSelect(AirLineEntity(Calendar.getInstance().timeInMillis.toString(),
                        etEstablish.text.toString(),
                        etCountry.text.toString(),
                        "",
                        etName.text.toString(),
                        etHead.text.toString(),
                        "",
                        etSlogon.text.toString()))
                }
                this.dismiss()
            }
        }

        binding.btnCancle.setOnClickListener {
            this.dismiss()
        }
    }

    private val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()


    // validate added item and make sure that all input text filled
    private fun vaild(): Boolean {
        binding.apply {
            if (etName.isEmpty())
                return showError("fill name")
            else if (etSlogon.isEmpty())
                return showError("fill Slogon")
            else if (etCountry.isEmpty())
                return showError("fill Country")
            else if (etHead.isEmpty())
                return showError("fill Head")
            else if (etEstablish.isEmpty())
                return showError("fill Establish")
            else
                return true
        }
    }

    fun TextInputEditText.isEmpty(): Boolean {
        return this.text.toString().isEmpty()
    }

    private fun showError(message:String) :Boolean{
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
        return false
    }


    override fun onDestroy() {
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
        super.onDestroy()
    }

}