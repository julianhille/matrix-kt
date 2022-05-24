package io.github.matrixkt.olm

@JsModule("@matrix-org/olm")
@JsNonModule
public external abstract class Olm() {
    // some declarations here

    public abstract class Account {
      public fun create()
    }
}

/*external abstract class Olm() {

}*/


val olm = Olm.init().await()