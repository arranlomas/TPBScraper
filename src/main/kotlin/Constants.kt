private var TPB_URL = "https://thepiratebay.rocks/"

fun updateUrl(newUrl: String){
    TPB_URL = newUrl
}

fun getMirrorUrl() = TPB_URL