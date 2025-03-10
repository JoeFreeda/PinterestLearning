package com.example.pintrestsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.pintrestsample.ui.theme.PintrestSampleTheme
import com.example.pintrestsample.presentation.view.Dashboard
import com.example.pintrestsample.presentation.view.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PintrestSampleTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { Dashboard(navController) }) { paddingValues ->
                    Box(
                        modifier = Modifier.padding(
                            paddingValues
                        )
                    ) { NavigationGraph(navController) }
                }
            }
        }
    }
}

