package sauceda.rayos.examen2_rayosalejandro

import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var listaContactos: ArrayList<Contacto>
    lateinit var lista: ListView
    lateinit var btnAgregarContacto : Button
    lateinit var adapter: ContactoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        listaContactos = ArrayList()
        lista = findViewById(R.id.lista_contactos)
        btnAgregarContacto = findViewById(R.id.btn_agregar_contacto)

        llenarLista()

        adapter = ContactoAdapter(this, listaContactos)

        lista.adapter = adapter

        btnAgregarContacto.setOnClickListener {
            val intent = Intent(this, AgregarContacto::class.java)
            startActivityForResult(intent, 1)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            val nuevoUsuario = data?.getSerializableExtra("nuevo_contacto") as? Contacto
            if (nuevoUsuario != null) {
                listaContactos.add(nuevoUsuario)
                adapter.notifyDataSetChanged()
            }
        }
    }

    fun llenarLista() {
        listaContactos.add(Contacto("Lucía",  "Peña",    "6441223565", "luciap@gmail.com",     "Secretaria de Salud", 1))
        listaContactos.add(Contacto("Pedro",  "Gómez",   "6441227889", "pedrog@acme.com",      "ACME Corp.",          2))
        listaContactos.add(Contacto("María",  "López",   "6449991122", "maria.lopez@yahoo.com","Soriana",     3))
        listaContactos.add(Contacto("Carlos", "Ramírez", "6445557744", "carlosr@hotmail.com",  "Carls jr",         4))
        listaContactos.add(Contacto("Ana",    "García",  "6441236789", "ana.garcia@gmail.com", "Tech Solutions",      5))
    }

    class ContactoAdapter: BaseAdapter {
        var contactos = ArrayList<Contacto>()
        var context: Context? = null

        constructor(context: Context, contactos: ArrayList<Contacto>) {
            this.context = context
            this.contactos = contactos
        }

        override fun getCount(): Int {
            return contactos.size
        }

        override fun getItem(position: Int): Any {
            return contactos[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var contacto = contactos[position]
            var inflator = LayoutInflater.from(context)
            var vista = inflator.inflate(R.layout.contacto_view, null)
            var imagen = vista.findViewById(R.id.img_contacto) as ImageView
            var nombre = vista.findViewById(R.id.txt_nombre) as TextView
            var empresa = vista.findViewById(R.id.txt_empresa) as TextView

            nombre.text = contacto.nombre
            empresa.text = contacto.empresa

            val colorUsuario = contacto?.color

            val colorResId = when (colorUsuario) {
                1 -> R.color.dark_red
                2 -> R.color.dark_blue
                3 -> R.color.dark_green
                4 -> R.color.dark_orange
                5 -> R.color.dark_yellow
                else -> R.color.dark_red
            }

            val fondo = imagen.background
            if (fondo is GradientDrawable) {
                fondo.setColor(ContextCompat.getColor(context!!, colorResId))
            }

            vista.setOnClickListener {
                val intent = Intent(context, InformacionContacto::class.java)
                intent.putExtra("contacto", contacto)
                context!!.startActivity(intent)
            }

            return vista
        }


    }

}