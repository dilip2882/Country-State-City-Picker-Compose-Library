package com.dilip.countrystatecitypicker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dilip.countrystatecitypicker.models.City
import com.dilip.countrystatecitypicker.models.Country
import com.dilip.countrystatecitypicker.models.State

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LocationPickerSheet(
    currentLevel: SelectionLevel,
    onCountrySelected: (Country) -> Unit,
    onStateSelected: (State) -> Unit,
    onCitySelected: (City) -> Unit,
    onDismiss: () -> Unit,
    customization: LocationPickerCustomization,
    viewModel: LocationPickerViewModel
) {
    val countries by viewModel.countries.collectAsState()
    val states by viewModel.states.collectAsState()
    val cities by viewModel.cities.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Surface(
            color = customization.backgroundColor,
            modifier = Modifier.fillMaxHeight(0.9f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = when (currentLevel) {
                        SelectionLevel.COUNTRY -> customization.headerTitles.countrySelection
                        SelectionLevel.STATE -> customization.headerTitles.stateSelection
                        SelectionLevel.CITY -> customization.headerTitles.citySelection
                    },
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                if (customization.searchEnabled) {
                    SearchField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        hint = customization.searchHint,
                        showClearIcon = customization.showClearIcon
                    )
                }

                LazyColumn {
                    when (currentLevel) {
                        SelectionLevel.COUNTRY -> {
                            items(filterLocations(countries, searchQuery)) { country ->
                                customization.countryItemContent(country)
                            }
                        }

                        SelectionLevel.STATE -> {
                            items(filterLocations(states, searchQuery)) { state ->
                                customization.stateItemContent(state)
                            }
                        }

                        SelectionLevel.CITY -> {
                            items(filterLocations(cities, searchQuery)) { city ->
                                customization.cityItemContent(city)
                            }
                        }
                    }
                }
            }
        }
    }
}
