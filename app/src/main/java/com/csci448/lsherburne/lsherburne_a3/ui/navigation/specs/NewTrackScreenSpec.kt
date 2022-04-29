package com.csci448.lsherburne.lsherburne_a3.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.*
import com.csci448.lsherburne.lsherburne_a3.ui.screens.NewTrackScreen
import com.csci448.lsherburne.lsherburne_a3.viewModel.I_DiscoViewModel
import com.csci448.lsherburne.lsherburne_a3.R

object NewTrackScreenSpec: I_ScreenSpec {
    private var ARG = "newtrack"
    override val title: Int
        get() = R.string.new_track_screen
    override val route: String
        get() = "NewTrackScreen"
    override val arguments: List<NamedNavArgument>
        get() = listOf( navArgument(ARG) { type = NavType.StringType })

    // New track dont do nothing with the top bar
    @Composable
    override fun TopAppBarActions(navController: NavHostController) { }

    // As mentioned before, the album state is saved in the viewModel, so all it needs to ask for
    // is the album form the viewModel in order to collect the necessary information about the
    // new track and relate the track to the correct album.
    @Composable
    override fun Content(
        viewModel: I_DiscoViewModel,
        navController: NavHostController,
        backStackEntry: NavBackStackEntry
    ) {

        val albumState = viewModel.albumDetailsLiveData.observeAsState()

        albumState.value?.let {
            NewTrackScreen(album = it, onSaveTrack = {
                viewModel.addTrack(it)
                navController.navigateUp()
            })
        }

    }

    override fun navigateTo(vararg args: String?): String {
        return route
    }
}