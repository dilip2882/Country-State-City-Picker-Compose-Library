package com.dilip.pickerjsonassets.picker

import com.dilip.pickerjsonassets.model.CityData
import com.dilip.pickerjsonassets.model.CountryData
import com.dilip.pickerjsonassets.model.StateData

interface LocationDataSource {
    suspend fun getCountries(): List<CountryData>
    suspend fun getStatesForCountry(countryId: Int): List<StateData>
    suspend fun getCitiesForState(stateId: Int): List<CityData>
}
