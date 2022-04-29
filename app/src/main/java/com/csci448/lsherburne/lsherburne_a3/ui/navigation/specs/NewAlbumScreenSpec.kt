package com.csci448.lsherburne.lsherburne_a3.ui.navigation.specs


import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.lsherburne.lsherburne_a3.R
import com.csci448.lsherburne.lsherburne_a3.ui.navigation.specs.AlbumListScreenSpec
import com.csci448.lsherburne.lsherburne_a3.ui.screens.NewAlbumScreen
import com.csci448.lsherburne.lsherburne_a3.viewModel.I_DiscoViewModel


object NewAlbumScreenSpec: I_ScreenSpec {
    override val title: Int
        get() = R.string.new_album_screen_title
    override val route: String
        get() = "NewAlbumScreen"
    override val arguments: List<NamedNavArgument>
        get() = emptyList()

    // TopBar dont do nothing on this view.
    @Composable
    override fun TopAppBarActions(navController: NavHostController) { }

    // Very little to do here
    @Composable
    override fun Content(
        viewModel: I_DiscoViewModel,
        navController: NavHostController,
        backStackEntry: NavBackStackEntry
    ) {
        NewAlbumScreen(onSaveAlbum ={
            viewModel.addAlbum(it)
            navController.popBackStack(route = AlbumListScreenSpec.navigateTo(), inclusive = false)
        })
    }

    override fun navigateTo(vararg args: String?): String {
        return route
    }
}