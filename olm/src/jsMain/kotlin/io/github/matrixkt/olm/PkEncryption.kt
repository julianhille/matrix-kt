package io.github.matrixkt.olm

import kotlin.random.Random

public actual class PkEncryption actual constructor(recipientKey: String) {
    private val ptr = JJOlm.PkEncryption()

    init {
        try {
            ptr.set_recipient_key(recipientKey)
        } catch (e: Exception) {
            clear()
            throw e
        }
    }

    public actual fun clear() {
        ptr.free()
    }

    public actual fun encrypt(plaintext: String, random: Random): PkMessage {
        val encrypted = ptr.encrypt(plaintext)
        //PkMessage(encrypted.cipherText, encrypted.mac, encrypted.ephemeral)
        return encrypted
    }

}
