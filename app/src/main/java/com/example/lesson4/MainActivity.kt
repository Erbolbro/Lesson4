package com.example.lesson4

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {
    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            // Обработка выбранного изображения
            image.setImageURI(uri)
        }

    private lateinit var button: Button
    private lateinit var image: ImageView
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = this.findViewById(R.id.button)
        image = this.findViewById(R.id.image)
        editText = this.findViewById(R.id.edit_text)

        goToWhatsapp()
        
    }

    private fun goToWhatsapp() {
        button.setOnClickListener {
            val phoneNumber = editText.text.toString().trim()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber")
            startActivity(intent)
        }
       image.setOnClickListener {
            openGallery()
        }
    }

    private fun openGallery() {
        getContent.launch("image/*")
    }
}



