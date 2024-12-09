package com.example.weathercompose.presentation.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import com.example.weathercompose.domain.weather.WeatherData
import com.example.weathercompose.domain.weather.WeatherType
import com.example.weathercompose.presentation.forcast.adapter.WeatherTypeAdapter
import com.google.gson.GsonBuilder

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailScreen(navBackStackEntry: NavBackStackEntry) {
    val jsonWeatherData = navBackStackEntry.arguments?.getString("weatherData")?.trim()
    val gson = GsonBuilder()
        .registerTypeAdapter(WeatherType::class.java, WeatherTypeAdapter())
        .create()
    val weatherData: WeatherData = gson.fromJson(jsonWeatherData, WeatherData::class.java)
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF3DEC9B))) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = weatherData.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.width(300.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "ტემპერატურა: ${weatherData.temperatureCelsius}°C",
                    fontSize = 24.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "მდგომარეობა:  ${weatherData.weatherType.weatherDesc}",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "ქარის სიჩქარე: ${weatherData.windSpeed}კმ/სთ",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(48.dp))

            }

        }
    }
}