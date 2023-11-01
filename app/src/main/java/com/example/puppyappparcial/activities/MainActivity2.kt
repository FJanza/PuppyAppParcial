package com.example.puppyappparcial.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.puppyappparcial.R

class MainActivity2 : AppCompatActivity() {
    lateinit var nombre2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Encuentra el TextView en tu layout usando su ID
        nombre2 = findViewById<TextView>(R.id.pruebaText)

        // Obtén los valores del Intent
        val nombre = intent.getStringExtra("nombre")
        val imagenUrl = intent.getStringExtra("imagenUrl")
        Log.d("MainActivity2", "Nombre: $nombre, Imagen URL: $imagenUrl")
        // Asigna el valor al TextView
        nombre2.text = nombre
    }

}