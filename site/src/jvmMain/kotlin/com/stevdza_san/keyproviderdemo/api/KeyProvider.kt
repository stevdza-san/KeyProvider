package com.stevdza_san.keyproviderdemo.api

import com.stevdza_san.keyproviderdemo.model.Keys
import com.stevdza_san.keyproviderdemo.util.DataEncryption
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Api("provide")
suspend fun keyProvider(context: ApiContext) {
    val firstKey = System.getenv("FIRST_KEY")
    val secondKey = System.getenv("SECOND_KEY")
    try {
        val publicKey = context.req.body?.decodeToString()
        context.logger.debug("PUBLIC KEY: $publicKey")
        if (publicKey != null) {
            val encryptedData = DataEncryption.encrypt(
                context = context,
                data = Json.encodeToString(
                    Keys(
                        firstKey = firstKey,
                        secondKey = secondKey
                    )
                ),
                generatedPublicKey = publicKey.replace("\"", "")
            )
            if (encryptedData != null) {
                context.logger.error("KEYS SUCCESSFULLY PROVIDED.")
                context.res.setBodyText(Json.encodeToString(encryptedData))
            } else {
                context.logger.error("ENCRYPTED DATA IS NULL.")
                context.res.status = 400
            }
        } else {
            context.logger.error("PUBLIC KEY IS NULL.")
            context.res.status = 400
        }
    } catch (e: Exception) {
        context.logger.error(e.message.toString())
        context.res.status = 400
    }
}