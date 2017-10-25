package utils

import io.ktor.util.ValuesMap
import models.Category
import models.SortType
import models.getNumber
import models.getTpbValue
import java.net.URLEncoder

fun generateTPBLinkFromQuery(map: ValuesMap): GenerateLinkResult {

    val searchTerm: String? = URLEncoder.encode(map["searchTerm"], "UTF-8")
    if (searchTerm.isNullOrEmpty()) return GenerateLinkResult.InvalidSearchTerm()

    val sortedBy: SortType = try{
        SortType.valueOf(map["sortedBy"] ?: "error!!!")
    }catch (e: Exception){
        return GenerateLinkResult.InvalidSortedBy()
    }

    val pageNumber: Int = map["pageNumber"]?.toIntOrNull() ?: -1
    if (pageNumber<0) return GenerateLinkResult.InvalidPageNumber()

    val category: Category = try{
        Category.valueOf(map["category"] ?: "error!!!")
    }catch (e: Exception){
        return GenerateLinkResult.InvalidCategory()
    }


    println("QUERY: searchTerm: $searchTerm")
    println("QUERY: sortedBy: $sortedBy")
    println("QUERY: pageNumber: $pageNumber")
    println("QUERY: category: $category")
    val url = "https://pirateproxy.cam/search/$searchTerm/$pageNumber/${sortedBy.getTpbValue()}/${category.getNumber()}"
    println("PIRATE BAY URL: url: $url")
    return GenerateLinkResult.Valid(url)
}

sealed class GenerateLinkResult{
    class InvalidSearchTerm: GenerateLinkResult()
    class InvalidSortedBy: GenerateLinkResult()
    class InvalidPageNumber: GenerateLinkResult()
    class InvalidCategory: GenerateLinkResult()
    data class Valid(val url: String): GenerateLinkResult()
}