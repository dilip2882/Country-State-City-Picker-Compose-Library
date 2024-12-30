package com.dilip.corepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.dilip.corepicker.models.City
import com.dilip.corepicker.models.Country
import com.dilip.corepicker.models.HeaderTitles
import com.dilip.corepicker.models.State

data class LocationPickerCustomization(
    val showFlags: Boolean = true,
    val showCodes: Boolean = true,
    val searchEnabled: Boolean = true,
    val searchHint: String = "Search",
    val showClearIcon: Boolean = true,
    val itemPadding: Int = 10,
    val dividerColor: Color = Color.LightGray,
    val headerTitles: HeaderTitles = HeaderTitles(),
    val selectionMode: SelectionMode = SelectionMode.HIERARCHICAL,
    val itemShape: Shape = RoundedCornerShape(4.dp),
    val backgroundColor: Color = Color.White,
    val textColor: Color = Color.Black,
    val countryItemContent: @Composable (Country) -> Unit = { country ->
        DefaultLocationItem(name = country.name, code = country.code)
    },
    val stateItemContent: @Composable (State) -> Unit = { state ->
        DefaultLocationItem(name = state.name, code = state.code)
    },
    val cityItemContent: @Composable (City) -> Unit = { city ->
        DefaultLocationItem(name = city.name, code = city.code)
    },
    val maxSelectionLimit: Int = 5
)

enum class SelectionMode {
    HIERARCHICAL, // Navigate through country -> state -> city
    DIRECT, // Direct selection of any level
    MULTI_SELECT // Allow selecting multiple items
}

@Composable
private fun DefaultLocationItem(name: String, code: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name)
        Text(text = code, style = MaterialTheme.typography.bodySmall)
    }
}
