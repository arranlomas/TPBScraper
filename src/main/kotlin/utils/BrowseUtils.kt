package utils

import models.Category
import models.SortType
import org.jetbrains.ktor.util.ValuesMap


fun generateBrowseLink(map: ValuesMap): GenerateBrowseLinkResult{

    val sortedBy: SortType = try{
        SortType.valueOf(map["sortedBy"] ?: "error!!!")
    }catch (e: Exception){
        return GenerateBrowseLinkResult.InvalidSortedBy()
    }

    val pageNumber: Int = map["pageNumber"]?.toIntOrNull() ?: -1
    if (pageNumber<0) return GenerateBrowseLinkResult.InvalidPageNumber()

    val category: Category = try{
        Category.valueOf(map["category"] ?: "error!!!")
    }catch (e: Exception){
        return GenerateBrowseLinkResult.InvalidCategory()
    }

    if (category == Category.All) return GenerateBrowseLinkResult.InvalidCategory()

    println("QUERY: sortedBy: $sortedBy")
    println("QUERY: pageNumber: $pageNumber")
    println("QUERY: category: $category")
    val url = "https://pirateproxy.cam/browse/${category.getTpbValue()}/$pageNumber/${sortedBy.getTpbValue()}"
    println("PIRATE BAY URL: url: $url")
    return GenerateBrowseLinkResult.Valid(url)
}

sealed class GenerateBrowseLinkResult {
    class InvalidSortedBy: GenerateBrowseLinkResult()
    class InvalidPageNumber: GenerateBrowseLinkResult()
    class InvalidCategory: GenerateBrowseLinkResult()
    data class Valid(val url: String): GenerateBrowseLinkResult()
}

