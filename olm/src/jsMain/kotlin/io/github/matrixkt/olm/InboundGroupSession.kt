package io.github.matrixkt.olm

import colm.internal.OlmInboundGroupSession

public actual class InboundGroupSession private constructor(internal val ptr: OlmInboundGroupSession) {

    public actual fun clear () {}
    public val identityKeys: IdentityKeys



    public actual fun pickle(key: ByteArray): String {
        return this.pickle(key)
    }

    public actual companion object {
        /**
         * Loads an account from a pickled bytes buffer.
         *
         * @see [pickle]
         * @param[key] key used to encrypt
         * @param[pickle] bytes buffer
         */
        public actual fun unpickle(key: ByteArray, pickle: String): InboundGroupSession {
            Olm().InboundGroupSession.unpickle(key, pickle)
        }
    }
}