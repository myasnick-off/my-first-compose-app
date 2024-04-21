package com.example.myfirstcomposeapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myfirstcomposeapp.R
import com.example.myfirstcomposeapp.domain.FeedPost
import com.example.myfirstcomposeapp.domain.StatisticItem
import com.example.myfirstcomposeapp.domain.StatisticType

@Composable
fun PostCard(
    post: FeedPost,
    onViewClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit,
    onLikeClickListener: (StatisticItem) -> Unit
) {
    Card() {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            PostHeader(post)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(post.contentResId))
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = post.imageResId),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(8.dp))
            Statistics(
                post.statistic,
                onViewClickListener,
                onShareClickListener,
                onCommentClickListener,
                onLikeClickListener
            )
        }
    }
}

@Composable
private fun PostHeader(post: FeedPost) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = post.profileResId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = post.title)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.postDate)
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun Statistics(
    statisticList: List<StatisticItem>,
    onViewClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit,
    onLikeClickListener: (StatisticItem) -> Unit
) {
    Row {
        Row(modifier = Modifier.weight(1f)) {
            val viewsItem = statisticList.getItemByType(StatisticType.VIEWS)
            IconWithCounter(iconResId = R.drawable.ic_views_count, count = viewsItem.count) {
                onViewClickListener(viewsItem)
            }
        }
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceBetween) {
            val repostItem = statisticList.getItemByType(StatisticType.REPOST)
            val commentItem = statisticList.getItemByType(StatisticType.COMMENT)
            val likeItem = statisticList.getItemByType(StatisticType.LIKE)
            IconWithCounter(iconResId = R.drawable.ic_repost, count = repostItem.count) {
                onShareClickListener(repostItem)
            }
            IconWithCounter(iconResId = R.drawable.ic_comment, count = commentItem.count) {
                onCommentClickListener(commentItem)
            }
            IconWithCounter(iconResId = R.drawable.ic_like, count = likeItem.count) {
                onLikeClickListener(likeItem)
            }
        }
    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem =
    find { it.type == type } ?: error("Unreachable state!")

@Composable
private fun IconWithCounter(
    iconResId: Int,
    count: Int,
    itemClickListener: () -> Unit
) {
    Row(
        modifier = Modifier.clickable { itemClickListener() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = iconResId), contentDescription = null)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = count.toString())
    }
}