package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*

fun Application.configureRouting() {
    routing {

        staticResources("/content", "myContent")

        get("/") {
            call.respondText("Hello World!")
        }

        get ("/hello") {
            val text = "<h1>Hello ktor!</h1>!"
            val type = ContentType.Text.Html
            call.respondText(text, type)
        }
    }
}