package com.dilip.countrystatecitypicker

import com.dilip.countrystatecitypicker.models.City
import com.dilip.countrystatecitypicker.models.Country
import com.dilip.countrystatecitypicker.models.State

object LocationDatabase {
    val countries = listOf(
        Country(
            code = "IN",
            name = "India",
            states = listOf(
                State(
                    code = "MH",
                    name = "Maharashtra",
                    cities = listOf(
                        City("MUM", "Mumbai"),
                        City("PUN", "Pune"),
                        City("NAG", "Nagpur")
                    )
                ),
                State(
                    code = "KA",
                    name = "Karnataka",
                    cities = listOf(
                        City("BLR", "Bangalore"),
                        City("MYS", "Mysore"),
                        City("UBL", "Hubli")
                    )
                ),
                State(
                    code = "DL",
                    name = "Delhi",
                    cities = listOf(
                        City("NDL", "New Delhi"),
                        City("SD", "South Delhi"),
                        City("WD", "West Delhi")
                    )
                ),
                State(
                    code = "UP",
                    name = "Uttar Pradesh",
                    cities = listOf(
                        City("LKO", "Lucknow"),
                        City("KAN", "Kanpur"),
                        City("VAR", "Varanasi")
                    )
                ),
                State(
                    code = "TN",
                    name = "Tamil Nadu",
                    cities = listOf(
                        City("CHN", "Chennai"),
                        City("CBE", "Coimbatore"),
                        City("MDU", "Madurai")
                    )
                )
            )
        ),
        Country(
            code = "US",
            name = "United States",
            states = listOf(
                State(
                    code = "CA",
                    name = "California",
                    cities = listOf(
                        City("LA", "Los Angeles"),
                        City("SF", "San Francisco")
                    )
                ),
                State(
                    code = "NY",
                    name = "New York",
                    cities = listOf(
                        City("NYC", "New York City"),
                        City("BUF", "Buffalo")
                    )
                )
            )
        )
    )
}
