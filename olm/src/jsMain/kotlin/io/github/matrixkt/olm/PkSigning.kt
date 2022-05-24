package io.github.matrixkt.olm


public actual class PkSigning actual constructor(seed: ByteArray) {
    private val ptr = JJOlm.PkSigning()

    public actual val publicKey: String

    init {
        this.publicKey = ptr.init_with_seed(seed)
    }

    public actual fun clear() {
        ptr.free()
    }

    public actual fun sign(message: String): String {
        return ptr.sign(message)
    }

    public actual companion object {
        public actual val seedLength: Long
          get() {
            return JJOlm.PkSigning().generate_seed().size.toLong()
        }
    }
}
