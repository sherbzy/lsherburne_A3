package com.csci448.lsherburne.lsherburne_a3.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.csci448.lsherburne.lsherburne_a3.ui.navigation.specs.I_ScreenSpec
import com.csci448.lsherburne.lsherburne_a3.viewModel.I_DiscoViewModel

@Composable
fun DiscoNavHost(navController: NavController, viewModel: I_DiscoViewModel){
    NavHost(
        navController = navController as NavHostController,
        startDestination = I_ScreenSpec.startDestination) {
        I_ScreenSpec.allScreens.forEach { (route ,screen) ->
            composable(
                route = route!!,
            ) { backStackEntry ->
                screen?.Content(
                    viewModel,
                    navController,
                    backStackEntry)
            }
        }
    }
}
