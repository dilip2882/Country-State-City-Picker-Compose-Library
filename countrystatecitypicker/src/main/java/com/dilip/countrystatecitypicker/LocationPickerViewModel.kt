package com.dilip.countrystatecitypicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilip.countrystatecitypicker.models.City
import com.dilip.countrystatecitypicker.models.Country
import com.dilip.countrystatecitypicker.models.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationPickerViewModel(
    private val locationProvider: LocationProvider = DefaultLocationProvider()
) : ViewModel() {
    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    private val _states = MutableStateFlow<List<State>>(emptyList())
    val states: StateFlow<List<State>> = _states

    private val _cities = MutableStateFlow<List<City>>(emptyList())
    val cities: StateFlow<List<City>> = _cities

    fun loadCountries() {
        viewModelScope.launch {
            _countries.value = locationProvider.loadCountries()
        }
    }

    fun loadStates(countryCode: String) {
        viewModelScope.launch {
            _states.value = locationProvider.loadStates(countryCode)
        }
    }

    fun loadCities(stateCode: String) {
        viewModelScope.launch {
            _cities.value = locationProvider.loadCities(stateCode)
        }
    }
}
