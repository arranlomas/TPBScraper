package server

import models.TPBTorrent
import utils.getJsoupDoc

object JsoupWrapper {
    fun parse(url: String): List<TPBTorrent>? {
        return try {
            val doc = getJsoupDoc(url)
            ScrapeHTML.scrape(doc)
        } catch (e: Exception) {
            null
        }
    }
}