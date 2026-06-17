package com.huanchengfly.tieba.post.ui.page.settings.about

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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.huanchengfly.tieba.post.ui.common.theme.compose.ExtendedTheme
import com.huanchengfly.tieba.post.ui.widgets.compose.BackNavigationIcon
import com.huanchengfly.tieba.post.ui.widgets.compose.MyScaffold
import com.huanchengfly.tieba.post.ui.widgets.compose.TitleCentredToolbar
import com.huanchengfly.tieba.post.utils.AppLog

@Composable
fun LogPage(
    navigator: NavHostController,
) {
    var logs by remember { mutableStateOf(AppLog.getLogs()) }
    val listState = rememberLazyListState()
    val clipboardManager = LocalClipboardManager.current

    LaunchedEffect(Unit) { listState.animateScrollToItem(logs.size - 1) }

    MyScaffold(
        topBar = {
            TitleCentredToolbar(
                title = {
                    Text("应用日志", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.h6)
                },
                navigationIcon = {
                    BackNavigationIcon(onBackPressed = { navigator.popBackStack() })
                },
                actions = {
                    IconButton(onClick = {
                        clipboardManager.setText(AnnotatedString(logs.joinToString("\n") {
                            "[${it.time}] ${it.tag}: ${it.message}"
                        }))
                    }) {
                        Icon(Icons.Rounded.Share, contentDescription = "复制日志")
                    }
                    IconButton(onClick = {
                        AppLog.clear()
                        logs = emptyList()
                    }) {
                        Icon(Icons.Rounded.Delete, contentDescription = "清空")
                    }
                }
            )
        }
    ) {
        if (logs.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(32.dp),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
            ) {
                Text("暂无日志", color = ExtendedTheme.colors.textSecondary, fontSize = 16.sp)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
                state = listState
            ) {
                items(logs) { entry ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp)
                    ) {
                        Text(
                            "[${entry.time}]",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 10.sp,
                            color = ExtendedTheme.colors.textSecondary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            entry.tag,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = ExtendedTheme.colors.accent
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            entry.message,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 10.sp,
                            color = ExtendedTheme.colors.text
                        )
                    }
                }
            }
        }
    }
}
