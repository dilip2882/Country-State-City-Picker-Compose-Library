package com.dilip.countrystatecitypickersample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.dilip.countrystatecitypickersample.ui.theme.CountryStateCityPickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryStateCityPickerTheme {
                Text(text = "CountryStateCityPicker")
            }
        }
    }
}
