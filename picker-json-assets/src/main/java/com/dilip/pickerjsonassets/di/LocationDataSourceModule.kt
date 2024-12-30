package com.dilip.pickerjsonassets.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import com.dilip.pickerjsonassets.picker.PickerAssetManager
import com.dilip.pickerjsonassets.picker.PickerAssetManagerImpl
import com.dilip.pickerjsonassets.utils.Dispatcher
import com.dilip.pickerjsonassets.utils.PickerDispatchers
import com.dilip.pickerjsonassets.picker.PickerLocationDataSource
import com.dilip.pickerjsonassets.picker.LocationDataSource

@Module
@InstallIn(SingletonComponent::class)
object LocationDataSourceModule {

    @Provides
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
        }
    }

    @Provides
    fun provideDemoAssetManager(@ApplicationContext context: Context): PickerAssetManager {
        return PickerAssetManagerImpl(context)
    }

    @Provides
    fun provideLocationDataSource(
        @Dispatcher(PickerDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        networkJson: Json,
        assets: PickerAssetManager
    ): LocationDataSource {
        return PickerLocationDataSource(ioDispatcher, networkJson, assets)
    }
}
