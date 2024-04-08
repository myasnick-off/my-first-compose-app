package com.example.myfirstcomposeapp.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myfirstcomposeapp.R

sealed class NavigationItem(
    val titleResId: Int,
    val icon: ImageVector
) {
    data object Home: NavigationItem(
        titleResId = R.string.navigation_title_home,
        icon = Icons.Filled.Home
    )
    data object Favorite: NavigationItem(
        titleResId = R.string.navigation_title_favorite,
        icon = Icons.Filled.Favorite
    )
    data object Profile: NavigationItem(
        titleResId = R.string.navigation_title_profile,
        icon = Icons.Default.Person
    )
}