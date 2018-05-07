import com.google.gson.Gson
import server.JsoupWrapper

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
import utils.*

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/") {
            call.respondText("hello", ContentType.Text.Plain)
        }
        get("/status") {
            try {
                getJsoupDoc(getMirrorUrl())
                call.respondText("current url is ${getMirrorUrl()} \n and everything seems ok!", ContentType.Text.Plain)
            } catch (e: Exception) {
                call.respond(HttpStatusCode(503, "Cannot connect to pirate bay mirror"))
            }
        }
        get("/search") {
            val urlResult = generateSearchLinkFromQuery(call.request.queryParameters)
            return@get when (urlResult) {
                is GenerateSearchLinkResult.InvalidPageNumber -> call.respond(HttpStatusCode(400, "invalid page number"))
                is GenerateSearchLinkResult.InvalidSearchTerm -> call.respond(HttpStatusCode(400, "invalid search term number"))
                is GenerateSearchLinkResult.InvalidSortedBy -> call.respond(HttpStatusCode(400, "invalid sort by"))
                is GenerateSearchLinkResult.InvalidCategory -> call.respond(HttpStatusCode(400, "invalid category"))
                is GenerateSearchLinkResult.Valid -> {
                    val results = JsoupWrapper.parse(urlResult.url)
                    if (results == null){
                        call.respond(HttpStatusCode(503, "Error parsing results"))
                    }else {
                        val json = Gson().toJson(results)
                        call.respondText(json, ContentType.Application.Json)
                    }

                }
            }
        }
        get("/browse") {
            val urlResult = generateBrowseLink(call.request.queryParameters)
            return@get when (urlResult) {
                is GenerateBrowseLinkResult.InvalidPageNumber -> call.respond(HttpStatusCode(400, "invalid page number"))
                is GenerateBrowseLinkResult.InvalidSortedBy -> call.respond(HttpStatusCode(400, "invalid sort by"))
                is GenerateBrowseLinkResult.InvalidCategory -> call.respond(HttpStatusCode(400, urlResult.message ?: "invalid category"))
                is GenerateBrowseLinkResult.Valid -> {
                    val results = JsoupWrapper.parse(urlResult.url)
                    if (results == null){
                        call.respond(HttpStatusCode(503, "Error parsing results"))
                    }else {
                        val json = Gson().toJson(results)
                        call.respondText(json, ContentType.Application.Json)
                    }
                }
            }
        }
        get("/changeMirrorUrl") {
            val urlResult = changeMirrorUrl(call.request.queryParameters)
            return@get when (urlResult) {
                is ChangeMirrorResult.Success -> {
                    val json = Gson().toJson("Success")
                    call.respondText(json, ContentType.Application.Json)
                }
                is ChangeMirrorResult.Error -> call.respond(HttpStatusCode(400, "missing newMirrorUrl query parameter"))
                is ChangeMirrorResult.EmptyUrl -> call.respond(HttpStatusCode(400, "new url cannot be empty"))
                is ChangeMirrorResult.RequiresHttp -> call.respond(HttpStatusCode(400, "url must start with http"))
                is ChangeMirrorResult.RemoveTrailingSlash -> call.respond(HttpStatusCode(400, "remove / from then end of the url"))
            }
        }
    }
}