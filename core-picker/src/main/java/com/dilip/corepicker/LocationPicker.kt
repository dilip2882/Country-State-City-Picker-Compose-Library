package com.dilip.corepicker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.dilip.corepicker.models.City
import com.dilip.corepicker.models.Country
import com.dilip.corepicker.models.State

@Composable
fun LocationPicker(
    modifier: Modifier = Modifier,
    initialCountry: Country? = null,
    initialState: State? = null,
    initialCity: City? = null,
    onLocationSelected: (List<Country>, List<State>, List<City>) -> Unit,
    customization: LocationPickerCustomization = LocationPickerCustomization(),
    viewModel: LocationPickerViewModel = LocationPickerViewModel()
) {
    var selectedCountries by remember { mutableStateOf(listOf<Country>()) }
    var selectedStates by remember { mutableStateOf(listOf<State>()) }
    var selectedCities by remember { mutableStateOf(listOf<City>()) }
    var showPicker by remember { mutableStateOf(false) }
    var currentLevel by remember { mutableStateOf(SelectionLevel.COUNTRY) }

    Column(modifier = modifier.clickable { showPicker = true }) {
        LocationDisplay(
            countries = selectedCountries,
            states = selectedStates,
            cities = selectedCities,
            customization = customization
        )

        if (showPicker) {
            LocationPickerSheet(
                currentLevel = currentLevel,
                onCountrySelected = { country ->
                    selectedCountries = if (customization.selectionMode == SelectionMode.MULTI_SELECT) {
                        (selectedCountries + country).take(customization.maxSelectionLimit)
                    } else {
                        listOf(country)
                    }
                    viewModel.loadStates(country.code)
                    currentLevel = SelectionLevel.STATE
                },
                onStateSelected = { state ->
                    selectedStates = if (customization.selectionMode == SelectionMode.MULTI_SELECT) {
                        (selectedStates + state).take(customization.maxSelectionLimit)
                    } else {
                        listOf(state)
                    }
                    viewModel.loadCities(state.code)
                    currentLevel = SelectionLevel.CITY
                },
                onCitySelected = { city ->
                    selectedCities = if (customization.selectionMode == SelectionMode.MULTI_SELECT) {
                        (selectedCities + city).take(customization.maxSelectionLimit)
                    } else {
                        listOf(city)
                    }
                    if (customization.selectionMode != SelectionMode.MULTI_SELECT) {
                        showPicker = false
                    }
                },
                onDismiss = { showPicker = false },
                customization = customization,
                viewModel = viewModel
            )
        }

        LaunchedEffect(selectedCountries, selectedStates, selectedCities) {
            onLocationSelected(selectedCountries, selectedStates, selectedCities)
        }
    }
}
