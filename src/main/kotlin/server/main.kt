package server

import io.ktor.netty.*
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.host.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.pipeline.*
import io.ktor.response.*
import io.ktor.routing.*


fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/") {
            System.out.print("hello")
            call.respondText("My Example Blog2", ContentType.Text.Html)
        }
    }
}

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, watchPaths = listOf("BlogAppKt"), module = Application::module).start()
}