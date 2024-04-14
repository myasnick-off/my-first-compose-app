package com.example.myfirstcomposeapp.ui.theme

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myfirstcomposeapp.domain.FeedPost
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VkMainScreen(vm: MainViewModel) {
    val snackbarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()

    var fabIsVisible by remember {
        mutableStateOf(true)
    }

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
                val selectedItemPosition = remember {
                    mutableIntStateOf(0)
                }
                val items =
                    listOf(NavigationItem.Home, NavigationItem.Favorite, NavigationItem.Profile)
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemPosition.intValue == index,
                        onClick = { selectedItemPosition.intValue = index },
                        icon = {
                            Icon(
                                item.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = stringResource(item.titleResId))
                        }
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            if (fabIsVisible) {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            val action = snackbarHostState.showSnackbar(
                                message = "This is snackbar!",
                                actionLabel = "Hide",
                                duration = SnackbarDuration.Long
                            )
                            if (action == SnackbarResult.ActionPerformed) {
                                fabIsVisible = false
                            }
                        }
                    }
                ) {
                    Icon(Icons.Filled.Favorite, contentDescription = null)
                }
            }
        },
    ) {
        val feedPostState = vm.feedPostData.observeAsState(FeedPost())
        PostCard(
            modifier = Modifier
                .padding(it)
                .padding(8.dp),
            feedPostState.value,
            onViewClickListener = vm::updatePostStatistics,
            onShareClickListener = vm::updatePostStatistics,
            onCommentClickListener = vm::updatePostStatistics,
            onLikeClickListener = vm::updatePostStatistics,
        )
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