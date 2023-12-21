package com.stevdza_san.keyproviderdemo.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun HomePage() {
    // TODO: Replace the following with your own content
    Div(attrs = {
        style {
            display(DisplayStyle.Flex)
            width(100.percent)
            height(100.vh)
            alignItems(AlignItems.Center)
            justifyContent(JustifyContent.Center)
        }
    }) {
       Text("THIS PAGE INTENTIONALLY LEFT BLANK")
    }
}
