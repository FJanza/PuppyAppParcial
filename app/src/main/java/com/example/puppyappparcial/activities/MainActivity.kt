package com.example.puppyappparcial.activities
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyappparcial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var submit: Button
    lateinit var txtNombre: EditText
    lateinit var txtImagen: EditText
    lateinit var txtNumero: EditText
    lateinit var binding: ActivityMainBinding
    var isAllFieldsChecked = false
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
        txtNumero = binding.txtNumero

        submit.setOnClickListener {
            isAllFieldsChecked = checkAllFields()
            if (isAllFieldsChecked == true) {
                val nombre = txtNombre.text.toString()
                val imagenUrl = txtImagen.text.toString()
                val telefono = txtNumero.text.toString()
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("nombre", nombre)
                intent.putExtra("imagenUrl", imagenUrl)
                intent.putExtra("telefono", telefono)
                startActivity(intent)
                finish()
            }
        }


    }

    private fun checkAllFields(): Boolean {
        if (txtNombre.text.toString().isNullOrBlank()) {
            txtNombre.error = "Ingrese un nombre valido"
            return false
        }
        if (txtNumero.text.toString().isNullOrBlank() || txtNumero.text.length < 8 ) {
            txtNumero.error = "Ingrese un numero valido"
            return false
        }
        if (txtImagen.text.toString().isNullOrBlank()) {
            txtImagen.error = "Ingrese un nombre valido"
            return false
        }

        return true
    }
}