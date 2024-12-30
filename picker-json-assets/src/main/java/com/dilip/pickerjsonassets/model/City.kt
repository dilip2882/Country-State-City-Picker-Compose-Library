package com.dilip.pickerjsonassets.model

import kotlinx.serialization.Serializable

@Serializable
data class CityData(
    val id: Int,
    val name: String,
    val latitude: String? = null,
    val longitude: String? = null
)