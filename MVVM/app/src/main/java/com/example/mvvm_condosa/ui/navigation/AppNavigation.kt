package com.example.mvvm_condosa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvm_condosa.ui.caja_chica.ui.AnadirGastosScreen
import com.example.mvvm_condosa.ui.caja_chica.ui.CajaChica
import com.example.mvvm_condosa.ui.caja_chica.ui.CajaChicaViewModel
import com.example.mvvm_condosa.ui.caja_chica.ui.GastosMesAnteriorScreen
import com.example.mvvm_condosa.ui.caja_chica.ui.GastosScreen
import com.example.mvvm_condosa.ui.caja_chica.ui.RegistroGastosScreen
import com.example.mvvm_condosa.ui.home.ui.HomeScreen
import com.example.mvvm_condosa.ui.login.ui.LoginScreen
import com.example.mvvm_condosa.data.Dao.PredioDAO

@Composable
fun AppNavigation() {
    // Crear una instancia de PredioDAO
    val predioDao = PredioDAO()

    // Luego, crear una instancia de CajaChicaViewModel y proporcionar predioDao
    val cajaChicaViewModel = CajaChicaViewModel(predioDao)



    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(navController)
        }
        // Finalmente, utilizar cajaChicaViewModel en tu composable
        composable(route = AppScreens.CajaChicaScreen.route) {
            CajaChica(navController, cajaChicaViewModel)
        }

        composable(route = AppScreens.GastosMesAnteriorScreen.route) {
            GastosMesAnteriorScreen()
        }
        composable(route = AppScreens.RegistroGastosScreen.route) {
            RegistroGastosScreen(navController)
        }
        composable(route = AppScreens.GastosScreen.route) {
            GastosScreen()
        }
        composable(route = AppScreens.AnadirGastoScreen.route) {
            AnadirGastosScreen(navController)
        }
    }
}