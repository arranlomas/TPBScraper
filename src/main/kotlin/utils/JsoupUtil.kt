package utils

import org.jsoup.Jsoup

fun getJsoupDoc(url: String) = Jsoup.connect(url)
        .timeout(200 * 1000)
        .userAgent("Mozilla")
        .get()