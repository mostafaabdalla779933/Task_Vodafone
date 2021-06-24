package com.example.task_vodafone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.task_vodafone.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    lateinit var job : Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        job = CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}