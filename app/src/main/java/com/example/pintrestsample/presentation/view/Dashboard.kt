package com.example.pintrestsample.presentation.view

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pintrestsample.presentation.view.SearchScreens.SearchScreen

enum class Menu {
    HOME,
    SEARCH,
    PROFILE
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun Dashboard(navController: NavHostController) {
    val menuItems = listOf(Menu.HOME, Menu.SEARCH, Menu.PROFILE)
    val currentRoute = currentRoute(navController)
    NavigationBar(modifier = Modifier.height(80.dp)) {
        menuItems.forEach { menu ->
            var iconImg : ImageVector = Icons.Default.Home
            if(menu== Menu.SEARCH){
                iconImg = Icons.Default.Search
            }
            if(menu == Menu.PROFILE){
                iconImg = Icons.Default.Face
            }
            NavigationBarItem(
                selected = currentRoute == menu.name,
                onClick = { navController.navigate(menu.name) },
                modifier = Modifier.padding(10.dp),
                label = { Text(text = menu.name) },
                icon = { Icon(iconImg, contentDescription = menu.name) }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Menu.HOME.name) {
        composable(Menu.HOME.name) { HomeScreen() }
        composable(Menu.SEARCH.name) { SearchScreen() }
        composable(Menu.PROFILE.name) {  }
    }
}