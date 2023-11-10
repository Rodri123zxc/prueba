package com.example.mvvm_condosa.data

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseConfig {
    fun init() {
        try {
            Database.connect(
                url = "jdbc:postgresql://137.184.120.127:5432/sigcon",
                driver = "org.postgresql.Driver",
                user = "modulo4",
                password = "modulo4"
            )
        } catch (e: Exception) {
            println("Error al conectar a la base de datos: ${e.message}")
        }
    }
}