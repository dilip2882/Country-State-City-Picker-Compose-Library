package com.dilip.corepicker.models

data class Country(
    val code: String,
    val name: String,
    val states: List<State> = emptyList()
)
