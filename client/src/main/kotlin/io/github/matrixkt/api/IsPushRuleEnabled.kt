package io.github.matrixkt.api

import io.github.matrixkt.models.push.PushRuleKind
import io.github.matrixkt.utils.MatrixRpc
import io.github.matrixkt.utils.RpcMethod
import io.github.matrixkt.utils.resource.Resource
import kotlinx.serialization.Serializable

/**
 * This endpoint gets whether the specified push rule is enabled.
 */
public class IsPushRuleEnabled(
    public override val url: Url
) : MatrixRpc.WithAuth<RpcMethod.Get, IsPushRuleEnabled.Url, Any?, IsPushRuleEnabled.Response> {
    public override val body: Any?
        get() = null

    @Resource("/_matrix/client/r0/pushrules/{scope}/{kind}/{ruleId}/enabled")
    @Serializable
    public class Url(
        /**
         * Either ``global`` or ``device/<profile_tag>`` to specify global
         * rules or device rules for the given ``profile_tag``.
         */
        public val scope: String,
        /**
         * The kind of rule
         */
        public val kind: PushRuleKind,
        /**
         * The identifier for the rule.
         */
        public val ruleId: String
    )

    @Serializable
    public class Response(
        /**
         * Whether the push rule is enabled or not.
         */
        public val enabled: Boolean
    )
}
