package utils

import org.jetbrains.ktor.util.ValuesMap
import updateUrl

fun changeMirrorUrl(map: ValuesMap): ChangeMirrorResult{

    val newUrl: String = try{
        map["newMirrorUrl"] ?: return ChangeMirrorResult.Error()
    }catch (e: Exception){
        return ChangeMirrorResult.Error()
    }

    if (newUrl.isEmpty()){
        return ChangeMirrorResult.EmptyUrl()
    }

    if(!newUrl.startsWith("http")){
        return ChangeMirrorResult.RequiresHttp()
    }

    if(newUrl.endsWith("/")){
        return ChangeMirrorResult.RemoveTrailingSlash()
    }



    updateUrl(newUrl)
    println("Mirror updated: $newUrl")
    return ChangeMirrorResult.Success()
}

sealed class ChangeMirrorResult {
    class Success: ChangeMirrorResult()
    class Error: ChangeMirrorResult()
    class EmptyUrl: ChangeMirrorResult()
    class RequiresHttp: ChangeMirrorResult()
    class RemoveTrailingSlash: ChangeMirrorResult()
}
