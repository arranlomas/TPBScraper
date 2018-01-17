package server

import models.TPBTorrent
import org.jsoup.Jsoup

object JsoupWrapper {
    fun parse(url: String): List<TPBTorrent>{
        val doc = Jsoup.connect(url)
                .timeout(10 * 1000)
                .userAgent("Mozilla")
                .get()
        return ScrapeHTML.scrape(doc)
    }
}