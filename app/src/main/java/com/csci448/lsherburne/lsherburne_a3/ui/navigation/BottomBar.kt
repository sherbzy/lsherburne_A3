package com.csci448.lsherburne.lsherburne_a3.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
//import com.csci448..jkonchar_a3.ui.navigation.specs.I_ScreenSpec
import com.csci448.lsherburne.lsherburne_a3.ui.navigation.specs.I_ScreenSpec

@Composable
fun DiscoBottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    I_ScreenSpec.BottomBar(
        navController = navController,
        navBackStackEntry = navBackStackEntry
    )
}
