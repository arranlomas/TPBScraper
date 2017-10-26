import com.google.gson.Gson
import server.JsoupWrapper
import utils.GenerateBrowseLinkResult
import utils.GenerateSearchLinkResult
import utils.generateBrowseLink
import utils.generateSearchLinkFromQuery

import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.CallLogging
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.response.respond
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.Routing
import org.jetbrains.ktor.routing.get

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/") {
            call.respondText("hello", ContentType.Text.Plain)
        }
        get("/status") {
            call.respondText("hello", ContentType.Text.Plain)
        }
        get("/search") {
            val urlResult = generateSearchLinkFromQuery(call.request.queryParameters)
            when (urlResult) {
                is GenerateSearchLinkResult.InvalidPageNumber -> {
                    call.respond(HttpStatusCode(400, "invalid page number"))
                }
                is GenerateSearchLinkResult.InvalidSearchTerm -> {
                    call.respond(HttpStatusCode(400, "invalid search term number"))
                }
                is GenerateSearchLinkResult.InvalidSortedBy -> {
                    call.respond(HttpStatusCode(400, "invalid sort by"))
                }
                is GenerateSearchLinkResult.InvalidCategory -> {
                    call.respond(HttpStatusCode(400, "invalid category"))
                }
                is GenerateSearchLinkResult.Valid -> {
                    val results = JsoupWrapper.parse(urlResult.url)
                    val json = Gson().toJson(results.first())
                    call.respondText(json, ContentType.Application.Json)
                }
            }
        }
        get("/browse") {
            val urlResult = generateBrowseLink(call.request.queryParameters)
            when (urlResult) {
                is GenerateBrowseLinkResult.InvalidPageNumber -> {
                    call.respond(HttpStatusCode(400, "invalid page number"))
                }
                is GenerateBrowseLinkResult.InvalidSortedBy -> {
                    call.respond(HttpStatusCode(400, "invalid sort by"))
                }
                is GenerateBrowseLinkResult.InvalidCategory -> {
                    call.respond(HttpStatusCode(400, "invalid category"))
                }
                is GenerateBrowseLinkResult.Valid -> {
                    val results = JsoupWrapper.parse(urlResult.url)
                    val json = Gson().toJson(results)
                    call.respondText(json, ContentType.Application.Json)
                }
            }
        }
    }
}