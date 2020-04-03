package io.github.matrixkt.models.events.contents.room

import io.github.matrixkt.models.events.HistoryVisibility
import io.github.matrixkt.models.events.contents.Content
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This event controls whether a user can see the events that happened in a room from before they joined.
 */
@SerialName("m.room.history_visibility")
@Serializable
data class HistoryVisibilityContent(
    /**
     * Who can see the room history. One of: ["invited", "joined", "shared", "world_readable"]
     */
    @SerialName("history_visibility")
    val historyVisibility: HistoryVisibility
) : Content()
