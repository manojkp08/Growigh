package com.example.growigh.Controller

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.growigh.R
import com.example.growigh.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)

            // Set the icons and information in the status bar to appear in black color
            val decorView: View = window.decorView
            var flags: Int = decorView.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            decorView.systemUiVisibility = flags
        }

        binding.btnFeeds.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnUploadImage.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, UploadImage::class.java)
            startActivity(intent)
            finish()
        }
    }
}