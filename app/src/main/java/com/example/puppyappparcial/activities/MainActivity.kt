package com.example.puppyappparcial.activities
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.puppyappparcial.R
import com.example.puppyappparcial.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executors

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var submit: Button
    lateinit var txtNombre: EditText
    lateinit var txtImagen: EditText
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onStart() {
        super.onStart()

        submit = binding.btnSubmit
        txtImagen = binding.txtImagen
        txtNombre = binding.txtNombre

        submit.setOnClickListener {
            val nombre = txtNombre.text.toString()
            val imagenUrl = txtImagen.text.toString()
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("nombre", nombre)
            intent.putExtra("imagenUrl", imagenUrl)
            startActivity(intent)
            finish()
        }

    }
}