package utils

import TPB_URL
import models.Category
import models.SortType
import org.jetbrains.ktor.util.ValuesMap
import java.net.URLEncoder

fun generateSearchLinkFromQuery(map: ValuesMap): GenerateSearchLinkResult {
    val searchTerm: String? = URLEncoder.encode(map["searchTerm"], "UTF-8")

    val sortedBy: SortType = try{
        SortType.valueOf(map["sortedBy"] ?: "error!!!")
    }catch (e: Exception){
        return GenerateSearchLinkResult.InvalidSortedBy()
    }

    val pageNumber: Int = map["pageNumber"]?.toIntOrNull() ?: -1
    if (pageNumber<0) return GenerateSearchLinkResult.InvalidPageNumber()

    val category: Category = try{
        Category.valueOf(map["category"] ?: "error!!!")
    }catch (e: Exception){
        return GenerateSearchLinkResult.InvalidCategory()
    }

    println("QUERY: searchTerm: $searchTerm")
    println("QUERY: sortedBy: $sortedBy")
    println("QUERY: pageNumber: $pageNumber")
    println("QUERY: category: $category")
    val url = "$TPB_URL/search/$searchTerm/$pageNumber/${sortedBy.getTpbValue()}/${category.getTpbValue()}"
    println("PIRATE BAY URL: url: $url")
    return GenerateSearchLinkResult.Valid(url)
}

sealed class GenerateSearchLinkResult {
    class InvalidSearchTerm: GenerateSearchLinkResult()
    class InvalidSortedBy: GenerateSearchLinkResult()
    class InvalidPageNumber: GenerateSearchLinkResult()
    class InvalidCategory: GenerateSearchLinkResult()
    data class Valid(val url: String): GenerateSearchLinkResult()
}