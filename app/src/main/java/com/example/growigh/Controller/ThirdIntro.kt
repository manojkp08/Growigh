package com.example.growigh.Controller

import android.animation.Animator
import android.animation.AnimatorInflater
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import com.example.growigh.R
import com.example.growigh.databinding.ActivityThirdIntroBinding

class ThirdIntro : AppCompatActivity() {
    private lateinit var binding: ActivityThirdIntroBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        val moveToRight: Animation = AnimationUtils.loadAnimation(this, R.anim.move_in_right)
        binding.introThirdBtn.startAnimation(moveToRight)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)

            val decorView: View = window.decorView
            var flags: Int = decorView.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            decorView.systemUiVisibility = flags
        }

        binding.btnSkipThirdIntro.setOnClickListener {
            startWelcomeActivity()
            finish()
        }

        binding.introThirdBtn.setOnClickListener {
            startWelcomeActivity()
            finish()
        }

        if (isIntroShown()) {
            startWelcomeActivity()
            finish()
        } else {
            markIntroAsShown()
        }
    }

    private fun isIntroShown(): Boolean {
        return sharedPreferences.getBoolean("isIntroShown", false)
    }

    private fun markIntroAsShown() {
        sharedPreferences.edit().putBoolean("isIntroShown", true).apply()
    }
    private fun startWelcomeActivity() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
