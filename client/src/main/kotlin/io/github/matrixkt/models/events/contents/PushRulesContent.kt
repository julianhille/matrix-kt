package io.github.matrixkt.models.events.contents

import io.github.matrixkt.models.push.Ruleset
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Describes all push rules for this user.
 */
@SerialName("m.push_rules")
@Serializable
public data class PushRulesContent(
    /**
     * The global ruleset
     */
    val global: Ruleset? = null
)
