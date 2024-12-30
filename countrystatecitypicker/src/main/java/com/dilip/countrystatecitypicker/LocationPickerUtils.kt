package com.dilip.countrystatecitypicker

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.dilip.countrystatecitypicker.models.City
import com.dilip.countrystatecitypicker.models.Country
import com.dilip.countrystatecitypicker.models.State

internal enum class SelectionLevel {
    COUNTRY, STATE, CITY
}

@Composable
fun LocationDisplay(
    countries: List<Country>,
    states: List<State>,
    cities: List<City>,
    customization: LocationPickerCustomization
) {
    Column {
        Text("Countries: ${countries.joinToString { it.name }}")
        Text("States: ${states.joinToString { it.name }}")
        Text("Cities: ${cities.joinToString { it.name }}")
    }
}

internal fun <T> filterLocations(items: List<T>, query: String): List<T> where T : Any {
    if (query.isEmpty()) return items
    return items.filter {
        when (it) {
            is Country -> it.name.contains(query, true) || it.code.contains(query, true)
            is State -> it.name.contains(query, true) || it.code.contains(query, true)
            is City -> it.name.contains(query, true) || it.code.contains(query, true)
            else -> false
        }
    }
}
