package com.example.weathercompose.presentation.forcast

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weathercompose.domain.weather.WeatherData
import com.example.weathercompose.presentation.WeatherCard
import com.example.weathercompose.presentation.WeatherForecast
import com.example.weathercompose.presentation.WeatherViewModel
import com.example.weathercompose.ui.theme.WeatherComposeTheme

@Composable
fun ForecastScreen(navController: NavController,viewModel: WeatherViewModel) {


    WeatherComposeTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF3DEC9B))
            ) {
                WeatherCard(
                    state = viewModel.state,
                    backgroundColor = Color(0xFF3DEC9B)
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    WeatherForecast(state = viewModel.state, clickHourlyData = {
                        val gson = com.google.gson.Gson()
                        val jsonWeatherData = gson.toJson(it)
                        navController.navigate("details/$jsonWeatherData")
                    })
                }
            }
            if(viewModel.state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            viewModel.state.error?.let { error ->
                Text(
                    text = error,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}