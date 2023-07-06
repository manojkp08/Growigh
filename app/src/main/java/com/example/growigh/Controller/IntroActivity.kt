package com.example.growigh.Controller

import android.animation.AnimatorInflater
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.growigh.R
import com.example.growigh.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
            val decorView: View = window.decorView
            var flags: Int = decorView.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            decorView.systemUiVisibility = flags
        }

        binding.btnSkipFirstIntro.setOnClickListener {
            startWelcomeActivity()
            finish()
        }

        binding.introFirstBtn.setOnClickListener {
            startSecondIntroActivity()
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

    private fun startSecondIntroActivity() {
        val intent = Intent(this, SecondIntro::class.java)
        startActivity(intent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        // Clear the shared preferences when the app is resumed
        sharedPreferences.edit().clear().apply()
    }
}
