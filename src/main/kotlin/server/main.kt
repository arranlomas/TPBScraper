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
import utils.GenerateBrowseLinkResult
import utils.GenerateSearchLinkResult
import utils.generateBrowseLink
import utils.generateSearchLinkFromQuery

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/search") {
            val urlResult = generateSearchLinkFromQuery(call.request.queryParameters)
            when(urlResult){
                is GenerateSearchLinkResult.InvalidPageNumber -> {call.respond(HttpStatusCode(400, "invalid page number"), "invalid page number")}
                is GenerateSearchLinkResult.InvalidSearchTerm -> { call.respond(HttpStatusCode(400, "invalid search term number"), "invalid search term") }
                is GenerateSearchLinkResult.InvalidSortedBy -> { call.respond(HttpStatusCode(400, "invalid sort by"), "invalid sort by") }
                is GenerateSearchLinkResult.InvalidCategory -> { call.respond(HttpStatusCode(400, "invalid category"), "invalid category") }
                is GenerateSearchLinkResult.Valid -> {
                    val results = JsoupWrapper.parse(urlResult.url)
                    val json = Gson().toJson(results)
                    call.respondText(json, ContentType.Application.Json)
                }
            }
        }
        get("/browse") {
            val urlResult = generateBrowseLink(call.request.queryParameters)
            when(urlResult){
                is GenerateBrowseLinkResult.InvalidPageNumber -> {call.respond(HttpStatusCode(400, "invalid page number"), "invalid page number")}
                is GenerateBrowseLinkResult.InvalidSortedBy -> { call.respond(HttpStatusCode(400, "invalid sort by"), "invalid sort by") }
                is GenerateBrowseLinkResult.InvalidCategory -> { call.respond(HttpStatusCode(400, "invalid category"), "invalid category") }
                is GenerateBrowseLinkResult.Valid -> {
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