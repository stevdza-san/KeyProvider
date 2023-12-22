package com.stevdza_san.keyproviderdemo.util

import com.varabyte.kobweb.api.ApiContext
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec
import java.util.Base64
import javax.crypto.Cipher

private const val TRANSFORMATION = "RSA/ECB/PKCS1Padding"

object DataEncryption {
    fun encrypt(
        context: ApiContext,
        data: String,
        generatedPublicKey: String
    ): String? {
        return try {
            val publicKeyBytes = Base64.getDecoder().decode(generatedPublicKey)
            val publicKeySpec = X509EncodedKeySpec(publicKeyBytes)

            val keyFactory = KeyFactory.getInstance("RSA")
            val publicKey = keyFactory.generatePublic(publicKeySpec)

            val cipher = Cipher.getInstance(TRANSFORMATION)
            cipher.init(Cipher.ENCRYPT_MODE, publicKey)

            val result = cipher.doFinal(data.toByteArray())
            Base64.getEncoder().encodeToString(result)
        } catch (e: Exception) {
            context.logger.error("ERROR WHILE ENCRYPTING: $e")
            null
        }
    }
}