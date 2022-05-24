package io.github.matrixkt.olm

import kotlin.random.Random

public actual class PkDecryption {
    private val ptr: JJOlm.PkDecryption
    public actual val publicKey: String

    private constructor(ptr: JJOlm.PkDecryption, publicKey: String) {
        this.ptr = ptr
        this.publicKey = publicKey
    }

    private constructor(ptr: JJOlm.PkDecryption) {
        this.ptr = ptr
        this.publicKey = ""
    }

    public actual constructor(random: Random) {
        ptr = JJOlm.PkDecryption()
        try {
            this.publicKey = ptr.generate_key()
        } catch (e: Exception) {
            clear()
            throw e
        }
    }

    public actual fun clear() {
        ptr.free()
    }

    public actual val privateKey: ByteArray
        get() {
            return ptr.get_private_key()
        }

    public actual fun decrypt(message: PkMessage): String {
        return ptr.decrypt(message.ephemeralKey, message.mac, message.cipherText)
    }

    public actual fun pickle(key: ByteArray): String {
        return ptr.pickle(key)
    }


    public actual companion object {
        public actual val publicKeyLength: Long
            get() {
                val pkdec = JJOlm.PkDecryption()
                // Todo: Needs error handling for failing and then freeing mem
                val pubKey: String = pkdec.generate_key()
                pkdec.free()
                return pubKey.length.toLong()
            }
        public actual val privateKeyLength: Long get() = JJOlm.PRIVATE_KEY_LENGTH;

        public actual fun fromPrivate(privateKey: ByteArray): PkDecryption {
            val pkdec = JJOlm.PkDecryption()
            val pubKey: String = pkdec.init_with_private_key(privateKey)
            // Todo: Needs error handling for failing and then freeing mem
            return PkDecryption(pkdec, pubKey)
        }

        public actual fun unpickle(key: ByteArray, pickle: String): PkDecryption {
            val pkdec = JJOlm.PkDecryption()
            val privateKey: ByteArray = pkdec.unpickle(key, pickle)
            // Todo: Needs error handling for failing and then freeing mem
            return fromPrivate(privateKey)
        }
    }
}
