package com.raulastete.vibeplayer.core

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatchers {

    val ioDispatcher : CoroutineDispatcher
    val mainDispatcher: CoroutineDispatcher
    val defaultDispatcher: CoroutineDispatcher
}