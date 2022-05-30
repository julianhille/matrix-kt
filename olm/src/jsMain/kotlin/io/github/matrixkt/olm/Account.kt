package io.github.matrixkt.olm

import org.khronos.webgl.Uint8Array
import kotlin.random.Random

@JsExport
public actual class Account actual constructor(random: Random) {
    internal var ptr: JJOlm.Account = JJOlm.Account()
    init {
        try {
            ptr.create()
        } catch (e: Exception) {
            clear()
            throw e
        }
    }

    internal fun replacePtr(newPtr: JJOlm.Account) {
        clear()
        ptr = newPtr
    }

    public actual val identityKeys: IdentityKeys
        get() {
            return ptr.identity_keys()
        }

    /**
     * Return the largest number of "one time keys" this account can store.
     * @return the max number of "one time keys", -1 otherwise
     */
    public actual val maxNumberOfOneTimeKeys: Long
        get() {
            return ptr.max_number_of_one_time_keys()
        }


    /**
     * Generate a number of new one time keys.
     * If total number of keys stored by this account exceeds [maxNumberOfOneTimeKeys],
     * the old keys are discarded.
     * The corresponding keys are retrieved by [oneTimeKeys].
     * @param numberOfKeys number of keys to generate
     */
    public actual fun generateOneTimeKeys(numberOfKeys: Long, random: Random) {
      ptr.generate_one_time_keys(numberOfKeys.toInt())
    }

    /**
     * Return the "one time keys" in a dictionary.
     * The number of "one time keys", is specified by [generateOneTimeKeys]
     * @sample
     * { "curve25519":
     *  {
     *      "AAAABQ":"qefVZd8qvjOpsFzoKSAdfUnJVkIreyxWFlipCHjSQQg",
     *      "AAAABA":"/X8szMU+p+lsTnr56wKjaLgjTMQQkCk8EIWEAilZtQ8",
     *      "AAAAAw":"qxNxxFHzevFntaaPdT0fhhO7tc7pco4+xB/5VRG81hA",
     *  }
     * }
     * Note: these keys are to be published on the server.
     * @return one time keys in string dictionary.
     */
    public actual val oneTimeKeys: OneTimeKeys
        get() {
            return ptr.one_time_keys()
        }


    /**
     * Remove the "one time keys" that the session used from the account.
     * @param session session instance
     */
    public actual fun removeOneTimeKeys(session: Session) {
        ptr.remove_one_time_keys(session.ptr)
    }

    /**
     * Marks the current set of "one time keys" as being published.
     */
    public actual fun markOneTimeKeysAsPublished() {
        ptr.mark_keys_as_published()
    }

    /**
     * Generates a new fallback key. Only one previous fallback key is stored.
     */
    public actual fun generateFallbackKey(random: Random) {
        ptr.generate_fallback_key()
    }

    /**
     * Get fallback key.
     */
    public actual val fallbackKey: OneTimeKeys
        get() {
            return ptr.fallback_key()
        }

    /**
     * Sign a message with the ed25519 fingerprint key for this account.
     *
     * The signed message is returned by the method.
     * @param message message to sign
     * @return the signed message
     */
    public actual fun sign(message: String): String {
        return ptr.sign(message)
    }


    public actual fun clear () {
       ptr.free();
    }

    public actual fun pickle(key: ByteArray): String {
        return ptr.pickle(Uint8Array(key.toTypedArray()))
    }

    public actual companion object {
        /**
         * Loads an account from a pickled bytes buffer.
         *
         * @see [pickle]
         * @param[key] key used to encrypt
         * @param[pickle] bytes buffer
         */
        public actual fun unpickle(key: ByteArray, pickle: String): Account {
            val result = JJOlm.Account()
            // Todo: Needs error handling for failing and then freeing mem
            result.unpickle(Uint8Array(key.toTypedArray()), pickle)
            val newPtr = Account()
            newPtr.replacePtr(result)
            return newPtr
        }
    }



}