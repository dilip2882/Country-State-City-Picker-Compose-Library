package com.dilip.pickerjsonassets.picker

import com.dilip.pickerjsonassets.utils.Dispatcher
import com.dilip.pickerjsonassets.utils.PickerDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import com.dilip.pickerjsonassets.model.CityData
import com.dilip.pickerjsonassets.model.CountryData
import com.dilip.pickerjsonassets.model.StateData
import javax.inject.Inject

class PickerLocationDataSource @Inject constructor(
    @Dispatcher(PickerDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: PickerAssetManager,
) : LocationDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getCountries(): List<CountryData> =
        withContext(ioDispatcher) {
            assets.open(LOCATION_ASSET).use(networkJson::decodeFromStream)
        }

    override suspend fun getStatesForCountry(countryId: Int): List<StateData> =
        getCountries().find { it.id == countryId }?.states ?: emptyList()

    override suspend fun getCitiesForState(stateId: Int): List<CityData> =
        getCountries().flatMap { it.states }
            .find { it.id == stateId }?.cities ?: emptyList()

    companion object {
        private const val LOCATION_ASSET = "countries+states+cities.json"
    }
}
