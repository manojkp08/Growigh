package com.example.growigh.Controller

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.growigh.R
import com.example.growigh.databinding.ActivityUploadImageBinding

class UploadImage : AppCompatActivity() {

    private lateinit var binding: ActivityUploadImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            val intent = Intent(this@UploadImage, WelcomeActivity::class.java)
            startActivity(intent)
        }

        binding.btnSelectImage.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, 45)
        }

        binding.tagCross.setOnClickListener {
            binding.selectedImage.setImageResource(R.drawable.place_holder)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 45 && resultCode == Activity.RESULT_OK) {
            val selectedImageUri = data?.data
            selectedImageUri?.let {
                val inputStream = contentResolver.openInputStream(it)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                binding.selectedImage.setImageBitmap(bitmap)
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(this@UploadImage, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}