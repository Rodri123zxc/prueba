package com.example.mvvm_condosa.data.Dao

import org.jetbrains.exposed.sql.transactions.transaction
import com.example.mvvm_condosa.data.tables.Predio.PredioTable
import org.jetbrains.exposed.sql.selectAll


class PredioDAO {
    fun obtenerDescripcionesUnicas(): List<String> {
        val descripcionesUnicas = mutableListOf<String>()

        transaction {
            val query = PredioTable
                .slice(PredioTable.descripcion)
                .selectAll()
                .distinct()

            for (row in query) {
                val descripcion = row[PredioTable.descripcion]
                descripcionesUnicas.add(descripcion)
            }
        }

        return descripcionesUnicas
    }
}
