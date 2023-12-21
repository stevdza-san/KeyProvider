package com.stevdza_san.keyproviderdemo

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.core.KobwebApp

@App
@Composable
fun MyApp(content: @Composable () -> Unit) {
    KobwebApp {
        content()
    }
}
