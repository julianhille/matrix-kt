package io.github.matrixkt.clientserver.api

import io.github.matrixkt.clientserver.models.filter.Filter
import io.github.matrixkt.clientserver.MatrixRpc
import io.github.matrixkt.clientserver.RpcMethod
import io.ktor.resources.*
import kotlinx.serialization.Serializable

/**
 * Download a filter
 */
public class GetFilter(
    public override val url: Url
) : MatrixRpc.WithAuth<RpcMethod.Get, GetFilter.Url, Nothing, Filter> {
    public override val body: Nothing
        get() = TODO()

    @Resource("_matrix/client/r0/user/{userId}/filter/{filterId}")
    @Serializable
    public class Url(
        /**
         * The user ID to download a filter for.
         */
        public val userId: String,
        /**
         * The filter ID to download.
         */
        public val filterId: String
    )
}
