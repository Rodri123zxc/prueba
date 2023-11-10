package com.example.mvvm_condosa.ui.caja_chica.ui

import androidx.lifecycle.ViewModel
import com.example.mvvm_condosa.data.Dao.PredioDAO
import com.example.mvvm_condosa.data.Models.Predio1
import javax.inject.Inject

class CajaChicaViewModel @Inject constructor(private val predioDao: PredioDAO) : ViewModel() {

    fun obtenerDescripcionesUnicas(): List<Predio1> {
        val descripcionesUnicas = predioDao.obtenerDescripcionesUnicas()
        return descripcionesUnicas.map { descripcion -> Predio1(descripcion = descripcion)}
    }

}

