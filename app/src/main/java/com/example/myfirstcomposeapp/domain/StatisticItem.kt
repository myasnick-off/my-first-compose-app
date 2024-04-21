package com.example.myfirstcomposeapp.domain

data class StatisticItem(
    val type: StatisticType,
    val count: Int = 0
)


enum class StatisticType {
    VIEWS, REPOST, COMMENT, LIKE
}