package com.dilip.pickerjsonassets.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StateData(
    val id: Int,
    val name: String,
    @SerialName("state_code")
    val stateCode: String? = null,
    val latitude: String? = null,
    val longitude: String? = null,
    val type: String? = null,
    val cities: List<CityData> = emptyList()
)