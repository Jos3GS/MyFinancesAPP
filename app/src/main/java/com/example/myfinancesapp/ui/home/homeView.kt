@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myfinancesapp.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.myfinancesapp.R

@Composable
fun HomeView(homeViewModel: HomeVM) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        bottomBar = { BottomAppbarHome(homeViewModel) },
        topBar = { TopAppbarHome() }
    ) { it.calculateTopPadding()

    }
}

@Composable
private fun TopAppbarHome() {
    TopAppBar(title = { Text(text = "Hola de vuelta don chimbo") },
        navigationIcon = {
            IconButton(onClick = {}) {
                BottomAppbarIcon(selectedIcon = R.drawable.menu)
            }
        },
        actions = {
            IconButton(onClick = {}) {
                BottomAppbarIcon(selectedIcon = R.drawable.help)
            }
        }

    )
}

@Composable
private fun BottomAppbarHome(homeViewModel: HomeVM) {
    val index by homeViewModel.bottomAppbarItemSelected.observeAsState(initial = 0)
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        NavigationBarItem(selected = index == 0, onClick = { homeViewModel.onBottomAppbarItemSelectedChanged(item = 0) }, icon = { BottomAppbarIcon(selected = index == 0, selectedIcon = R.drawable.resume_filled, unselectedIcon = R.drawable.resume_outlined) }, label = {BottomAppbarLabel(label = "Balance")})
        NavigationBarItem(selected = index == 1, onClick = { homeViewModel.onBottomAppbarItemSelectedChanged(item = 1) }, icon = { BottomAppbarIcon(selected = index == 1, selectedIcon = R.drawable.gained_filled, unselectedIcon = R.drawable.gained_outlined) }, label = {BottomAppbarLabel(label = "Ingresos")})
        NavigationBarItem(selected = index == 2, onClick = { homeViewModel.onBottomAppbarItemSelectedChanged(item = 2) }, icon = { BottomAppbarIcon(selected = index == 2, selectedIcon = R.drawable.shop_filled, unselectedIcon = R.drawable.shop_outlined) }, label = {BottomAppbarLabel(label = "Gastos")})
        NavigationBarItem(selected = index == 3, onClick = { homeViewModel.onBottomAppbarItemSelectedChanged(item = 3) }, icon = { BottomAppbarIcon(selected = index == 3, selectedIcon = R.drawable.check_filled, unselectedIcon = R.drawable.check_outlined) }, label = {BottomAppbarLabel(label = "Resumen")})
        NavigationBarItem(selected = index == 4, onClick = { homeViewModel.onBottomAppbarItemSelectedChanged(item = 4) }, icon = { BottomAppbarIcon(selected = index == 4, selectedIcon = R.drawable.profile_filled, unselectedIcon = R.drawable.profile_outlined) }, label = {BottomAppbarLabel(label = "Perfil")})
    }
}

@Composable
private fun BottomAppbarIcon(
    selected: Boolean,
    @DrawableRes unselectedIcon: Int,
    @DrawableRes selectedIcon: Int,
) {
    if (selected) {
        Icon(
            painter = painterResource(id = selectedIcon),
            contentDescription = "icono seleccionado",
            tint = MaterialTheme.colorScheme.primary)
    } else {
        Icon(
            painter = painterResource(id = unselectedIcon),
            contentDescription = "icono sin seleccionar",
            tint = MaterialTheme.colorScheme.secondary)
    }
}

@Composable
private fun BottomAppbarIcon(
    @DrawableRes selectedIcon: Int,
) {
    Icon(
            painter = painterResource(id = selectedIcon),
            contentDescription = "icono seleccionado",
            tint = MaterialTheme.colorScheme.secondary
    )

}

@Composable
private fun BottomAppbarLabel(label:String){
    Text(text = label)
}
