package com.example.myfirstcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myfirstcomposeapp.ui.theme.MyFirstComposeAppTheme
import com.example.myfirstcomposeapp.ui.theme.VkMainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstComposeAppTheme {
                VkMainScreen()
            }
        }
    }
}
