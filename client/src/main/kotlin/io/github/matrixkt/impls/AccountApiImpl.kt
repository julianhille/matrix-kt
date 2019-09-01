package io.github.matrixkt.impls

import io.github.matrixkt.apis.AccountApi
import io.github.matrixkt.models.*
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.response.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.io.core.use
import kotlin.reflect.KProperty0

internal class AccountApiImpl(private val client: HttpClient, private val accessTokenProp: KProperty0<String>) : AccountApi {
    private inline val accessToken: String get() = accessTokenProp.get()

    override suspend fun getAccount3PIDs(): List<ThirdPartyIdentifier> {
        val response = client.get<HttpResponse>("/_matrix/client/r0/account/3pid") {
            header("Authorization", "Bearer $accessToken")
        }

        response.use {
            when (it.status) {
                HttpStatusCode.OK -> return it.receive<Get3PidsResponse>().threepids
                else -> throw it.receive<MatrixError>()
            }
        }
    }

    override suspend fun add3PID(params: Add3PidRequest) {
        val response = client.post<HttpResponse>("/_matrix/client/r0/account/3pid") {
            header("Authorization", "Bearer $accessToken")
            contentType(ContentType.Application.Json)
            body = params
        }

        response.use {
            when (it.status) {
                HttpStatusCode.OK -> return it.receive()
                else -> throw it.receive<MatrixError>()
            }
        }
    }

    override suspend fun delete3pidFromAccount(params: Remove3PidRequest): Remove3PidResponse {
        val response = client.post<HttpResponse>("/_matrix/client/r0/account/3pid/delete") {
            header("Authorization", "Bearer $accessToken")
            contentType(ContentType.Application.Json)
            body = params
        }

        response.use {
            when (it.status) {
                HttpStatusCode.OK -> return it.receive()
                else -> throw it.receive<MatrixError>()
            }
        }
    }

    override suspend fun getTokenOwner(): String {
        val response = client.get<HttpResponse>("/_matrix/client/r0/account/whoami") {
            header("Authorization", "Bearer $accessToken")
        }

        response.use {
            when (it.status) {
                HttpStatusCode.OK -> return it.receive<WhoAmIResponse>().userId
                else -> throw it.receive<MatrixError>()
            }
        }
    }

    override suspend fun changePassword(params: ChangePasswordRequest) {
        val response = client.post<HttpResponse>(path = "_matrix/client/r0/account/password") {
            header("Authorization", "Bearer $accessToken")
            contentType(ContentType.Application.Json)
            body = params
        }

        response.use {
            when (it.status) {
                HttpStatusCode.OK -> return it.receive()
                else -> throw it.receive<MatrixError>()
            }
        }
    }

    override suspend fun deactivateAccount(params: DeactivateRequest): DeactivateResponse {
        val response = client.post<HttpResponse>(path = "_matrix/client/r0/account/deactivate") {
            header("Authorization", "Bearer $accessToken")
            contentType(ContentType.Application.Json)
            body = params
        }

        response.use {
            when (it.status) {
                HttpStatusCode.OK -> return it.receive()
                else -> throw it.receive<MatrixError>()
            }
        }
    }

    override suspend fun requestTokenToResetPasswordEmail(params: EmailValidationRequest): TokenValidationResponse {
        val response = client.post<HttpResponse>(path = "_matrix/client/r0/account/password/email/requestToken") {
            contentType(ContentType.Application.Json)
            body = params
        }

        response.use {
            when (it.status) {
                HttpStatusCode.OK -> return it.receive()
                else -> throw it.receive<MatrixError>()
            }
        }
    }

    override suspend fun requestTokenToResetPasswordMSISDN(params: MSISDNValidationRequest): TokenValidationResponse {
        val response = client.post<HttpResponse>(path = "_matrix/client/r0/account/password/msisdn/requestToken") {
            contentType(ContentType.Application.Json)
            body = params
        }

        response.use {
            when (it.status) {
                HttpStatusCode.OK -> return it.receive()
                else -> throw it.receive<MatrixError>()
            }
        }
    }

    override suspend fun requestTokenTo3PIDEmail(params: EmailValidationRequest): TokenValidationResponse {
        val response = client.post<HttpResponse>("/_matrix/client/r0/account/3pid/email/requestToken") {
            contentType(ContentType.Application.Json)
            body = params
        }

        response.use {
            when (it.status) {
                HttpStatusCode.OK -> return it.receive()
                else -> throw it.receive<MatrixError>()
            }
        }
    }

    override suspend fun requestTokenTo3PIDMSISDN(params: MSISDNValidationRequest): TokenValidationResponse {
        val response = client.post<HttpResponse>("/_matrix/client/r0/account/3pid/msisdn/requestToken") {
            contentType(ContentType.Application.Json)
            body = params
        }

        response.use {
            when (it.status) {
                HttpStatusCode.OK -> return it.receive()
                else -> throw it.receive<MatrixError>()
            }
        }
    }
}