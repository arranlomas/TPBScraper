package utils

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

fun getJsoupDoc(url: String) = Jsoup.connect(url)
        .timeout(10 * 1000)
        .userAgent("Mozilla")
        .get()