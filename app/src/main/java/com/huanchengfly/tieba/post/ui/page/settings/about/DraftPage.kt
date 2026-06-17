package com.huanchengfly.tieba.post.ui.page.settings.about

import com.huanchengfly.tieba.post.utils.AppLog

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.huanchengfly.tieba.post.R
import com.huanchengfly.tieba.post.models.database.Draft
import com.huanchengfly.tieba.post.toastShort
import com.huanchengfly.tieba.post.ui.common.theme.compose.ExtendedTheme
import com.huanchengfly.tieba.post.ui.widgets.compose.MyScaffold
import com.huanchengfly.tieba.post.ui.widgets.compose.TitleCentredToolbar
import com.huanchengfly.tieba.post.utils.DateTimeUtils
import org.litepal.LitePal
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DraftPage(
    navigator: NavHostController,
) {
    val context = LocalContext.current
    var drafts by remember {
        mutableStateOf(
            try { LitePal.order("createTime desc").find(Draft::class.java) }
            catch (_: Exception) { emptyList() }
        )
    }

    MyScaffold(
        topBar = {
            TitleCentredToolbar(
                title = {
                    Text(
                        text = "草稿箱",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h6
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navigator.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {
        if (drafts.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
            ) {
                Text(
                    "暂无草稿",
                    color = ExtendedTheme.colors.textSecondary,
                    fontSize = 16.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(drafts, key = { it.hash ?: it.hashCode().toString() }) { draft ->
                    DraftItem(
                        draft = draft,
                        onClick = {
                            val hasMeta = draft.forumId > 0 && draft.threadId > 0
                            if (hasMeta) {
                                navigator.navigate(
                                    "reply/${draft.threadId}?forumId=${draft.forumId}&forumName=${draft.forumName ?: ""}&postId=${draft.postId}"
                                )
                            } else {
                                // Debug: 显示草稿字段值帮助排查
                                val dbg = "fId=${draft.forumId} tId=${draft.threadId} pId=${draft.postId}"
                                context.toastShort("草稿不含帖子上下文($dbg)，内容已复制")
                                draft.content?.let { content ->
                                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                    clipboard.setPrimaryClip(ClipData.newPlainText("draft", content))
                                }
                            }
                        },
                        onDelete = {
                            try {
                                LitePal.deleteAll(Draft::class.java, "hash = ?", draft.hash)
                                drafts = LitePal.order("createTime desc").find(Draft::class.java)
                                context.toastShort("已删除")
                            } catch (_: Exception) {
                                context.toastShort("删除失败")
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun DraftItem(
    draft: Draft,
    onClick: () -> Unit,
    onDelete: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = ExtendedTheme.colors.text.copy(alpha = 0.04f),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                val forumNameValue = draft.forumName
                if (!forumNameValue.isNullOrEmpty()) {
                    Text(
                        forumNameValue,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = ExtendedTheme.colors.accent
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
                Text(
                    draft.contentPreview.ifNullOrEmpty { draft.content?.take(200) } ?: "草稿",
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 13.sp,
                    color = ExtendedTheme.colors.textSecondary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    formatTime(draft.createTime),
                    fontSize = 11.sp,
                    color = ExtendedTheme.colors.textSecondary.copy(alpha = 0.6f)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = onDelete
            ) {
                Icon(
                    Icons.Rounded.Delete,
                    contentDescription = "删除草稿",
                    tint = ExtendedTheme.colors.textSecondary,
                )
            }
        }
    }
}

private fun formatTime(time: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - time
    return when {
        diff < 60_000 -> "刚刚"
        diff < 3600_000 -> "${diff / 60_000} 分钟前"
        diff < 86400_000 -> "${diff / 3600_000} 小时前"
        else -> SimpleDateFormat("MM-dd HH:mm", Locale.getDefault()).format(Date(time))
    }
}

private fun String?.ifNullOrEmpty(default: () -> String?): String? {
    return if (this.isNullOrEmpty()) default() else this
}
