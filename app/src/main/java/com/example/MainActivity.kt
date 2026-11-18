package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.repository.UserPreferencesRepository
import com.example.ui.navigation.JansevaApp
import com.example.ui.state.JansevaViewModel
import com.example.ui.theme.TarapurJansevaTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val preferencesRepository = UserPreferencesRepository(applicationContext)
    setContent {
      TarapurJansevaTheme {
        val jansevaViewModel: JansevaViewModel = viewModel(
          factory = JansevaViewModel.Factory(preferencesRepository),
        )
        JansevaApp(viewModel = jansevaViewModel)
      }
    }
  }
}
