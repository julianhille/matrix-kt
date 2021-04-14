package io.github.matrixkt.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class Presence {
    @SerialName("offline")
    OFFLINE,
    @SerialName("online")
    ONLINE,
    @SerialName("unavailable")
    UNAVAILABLE;
}
