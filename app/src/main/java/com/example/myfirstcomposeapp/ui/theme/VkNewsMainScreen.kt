package com.example.myfirstcomposeapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VkMainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "TopBar Title")
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationItem(iconImage = Icons.Filled.Favorite, title = "Favorite")
                NavigationItem(iconImage = Icons.Filled.Edit, title = "Edit")
                NavigationItem(iconImage = Icons.Filled.Delete, title = "Delete")
            }
        }
    ) {
        Box(
            Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            //InstagramProfileCard()
            PostCard()
        }
    }
}

@Composable
private fun RowScope.NavigationItem(iconImage: ImageVector, title: String) {
    NavigationBarItem(
        selected = true,
        onClick = { /*TODO*/ },
        icon = {
            Icon(
                iconImage,
                contentDescription = null
            )
        },
        label = {
            Text(text = title)
        }
    )
}