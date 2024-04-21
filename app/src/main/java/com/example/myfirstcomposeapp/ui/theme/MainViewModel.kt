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

    private val postsList = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(FeedPost(id = it))
        }
    }

    private val _models = MutableLiveData<List<InstagramModel>>(instagramList)
    val models: LiveData<List<InstagramModel>> = _models

    private val _feedPostData = MutableLiveData<List<FeedPost>>(postsList)
    val feedPostData: LiveData<List<FeedPost>> = _feedPostData

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

    fun deleteProfileCard(model: InstagramModel) {
        val modifiedList = models.value.orEmpty().toMutableList()
        modifiedList.remove(model)
        _models.value = modifiedList
    }

    fun updatePostStatistics(feedPost: FeedPost, item: StatisticItem) {
        val postList = feedPostData.value?.toMutableList() ?: mutableListOf()
        val currentPostIndex = postList.indexOf(feedPost)

        if (currentPostIndex > -1) {
            val oldStatistic = feedPost.statistic
            val newStatistic = oldStatistic.toMutableList().apply {
                replaceAll { currentItem ->
                    if (currentItem.type == item.type) {
                        currentItem.copy(count = currentItem.count + 1)
                    } else currentItem
                }
            }
            postList[currentPostIndex] = feedPost.copy(statistic = newStatistic)
            _feedPostData.value = postList
        }
    }

    fun deleteFeedPost(feedPost: FeedPost) {
        val postList = feedPostData.value?.toMutableList() ?: mutableListOf()
        _feedPostData.value = postList.apply {
            remove(feedPost)
        }
    }
}