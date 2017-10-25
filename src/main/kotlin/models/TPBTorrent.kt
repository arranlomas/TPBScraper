package models

data class TPBTorrent (
        var Name: String? = null,
        var Magnet: String? = null,
        var Link: String? = null,
        var Uploaded: String? = null,
        var Size: String? = null,
        var Uled: String? = null,
        var Seeds: Int = 0,
        var Leechers: Int = 0,
        var CategoryParent: String? = null,
        var Category: String? = null,
        var ImdbID: String? = null,
        var CoverImage: String? = null,
        var blackoutShowing: Boolean = false
)