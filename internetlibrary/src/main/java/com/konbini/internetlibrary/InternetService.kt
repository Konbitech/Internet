package com.konbini.internetlibrary

import kotlinx.coroutines.flow.Flow

interface InternetService {
    fun observe(): Flow<Status>

    enum class Status {
        Available,
        Losing,
        Lost,
        Unavailable,
        CapabilitiesChanged,
        LinkPropertiesChanged,
        BlockedStatusChanged
    }
}