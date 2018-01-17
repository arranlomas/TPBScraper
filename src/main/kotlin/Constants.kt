private var TPB_URL = "https://thepiratebay.org"

fun updateUrl(newUrl: String){
    TPB_URL = newUrl
}

fun getMirrorUrl() = TPB_URL