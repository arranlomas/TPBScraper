package server

import com.google.gson.Gson
import io.ktor.netty.*
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.host.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.pipeline.*
import utils.GenerateLinkResult
import utils.generateTPBLinkFromQuery

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/search") {
            val urlResult = generateTPBLinkFromQuery(call.request.queryParameters)
            when(urlResult){
                is GenerateLinkResult.InvalidPageNumber -> {call.respond(HttpStatusCode(400, "invalid page number"), "invalid page number")}
                is GenerateLinkResult.InvalidSearchTerm -> { call.respond(HttpStatusCode(400, "invalid search term number"), "invalid search term") }
                is GenerateLinkResult.InvalidSortedBy -> { call.respond(HttpStatusCode(400, "invalid sort by"), "invalid sort by") }
                is GenerateLinkResult.InvalidCategory -> { call.respond(HttpStatusCode(400, "invalid category"), "invalid category") }
                is GenerateLinkResult.Valid -> {
                    val results = JsoupWrapper.parse(urlResult.url)
                    val json = Gson().toJson(results)
                    call.respondText(json, ContentType.Application.Json)
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, module = Application::module).start()
}