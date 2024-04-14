package com.example.myfirstcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.myfirstcomposeapp.ui.theme.MainViewModel
import com.example.myfirstcomposeapp.ui.theme.MyFirstComposeAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstComposeAppTheme {
                //VkMainScreen(viewModel)
                Content(viewModel)
            }
        }
    }
}

@Composable
private fun Content(viewModel: MainViewModel) {
    val models = viewModel.models.observeAsState(listOf())
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(models.value) { model ->
            InstagramProfileCard(
                model = model,
                followClickListener = { viewModel.changeFollowingState(it) })
        }
    }
}
