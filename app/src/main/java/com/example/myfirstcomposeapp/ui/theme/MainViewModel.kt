package com.example.myfirstcomposeapp.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfirstcomposeapp.domain.FeedPost
import com.example.myfirstcomposeapp.domain.InstagramModel
import com.example.myfirstcomposeapp.domain.StatisticItem
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val instagramList = mutableListOf<InstagramModel>().apply {
        repeat(100) {
            add(
                InstagramModel(
                    id = it,
                    title = "Title #$it",
                    isFollowed = Random.nextBoolean()
                )
            )
        }
    }

    private val _models = MutableLiveData<List<InstagramModel>>(instagramList)
    val models: LiveData<List<InstagramModel>> = _models

    private val _feedPostData = MutableLiveData(FeedPost())
    val feedPostData: LiveData<FeedPost> = _feedPostData

    fun changeFollowingState(model: InstagramModel) {
        val modifiedList = models.value.orEmpty().toMutableList()
        modifiedList.replaceAll {
            if (it.id == model.id) {
                it.copy(isFollowed = !it.isFollowed)
            } else {
                it
            }
        }
        _models.value = modifiedList
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