package models

enum class SortType{
    NAME,
    NAME_DESCENDING,
    UPLOAD,
    UPLOAD_DESCENDING,
    SIZE,
    SIZE_DESCENDING,
    SEEDS,
    SEEDS_DESCENDING,
    LEECHERS,
    LEECHERS_DESCENDING,
    ULED_BY,
    ULED_BY_DESCENDING,
    TYPE,
    TYPE_DESCENDING,
    DEFAULT
}

fun SortType.getTpbValue(): Int{
    return when(this){
        SortType.DEFAULT -> 99
        else -> this.ordinal+1
    }
}
