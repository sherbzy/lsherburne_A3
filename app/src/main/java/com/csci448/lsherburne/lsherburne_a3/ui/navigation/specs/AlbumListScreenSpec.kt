package com.csci448.lsherburne.lsherburne_a3.ui.navigation.specs

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.lsherburne.lsherburne_a3.R
import com.csci448.lsherburne.lsherburne_a3.ui.navigation.specs.AlbumDetailScreenSpec
import com.csci448.lsherburne.lsherburne_a3.ui.navigation.specs.I_ScreenSpec
import com.csci448.lsherburne.lsherburne_a3.ui.navigation.specs.NewAlbumScreenSpec
import com.csci448.lsherburne.lsherburne_a3.ui.screens.AlbumListScreen
import com.csci448.lsherburne.lsherburne_a3.viewModel.I_DiscoViewModel

object AlbumListScreenSpec: I_ScreenSpec {
    override val title: Int
        get() = R.string.album_list_title
    override val route: String
        get() = "AlbumListScreen"
    override val arguments: List<NamedNavArgument>
        get() = emptyList()

    @Composable
    override fun TopAppBarActions(navController: NavHostController) {
        IconButton(
            onClick = { navController.navigate(NewAlbumScreenSpec.navigateTo()){
            } }
        ) {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = null,
            )
        }
    }

    @Composable
    override fun Content(
        viewModel: I_DiscoViewModel,
        navController: NavHostController,
        backStackEntry: NavBackStackEntry
    ) {

        val listState = viewModel.albumListLiveData.observeAsState()

        AlbumListScreen(albumList = listState.value, onSelectAlbum = {
            navController.navigate(AlbumDetailScreenSpec.navigateTo(it.albumTitle))
        })

    }

    override fun navigateTo(vararg args: String?): String {
        return route
    }
}

