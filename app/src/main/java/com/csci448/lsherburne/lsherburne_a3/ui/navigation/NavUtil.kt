package com.csci448.lsherburne.lsherburne_a3.ui.navigation

import com.csci448.lsherburne.lsherburne_a3.R

sealed class NavUtil(var route: String, var icon: Int, var title: String) {

    object Tracks : NavUtil(route = "TrackListScreen", icon = R.drawable.ic_baseline_audiotrack_24, title = "Track List")

    object Albums : NavUtil(route = "AlbumListScreen", icon = R.drawable.ic_baseline_album, title = "Album List")
}
