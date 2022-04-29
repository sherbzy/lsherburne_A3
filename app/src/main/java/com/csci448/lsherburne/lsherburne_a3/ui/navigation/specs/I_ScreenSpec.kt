package com.csci448.lsherburne.lsherburne_a3.ui.navigation.specs


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.lsherburne.lsherburne_a3.ui.navigation.NavUtil
import com.csci448.lsherburne.lsherburne_a3.R
import com.csci448.lsherburne.lsherburne_a3.ui.navigation.specs.AlbumListScreenSpec
import com.csci448.lsherburne.lsherburne_a3.viewModel.I_DiscoViewModel

sealed interface I_ScreenSpec {

    val route: String
    val arguments: List<NamedNavArgument>
    val title: Int

    companion object {
        val startDestination: String = AlbumListScreenSpec.route
        val allScreens =
            I_ScreenSpec::class.sealedSubclasses.associate { it.objectInstance?.route to it.objectInstance }

        @Composable
        fun TopBar(navController: NavHostController, navBackStackEntry: NavBackStackEntry?) {
            val route = navBackStackEntry?.destination?.route ?: ""
            if (route != "") allScreens[route]?.TopAppBarContent(navController = navController)
        }

        @Composable
        fun BottomBar(navController: NavHostController, navBackStackEntry: NavBackStackEntry?) {
            val route = navBackStackEntry?.destination?.route ?: ""
            if (route != "") allScreens[route]?.BottomAppBarContent(
                navController = navController,
                navBackStackEntry = navBackStackEntry
            )
        }

    }

    @Composable
    fun TopAppBarActions(navController: NavHostController)
    @Composable fun Content(viewModel: I_DiscoViewModel, navController: NavHostController, backStackEntry: NavBackStackEntry)
    fun navigateTo(vararg args: String?): String

    private @Composable
    fun TopAppBarContent(navController: NavHostController) {
        TopAppBar (
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            navigationIcon = {
                if (navController.previousBackStackEntry != null) {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                } else {
                    null
                }
            }
            ,
            actions = {TopAppBarActions(navController = navController)}
        )
    }

    @Composable
    private fun BottomAppBarContent(
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?
    ) {
        BottomNavigation() {
            val items = listOf(
                NavUtil.Albums,
                NavUtil.Tracks
            )
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach{
                BottomNavigationItem(
                    selected = currentRoute == it.route,
                    onClick = {
                        navController.navigate(it.route) {
                            popUpTo(it.route)
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            painterResource(id = it.icon),
                            contentDescription = it.title
                        )
                    },
                    label = {
                        Text(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 12.sp,
                            text = it.title
                        )
                    },
                    alwaysShowLabel = true)
            }
        }
    }
}
