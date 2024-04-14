package com.example.myfirstcomposeapp.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfirstcomposeapp.domain.FeedPost
import com.example.myfirstcomposeapp.domain.StatisticItem

class MainViewModel: ViewModel() {

    private val _isFallowing = MutableLiveData<Boolean>()
    val isFollowing: LiveData<Boolean> = _isFallowing

    private val _feedPostData = MutableLiveData(FeedPost())
    val feedPostData: LiveData<FeedPost> = _feedPostData

    fun changeFollowingState() {
        val currentState = isFollowing.value ?: false
        _isFallowing.value = !currentState
    }

    fun updatePostStatistics(item: StatisticItem) {
        val oldStatistic = feedPostData.value?.statistic ?: error("Illegal State!")
        val newStatistic = oldStatistic.toMutableList().apply {
            replaceAll { currentItem ->
                if (currentItem.type == item.type) {
                    currentItem.copy(count = currentItem.count + 1)
                } else currentItem
            }
        }
        _feedPostData.value = feedPostData.value?.copy(statistic = newStatistic)
    }
}