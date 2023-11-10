package com.example.mvvm_condosa.data.tables
import org.jetbrains.exposed.sql.Table
class Predio {
    object PredioTable : Table("predio") {
        val id_predio = integer("id_predio").autoIncrement()
        val id_tipo_predio = integer("id_tipo_predio")
        val descripcion = varchar("descripcion", length = 80)
        val ruc = varchar("ruc", length = 20)
        val telefono = varchar("telefono", length = 12)
        val correo = varchar("correo", length = 80)
        val direccion = varchar("direccion", length = 100)
        val idubigeo = varchar("idubigeo", length = 6)
        val id_persona = integer("id_persona")

        override val primaryKey = PrimaryKey(id_predio)
    }
}
