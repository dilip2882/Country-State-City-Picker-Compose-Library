package com.dilip.countrystatecitypicker

import com.dilip.countrystatecitypicker.models.City
import com.dilip.countrystatecitypicker.models.Country
import com.dilip.countrystatecitypicker.models.State

interface LocationProvider {
    suspend fun loadCountries(): List<Country>
    suspend fun loadStates(countryCode: String): List<State>
    suspend fun loadCities(stateCode: String): List<City>
}

class DefaultLocationProvider : LocationProvider {
    override suspend fun loadCountries() = LocationDatabase.countries

    override suspend fun loadStates(countryCode: String) =
        LocationDatabase.countries.find { it.code == countryCode }?.states ?: emptyList()

    override suspend fun loadCities(stateCode: String) =
        LocationDatabase.countries.flatMap { it.states }.find { it.code == stateCode }?.cities
            ?: emptyList()
}
