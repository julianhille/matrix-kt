package io.github.matrixkt.clientserver.api

import io.github.matrixkt.clientserver.models.Device
import io.github.matrixkt.clientserver.MatrixRpc
import io.github.matrixkt.clientserver.RpcMethod
import io.ktor.resources.*
import kotlinx.serialization.Serializable

/**
 * Gets information about all devices for the current user.
 */
public class GetDevices(
    public override val url: Url
) : MatrixRpc.WithAuth<RpcMethod.Get, GetDevices.Url, Nothing, GetDevices.Response> {
    public override val body: Nothing
        get() = TODO()

    @Resource("_matrix/client/r0/devices")
    @Serializable
    public class Url

    @Serializable
    public class Response(
        /**
         * A list of all registered devices for this user.
         */
        public val devices: List<Device>? = null
    )
}