package sauceda.rayos.examen2_rayosalejandro

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LlamadaAContacto : AppCompatActivity() {

    lateinit var btnColgar: Button
    lateinit var txtLlamar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_llamada_acontacto)

        val contactoRecibido = intent.getSerializableExtra("contacto") as? Contacto
        btnColgar = findViewById(R.id.btn_terminar_llamada)
        txtLlamar = findViewById(R.id.tv_llamando)

        if (contactoRecibido != null) {
            txtLlamar.text = "Llamando a ${contactoRecibido.nombre}..."
        }

        btnColgar.setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}