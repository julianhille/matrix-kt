package io.github.matrixkt.models.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Membership {
    @SerialName("invite")
	INVITE,

    @SerialName("join")
	JOIN,

    @SerialName("knock")
	KNOCK,

    @SerialName("leave")
	LEAVE,

    @SerialName("ban")
	BAN;
}
