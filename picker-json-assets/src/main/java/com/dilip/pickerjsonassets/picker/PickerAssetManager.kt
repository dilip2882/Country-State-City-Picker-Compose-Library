package com.dilip.pickerjsonassets.picker

import android.content.Context
import java.io.InputStream
import javax.inject.Inject

fun interface PickerAssetManager {
    fun open(fileName: String): InputStream
}

class PickerAssetManagerImpl @Inject constructor(
    private val context: Context
) : PickerAssetManager {

    override fun open(fileName: String): InputStream {
        return context.assets.open(fileName)
    }
}
