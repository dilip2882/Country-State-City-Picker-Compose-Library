package com.dilip.pickerjsonassets.utils

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val pickerDispatcher: PickerDispatchers)

enum class PickerDispatchers {
    Default,
    IO,
}
