package cat.dam.andy.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cat.dam.andy.app.ui.navigation.NavigationConstants.LAYOUT1
import cat.dam.andy.app.ui.navigation.NavigationConstants.LAYOUT2
import cat.dam.andy.app.ui.navigation.NavigationConstants.LAYOUT3
import cat.dam.andy.app.ui.navigation.NavigationConstants.LAYOUT4
import cat.dam.andy.app.ui.screens.Layout1
import cat.dam.andy.app.ui.screens.Layout2
import cat.dam.andy.app.ui.screens.Layout3
import cat.dam.andy.app.ui.screens.Layout4

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = LAYOUT1) {
        composable(LAYOUT1) { Layout1() }
        composable(LAYOUT2) { Layout2() }
        composable(LAYOUT3) { Layout3() }
        composable(LAYOUT4) { Layout4() }
    }
}