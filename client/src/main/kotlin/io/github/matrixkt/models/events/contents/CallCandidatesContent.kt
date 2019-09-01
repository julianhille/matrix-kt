package io.github.matrixkt.models.events.contents

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CallCandidatesContent(
    /**
     * The ID of the call this event relates to.
     */
    @SerialName("call_id")
    val callId: String,

    /**
     * Array of objects describing the candidates.
     */
    val candidates: Candidates,

    /**
     * The version of the VoIP specification this messages adheres to.
     * This specification is version 0.
     */
    val version: Long
) : Content() {
    @Serializable
    data class Candidates(
        /**
         * The SDP media type this candidate is intended for.
         */
        val sdpMid: String,

        /**
         * The index of the SDP 'm' line this candidate is intended for.
         */
        val sdpMLineIndex: Int,

        /**
         * The SDP 'a' line of the candidate.
         */
        val candidate: String
    )
}