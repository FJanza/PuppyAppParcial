package  com.example.puppyappparcial.activities
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyappparcial.R
import com.example.puppyappparcial.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var submit_text: Button? = null
    private var nombre_text: EditText? = null
    private var numero_text: EditText? = null
    private var imagen_text: EditText? = null
    lateinit var binding_text: ActivityMainBinding
    var isAllFieldsChecked = false

    private fun checkAllFields(): Boolean {
        if (nombre_text?.text.isNullOrBlank()) {
            nombre_text?.error = "Ingrese un nombre valido"
            return false
        }

        if (numero_text?.text.isNullOrBlank()) {
            numero_text?.error = "Ingrese un numero valido"
            return false
        }
        if (imagen_text?.text.isNullOrBlank()) {
            imagen_text?.error = "Ingrese una URL valida"
            return false
        }

        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        submit_text = findViewById(R.id.btnSubmit)
        nombre_text = findViewById(R.id.txtNombre)
        numero_text = findViewById(R.id.txtnumero)
        imagen_text = findViewById(R.id.txtImagen)
        

        submit_text?.setOnClickListener {
            isAllFieldsChecked = checkAllFields()

            if (isAllFieldsChecked) {
                val nombre = nombre_text.toString()
                val imagenUrl = imagen_text.toString()
                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                intent.putExtra("nombre", nombre)
                intent.putExtra("imagenUrl", imagenUrl)
                startActivity(intent)
                finish()
                startActivity(intent)
            }
        }
    }
}







