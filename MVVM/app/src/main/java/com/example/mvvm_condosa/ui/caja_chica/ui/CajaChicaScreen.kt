package com.example.mvvm_condosa.ui.caja_chica.ui

import com.example.mvvm_condosa.data.Models.Predio1
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.mvvm_condosa.R
import com.example.mvvm_condosa.data.tables.Predio
import com.example.mvvm_condosa.ui.navigation.AppScreens
import com.example.mvvm_condosa.ui.theme.DarkColors
import com.example.mvvm_condosa.ui.theme.LightColors
@Composable
fun OptionsCaja(navController: NavController, colorScheme : ColorScheme) {
    Button(
        onClick = { navController.navigate(route = AppScreens.GastosMesAnteriorScreen.route) },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(colorScheme.secondaryContainer)
    ) {
        Text(
            text = stringResource(id = R.string.gastos_del_mes_anterior),
            fontSize = 20.sp,
            color = colorScheme.onSecondaryContainer
        )
    }
    Spacer(modifier = Modifier.padding(8.dp))
    Button(
        onClick = { navController.navigate(route = AppScreens.RegistroGastosScreen.route) },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(colorScheme.secondaryContainer)
    ) {
        Text(
            text = stringResource(id = R.string.registro_de_gastos),
            fontSize = 20.sp,
            color = colorScheme.onSecondaryContainer
        )
    }
}
@Composable
fun CajaChica(navController: NavController, cajaChicaViewModel: CajaChicaViewModel) {
    val colorScheme = if (isSystemInDarkTheme()) {
        DarkColors
    } else {
        LightColors
    }

    // Utiliza el ViewModel para obtener los datos desde la base de datos
    val predios = cajaChicaViewModel.obtenerDescripcionesUnicas() // Asegúrate de que esta función devuelva una lista de objetos Predio

    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<Predio1?>(null) } // Cambia el tipo de selectedItem a predio

    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded) {
        Icons.Default.KeyboardArrowUp
    } else {
        Icons.Default.KeyboardArrowDown
    }

    Column(modifier = Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedItem?.descripcion ?: "",
            onValueChange = { newItem ->
                selectedItem = predios.find { it.descripcion == newItem }
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = {
                Text(
                    text = stringResource(id = R.string.selecciona_un_predio),
                    color = colorScheme.onPrimary
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = !expanded },
                    tint = colorScheme.primary
                )
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            predios.forEach { predio ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = predio.descripcion,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    onClick = {
                        selectedItem = predio
                        expanded = false
                    }
                )
            }
        }
    }
}