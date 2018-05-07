package server

import models.TPBTorrent
import org.jsoup.nodes.Document

object ScrapeHTML {
    fun scrape(doc: Document): List<TPBTorrent> {
        val results = mutableListOf<TPBTorrent>()
        // get all table rows
        val tableRows = doc.getElementsByTag("tr")
        try {
            for (element in tableRows) {

                if (!element.hasClass("header")) {
                    val tpbTorrent = TPBTorrent()

                    try {
                        // first td, lets get the categories
                        val td1 = element.children().select("td").first()
                        val categories = td1.children().select("a")
                        tpbTorrent.CategoryParent = categories[0].text()
                        tpbTorrent.Category = categories[1].text()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    try {
                        // second td, get some more info
                        // get the torrent name
                        val td2 = element.children().select("td")[1]
                        val aTorrentName = td2.children().select("a").first()
                        tpbTorrent.Name = aTorrentName.text()

                        // get the file link
                        tpbTorrent.Link = aTorrentName.attr("href")

                        try {
                            // get the magnet
                            val torrentMagnet = td2.children().select("a")[1]
                            tpbTorrent.Magnet = torrentMagnet.attr("href")

                            // get date, size, and uploader
                            val details = td2.select("font").first()
                            val torrentInfo = details.text()
                            val splitInfo = torrentInfo.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

                            tpbTorrent.Uploaded = splitInfo[0].substring(9)
                            tpbTorrent.Size = splitInfo[1].substring(6)
                            tpbTorrent.Uled = splitInfo[2].substring(9)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }


                    try {
                        // third td, get the seeds
                        val td3 = element.children().select("td")[2]
                        tpbTorrent.Seeds = Integer.parseInt(td3.text())
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    try {
                        val td4 = element.children().select("td")[3]
                        tpbTorrent.Leechers = Integer.parseInt(td4.text())
                    } catch (e: IndexOutOfBoundsException) {
                        tpbTorrent.Leechers = -1
                    }


                    results.add(tpbTorrent)
                }

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return results.toList()
    }
}
