package com.dilip.countrystatecitypicker.models

data class State(
    val code: String,
    val name: String,
    val cities: List<City> = emptyList()
)
