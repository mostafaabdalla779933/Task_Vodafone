package com.example.task_vodafone.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.task_vodafone.databinding.ActivitySplashBinding
import com.example.task_vodafone.ui.MainActivity
import com.example.task_vodafone.ui.airlines.AirLineViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    lateinit var viewModel : SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        viewModel.getAirLines().observe(this) {
            if (it) {
                navigate()
            }
        }

        viewModel.stateLiveData.observe(this){ message ->

            if (message != null){
                showToast(message)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()

    }
    fun navigate(){

        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }


    fun showToast(message:String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }
}