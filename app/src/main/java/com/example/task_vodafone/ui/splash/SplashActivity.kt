package com.example.task_vodafone.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.task_vodafone.R
import com.example.task_vodafone.databinding.ActivitySplashBinding
import com.example.task_vodafone.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    lateinit var viewModel : SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        viewModel.getAirLines()

        viewModel.dataCached.observe(this){ cached ->
            if (cached){
                navigate()
            }
        }

        viewModel.errorLineLiveData.observe(this){ offline ->

            if(offline){
                binding.pb.visibility = View.GONE
                Snackbar.make(binding.root , getString(R.string.no_internet_connection), Snackbar.LENGTH_INDEFINITE )
                    .setAction("reload") {
                        binding.pb.visibility = View.VISIBLE
                        viewModel.getAirLines()
                    }.show()
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
        viewModel.clear()
    }
    private fun navigate(){
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }


    private fun showToast(message:String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }
}