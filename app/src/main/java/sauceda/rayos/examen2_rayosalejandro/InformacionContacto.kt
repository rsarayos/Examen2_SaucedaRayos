package sauceda.rayos.examen2_rayosalejandro

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InformacionContacto : AppCompatActivity() {

    lateinit var btnLlamar: Button
    lateinit var imagen: ImageView
    lateinit var txtNombre: TextView
    lateinit var txtEmpresa: TextView
    lateinit var txtEmail: TextView
    lateinit var txtTelefono: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_informacion_contacto)

        btnLlamar = findViewById(R.id.btn_llamar_contacto)
        txtNombre = findViewById(R.id.txt_nombre)
        txtEmpresa = findViewById(R.id.txt_empresa)
        txtEmail = findViewById(R.id.txt_correo)
        txtTelefono = findViewById(R.id.txt_telefono)
        imagen = findViewById(R.id.img_contacto)

        val contactoRecibido = intent.getSerializableExtra("contacto") as? Contacto

        if (contactoRecibido != null) {
            txtNombre.setText(contactoRecibido.nombre)
            txtEmpresa.setText(contactoRecibido.empresa)
            txtEmail.setText(contactoRecibido.correo)
            txtTelefono.setText(contactoRecibido.telefono)

            val colorResId = when (contactoRecibido.color) {
                1 -> R.color.dark_red
                2 -> R.color.dark_blue
                3 -> R.color.dark_green
                4 -> R.color.dark_orange
                5 -> R.color.dark_yellow
                else -> R.color.dark_red
            }

            val fondo = imagen.background
            if (fondo is GradientDrawable) {
                fondo.setColor(ContextCompat.getColor(this, colorResId))
            }

        }

        btnLlamar.setOnClickListener {
            val intent = Intent(this, LlamadaAContacto::class.java)
            intent.putExtra("contacto", contactoRecibido)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}