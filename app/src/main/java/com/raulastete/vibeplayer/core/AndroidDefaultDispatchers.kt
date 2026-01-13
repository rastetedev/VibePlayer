package com.raulastete.vibeplayer.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AndroidDefaultDispatchers : AppDispatchers {
    override val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO
    override val mainDispatcher: CoroutineDispatcher
        get() = Dispatchers.Main
    override val defaultDispatcher: CoroutineDispatcher
        get() = Dispatchers.Default

}