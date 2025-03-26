package sauceda.rayos.examen2_rayosalejandro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AgregarContacto : AppCompatActivity() {

    lateinit var btnGuardar: Button
    lateinit var etNombre: EditText
    lateinit var etApellido: EditText
    lateinit var etTelefono: EditText
    lateinit var etEmail: EditText
    lateinit var etEmpresa: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_contacto)

        btnGuardar = findViewById(R.id.btn_guardar_contacto)
        etNombre = findViewById(R.id.et_nombre)
        etApellido = findViewById(R.id.et_apellidos)
        etTelefono = findViewById(R.id.et_telefono)
        etEmail = findViewById(R.id.et_email)
        etEmpresa = findViewById(R.id.et_empresa)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val apellido = etApellido.text.toString()
            val telefono = etTelefono.text.toString()
            val email = etEmail.text.toString()
            val empresa = etEmpresa.text.toString()

            val color = (1..5).random()

            val contacto = Contacto(nombre, apellido, telefono, email, empresa, color)
            val intent = Intent()
            intent.putExtra("nuevo_contacto", contacto)
            setResult(RESULT_OK, intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}