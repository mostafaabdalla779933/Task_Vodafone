package com.example.task_vodafone.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.task_vodafone.R
import com.example.task_vodafone.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        (supportFragmentManager.findFragmentById(R.id.f_container) as NavHostFragment).navController
            .addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.airlinesFragment -> {
                        binding.toolbar.navigationIcon = null
                        binding.faBtn.startAnimation(slideDown())
                    }
                    R.id.detialsFragment -> {
                        binding.toolbar.navigationIcon = ResourcesCompat.getDrawable(resources,R.drawable.ic_chevron_left,null)
                        binding.faBtn.visibility = View.GONE
                        binding.faBtn.startAnimation(slideUp())
                    }
                }

                binding.toolbar.setNavigationOnClickListener {
                    findNavController(R.id.f_container).popBackStack()
                }
            }


    }


    private fun slideUp(): TranslateAnimation {
        TranslateAnimation(0.0F,
            0f, 0f, binding.faBtn.height.toFloat() + 100f).let { animate ->
            animate.duration = 200
            return animate
        }
    }

    private fun slideDown(): TranslateAnimation {
        TranslateAnimation( 0f, 0f,binding.faBtn.height.toFloat() + 100f, 0f).let { animate ->
            animate.duration = 200
            animate.fillAfter = true
            animate.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {
                    binding.faBtn.visibility = View.VISIBLE
                }
                override fun onAnimationRepeat(animation: Animation?) {}
            })
            return animate
        }
    }
}