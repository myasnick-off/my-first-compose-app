package com.example.myfirstcomposeapp.domain

import com.example.myfirstcomposeapp.R

data class FeedPost(
    val id: Int = 0,
    val title: String = "dev/null",
    val postDate: String = "15:00",
    val profileResId: Int = R.drawable.avatar,
    val contentResId: Int = R.string.post_content,
    val imageResId: Int = R.drawable.some_content,
    val statistic: List<StatisticItem> = StatisticType.entries.map { StatisticItem(it) }
)
