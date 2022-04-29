package com.csci448.lsherburne.lsherburne_a3.ui.navigation.specs

import androidx.compose.material.IconButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.*
import com.csci448.lsherburne.lsherburne_a3.ui.navigation.specs.NewTrackScreenSpec
import com.csci448.lsherburne.lsherburne_a3.R
import com.csci448.lsherburne.lsherburne_a3.data.Album
import com.csci448.lsherburne.lsherburne_a3.data.Track
import com.csci448.lsherburne.lsherburne_a3.ui.screens.AlbumDetailScreen
import com.csci448.lsherburne.lsherburne_a3.viewModel.I_DiscoViewModel

object AlbumDetailScreenSpec: I_ScreenSpec {
    private var ARG = "id"
    override val title: Int
        get() = R.string.album_detail_title
    override val route: String
        get() = "AlbumDetailScreen/{$ARG}"
    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(ARG) { type = NavType.StringType })

    @Composable
    override fun TopAppBarActions(navController: NavHostController) {
        IconButton(
            onClick = { navController.navigate(NewTrackScreenSpec.navigateTo()){
            } }
        ) {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = null,
                )
        }
    }

    // In the below content function, the viewModel loads the specified album name from the
    // backstack of arguments. The viewModel.loadAlbum function loads the album only to the
    // viewModel. This is important later when we are adding a track. Since the add track view
    // is different from the album detail view, the data on the album is not persistent to the view.
    // However, the viewModel is ubiquitous between views, and thus is able to share and collect
    // that data when creating a new track.
    @Composable
    override fun Content(
        viewModel: I_DiscoViewModel,
        navController: NavHostController,
        backStackEntry: NavBackStackEntry
    ) {

        val thisAlbumName = backStackEntry.arguments?.getString("id")
        viewModel.loadAlbum((thisAlbumName!!))

        val albumState = viewModel.albumDetailsLiveData.observeAsState()
        val tracklist: List<Track>? = viewModel.trackListLiveData.observeAsState().value

        albumState.value?.let {
            AlbumDetailScreen(
                album = it,
                trackList = tracklist
            )
        }

    }

    override fun navigateTo(vararg args: String?): String {
               if (args == null) return "AlbumDetailScreen/0"
               else return "AlbumDetailScreen/${args[0].toString()}"
    }
}