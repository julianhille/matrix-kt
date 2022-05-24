package io.github.matrixkt.olm

import colm.internal.OlmAccount

public actual class Account private constructor(internal val ptr: OlmAccount) {

    public actual fun clear () {}
    public val identityKeys: IdentityKeys
}