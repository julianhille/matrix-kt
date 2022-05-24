package io.github.matrixkt.olm

import kotlin.random.Random


public actual class InboundGroupSession private constructor(internal val ptr: JJOlm.InboundGroupSession) {


    public actual constructor(sessionKey: String): this(JJOlm.InboundGroupSession()) {
        try {
            ptr.create(sessionKey)
        } catch (e: Exception) {
            clear()
            throw e
        }
    }


    public actual fun clear () {
        ptr.free();
    }


    /**
     * Retrieve the base64-encoded identifier for this inbound group session.
     * @return the session ID
     */
    public actual val sessionId: String get() = ptr.session_id()


    /**
     * Provides the first known index.
     * @return the first known index.
     */
    public actual val firstKnownIndex: Long get() = ptr.first_known_index()

    /**
     * Tells if the session is verified.
     * @return true if the session is verified
     */
    public actual val isVerified: Boolean
        get()  {
            try {
                // Todo: has no equivalent on js olm
                return true
            } catch (_: Exception) {
                return false
            }
        }

    /**
     * Export the session from a message index as String.
     * @param messageIndex the message index
     * @return the session as String
     */
    public actual fun export(messageIndex: Long): String {
        return ptr.export_session(messageIndex);
    }

    /**
     * Decrypt the message passed in parameter.
     * In case of error, null is returned and an error message description is provided in aErrorMsg.
     * @param message the message to be decrypted
     * @return the decrypted message information
     */
    public actual fun decrypt(message: String): GroupMessage {
      return ptr.decrypt(message)
    }

    public actual fun pickle(key: ByteArray): String {
        return ptr.pickle(key);
    }

    public actual companion object {
        public actual fun import(sessionKey: String): InboundGroupSession {
            // Todo: Needs error handling for failing and then freeing mem
            val session = JJOlm.InboundGroupSession()
            session.import_session(sessionKey)
            return InboundGroupSession(session)
        }


        /**
         * Loads an Inbound group session from a pickled bytes buffer.
         *
         * @see [pickle]
         * @param[key] key used to encrypt
         * @param[pickle] bytes buffer
         */
        public actual fun unpickle(key: ByteArray, pickle: String): InboundGroupSession {
            // Todo: Needs error handling for failing and then freeing mem
            val session = JJOlm.InboundGroupSession()
            session.unpickle(key, pickle)
            return InboundGroupSession(session)
        }
    }
}