package sauceda.rayos.examen2_rayosalejandro

import java.io.Serializable

data class Contacto (val nombre: String,
                     val apellido: String,
                     val telefono: String,
                     val correo: String,
                     val empresa: String,
                     val color: Int) : Serializable