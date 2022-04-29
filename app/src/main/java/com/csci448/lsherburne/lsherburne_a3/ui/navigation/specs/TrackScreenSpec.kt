package com.csci448.lsherburne.lsherburne_a3.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.lsherburne.lsherburne_a3.R
import com.csci448.lsherburne.lsherburne_a3.data.Track
import com.csci448.lsherburne.lsherburne_a3.ui.screens.TrackListScreen
import com.csci448.lsherburne.lsherburne_a3.viewModel.I_DiscoViewModel

object TrackScreenSpec : I_ScreenSpec{
    override val title: Int
        get() = R.string.track_list_screen_title
    override val route: String
        get() = "TrackListScreen"
    override val arguments: List<NamedNavArgument>
        get() = emptyList()

    @Composable
    override fun TopAppBarActions(navController: NavHostController) { }

    @Composable
    override fun Content(
        viewModel: I_DiscoViewModel,
        navController: NavHostController,
        backStackEntry: NavBackStackEntry

    ) {

        val tracklist : List<Track>? = viewModel.allTracksLiveData.observeAsState().value

        TrackListScreen(trackList = tracklist, onSelectTrack = {} )
    }

    override fun navigateTo(vararg args: String?): String {
       return route
    }
}