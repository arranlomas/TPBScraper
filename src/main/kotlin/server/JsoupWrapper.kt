package server

import models.TPBTorrent
import org.jsoup.Jsoup

object JsoupWrapper {
    fun parse(url: String): List<TPBTorrent>{
        val doc = Jsoup.connect(url)
                .timeout(10 * 1000)
                .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36")
                .get()
        return ScrapeHTML.scrape(doc)
    }
}