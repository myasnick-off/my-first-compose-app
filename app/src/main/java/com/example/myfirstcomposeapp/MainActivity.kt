package com.example.myfirstcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.myfirstcomposeapp.ui.theme.MainViewModel
import com.example.myfirstcomposeapp.ui.theme.MyFirstComposeAppTheme
import com.example.myfirstcomposeapp.ui.theme.VkMainScreen

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstComposeAppTheme {
                VkMainScreen(viewModel)
                //InstagramProfileCard(viewModel)
            }
        }
    }
}
