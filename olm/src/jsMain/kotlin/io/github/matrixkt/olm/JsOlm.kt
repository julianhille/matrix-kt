package io.github.matrixkt.olm

import org.khronos.webgl.Uint8Array

@JsModule("@matrix-org/olm")
@JsNonModule
public external abstract class JJOlm() {
    public class PkSigning {

        public fun free();
        public fun init_with_seed(seed: ByteArray): String;
        public fun generate_seed(): ByteArray;
        public fun sign(message: String): String;
    }
    public companion object {
        public suspend fun init(): JJOlm;
        public val PRIVATE_KEY_LENGTH: Long;
    }
    public class InboundGroupSession {
        public fun pickle(key: ByteArray): String;
        public fun create(session_key: String): String;
        public fun session_id(): String;
        public fun first_known_index(): Long;
        public fun free();
        public fun import_session(session_key: String): String;

        public fun export_session(message_index: Long): String;
        public fun decrypt(message: String): GroupMessage;
        public fun unpickle(key: ByteArray, pickle: String): InboundGroupSession;
    }

    public class Account {
      public fun create(): Account;
      public fun identity_keys(): IdentityKeys;
      public fun mark_keys_as_published();
      public fun max_number_of_one_time_keys(): Long;
      public fun generate_one_time_keys(Int: Int);
      public fun one_time_keys(): OneTimeKeys;
      public fun remove_one_time_keys(session: Session);
      public fun generate_fallback_key();
      public fun fallback_key(): OneTimeKeys;
      public fun pickle(key: Uint8Array ): String;
      public fun free();
      public fun sign(message: String): String;
      public fun unpickle(key: Uint8Array, pickle: String): Account;
    }

    public class OutboundGroupSession {
        public fun free();
        public fun pickle(key: ByteArray): String;
        public fun unpickle(key: ByteArray, pickle: String): OutboundGroupSession;
        public fun create();
        public fun encrypt(plaintext: String): String;
        public fun session_id(): String;
        public fun session_key(): String;
        public fun message_index(): Int;
    }

    public class PkDecryption {
        public fun constructor();
        public fun free();
        public fun init_with_private_key(key: ByteArray): String;
        public fun generate_key(): String;
        public fun get_private_key(): ByteArray;
        public fun pickle(key: ByteArray): String;
        public fun unpickle(key: ByteArray, pickle: String): ByteArray;
        public fun decrypt(ephemeral_key: String, mac: String, ciphertext: String): String;
    }

    public class PkEncryption {
        public fun free();
        public fun set_recipient_key(key: String);
        public fun encrypt(plaintext: String): PkMessage;
    }

    public class SAS {
        public fun free();
        public fun get_pubkey(): String;
        public fun set_their_key(their_key: String);
        public fun generate_bytes(info: String, length: Int): ByteArray;
        public fun calculate_mac(input: String, info: String): String;
        public fun calculate_mac_long_kdf(input: String, info: String): String;
        public fun is_their_key_set(): Boolean;
    }
    public class Session {
        public fun free();
        public fun pickle(key: ByteArray): String;
        public fun unpickle(key: ByteArray, pickle: String);
        public fun create_outbound(account: Account, their_identity_key: String, their_one_time_key: String);
        public fun create_inbound(account: Account, one_time_key_message: String);
        public fun create_inbound_from(account: Account, identity_key: String, one_time_key_message: String,
        );
        public fun session_id(): String;
        public fun has_received_message(): Boolean;
        public fun matches_inbound(one_time_key_message: String): Boolean;
        public fun matches_inbound_from(identity_key: String, one_time_key_message: String): Boolean;
        public fun encrypt(plaintext: String): Message;
        public fun decrypt(message_type: Int, message: String): String;
        public fun describe(): String;
    }
    
    public class Utility {
        public fun free();
        public fun sha256(input: String): String;
        public fun ed25519_verify(key: String, message: String, signature: String);
    }

    
}

/*external abstract class Olm() {

}*/


//public val jsOlm = JJOlm.init().await()