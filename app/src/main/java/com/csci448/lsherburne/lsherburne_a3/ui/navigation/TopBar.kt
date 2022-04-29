package com.csci448.konchar.jkonchar_a3.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.csci448.lsherburne.lsherburne_a3.ui.navigation.specs.I_ScreenSpec

@Composable
fun DiscoTopBar(navController: NavHostController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    I_ScreenSpec.TopBar(
        navController = navController,
        navBackStackEntry = navBackStackEntry
    )
}