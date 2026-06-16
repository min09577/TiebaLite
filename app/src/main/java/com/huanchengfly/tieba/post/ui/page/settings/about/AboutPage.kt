package com.huanchengfly.tieba.post.ui.page.settings.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.OpenInNew
import androidx.compose.material.icons.rounded.SystemUpdate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.huanchengfly.tieba.post.BuildConfig
import com.huanchengfly.tieba.post.R
import com.huanchengfly.tieba.post.toastShort
import com.huanchengfly.tieba.post.ui.common.theme.compose.ExtendedTheme
import com.huanchengfly.tieba.post.ui.widgets.compose.BackNavigationIcon
import com.huanchengfly.tieba.post.ui.widgets.compose.MyScaffold
import com.huanchengfly.tieba.post.ui.widgets.compose.TitleCentredToolbar
import com.huanchengfly.tieba.post.utils.appPreferences
import com.huanchengfly.tieba.post.utils.launchUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONObject

data class GithubRelease(
    val tagName: String = "",
    val htmlUrl: String = "",
    val name: String = "",
    val downloadUrl: String = "",
)

sealed class UpdateState {
    data object Idle : UpdateState()
    data object Checking : UpdateState()
    data class Found(val release: GithubRelease) : UpdateState()
    data object UpToDate : UpdateState()
    data class Error(val message: String) : UpdateState()
}

@Composable
fun AboutPage(
    navigator: NavHostController,
) {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    var clickCount by remember { mutableIntStateOf(0) }
    var updateState by remember { mutableStateOf<UpdateState>(UpdateState.Idle) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val currentVersion = BuildConfig.VERSION_NAME

    fun checkUpdate() {
        updateState = UpdateState.Checking
        scope.launch {
            updateState = try {
                withContext(Dispatchers.IO) {
                    val conn = URL("https://api.github.com/repos/min09577/TiebaLite/releases/latest")
                        .openConnection() as HttpURLConnection
                    conn.setRequestProperty("Accept", "application/vnd.github+json")
                    conn.connectTimeout = 10000
                    conn.readTimeout = 10000
                    val text = conn.inputStream.bufferedReader().readText()
                    val json = JSONObject(text)
                    val tagName = json.optString("tag_name", "")
                    val htmlUrl = json.optString("html_url", "")
                    val name = json.optString("name", "")
                    val asset = json.optJSONArray("assets")?.optJSONObject(0)
                    val downloadUrl = asset?.optString("browser_download_url", "") ?: ""
                    val release = GithubRelease(
                        tagName = tagName,
                        htmlUrl = htmlUrl,
                        name = name,
                        downloadUrl = downloadUrl
                    )
                    val remoteTag = tagName.removePrefix("v").trim()
                    val localTag = currentVersion.removePrefix("v").substringBefore("+").trim()
                    if (remoteTag != localTag && remoteTag.isNotEmpty()) {
                        UpdateState.Found(release)
                    } else {
                        UpdateState.UpToDate
                    }
                }
            } catch (e: Exception) {
                UpdateState.Error(e.message ?: "检查失败")
            }
        }
    }

    MyScaffold(
        backgroundColor = Color.Transparent,
        topBar = {
            TitleCentredToolbar(
                title = {
                    Text(
                        text = stringResource(id = R.string.title_about),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h6
                    )
                },
                navigationIcon = {
                    BackNavigationIcon(onBackPressed = { navigator.popBackStack() })
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // App Icon
            Image(
                painter = rememberDrawablePainter(
                    drawable = context.getDrawable(R.mipmap.ic_launcher_new)
                ),
                contentDescription = null,
                modifier = Modifier.size(88.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = ExtendedTheme.colors.text
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = currentVersion,
                style = MaterialTheme.typography.body2,
                color = ExtendedTheme.colors.textSecondary,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        val currentTime = System.currentTimeMillis()
                        if (currentTime - lastClickTime < 500) clickCount++
                        else clickCount = 1
                        lastClickTime = currentTime
                        if (clickCount >= 7) {
                            clickCount = 0
                            context.appPreferences.showExperimentalFeatures =
                                !context.appPreferences.showExperimentalFeatures
                            val msg = if (context.appPreferences.showExperimentalFeatures)
                                R.string.toast_experimental_features_enabled
                            else R.string.toast_experimental_features_disabled
                            context.toastShort(msg)
                        }
                    }
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Update Check Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(16.dp),
                backgroundColor = ExtendedTheme.colors.text.copy(alpha = 0.06f),
                elevation = 0.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (val state = updateState) {
                        UpdateState.Idle -> {
                            Text(
                                "检查是否有新版本",
                                color = ExtendedTheme.colors.textSecondary,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = { checkUpdate() },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = ExtendedTheme.colors.accent,
                                    contentColor = Color.White
                                )
                            ) {
                                Icon(
                                    Icons.Rounded.SystemUpdate,
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("检查更新", fontWeight = FontWeight.Medium)
                            }
                        }
                        UpdateState.Checking -> {
                            CircularProgressIndicator(
                                modifier = Modifier.size(32.dp),
                                strokeWidth = 3.dp,
                                color = ExtendedTheme.colors.accent
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                "正在检查...",
                                color = ExtendedTheme.colors.textSecondary,
                                fontSize = 14.sp
                            )
                        }
                        is UpdateState.Found -> {
                            Icon(
                                Icons.Rounded.SystemUpdate,
                                contentDescription = null,
                                tint = ExtendedTheme.colors.accent,
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                "发现新版本",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = ExtendedTheme.colors.accent
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                state.release.name.ifEmpty { state.release.tagName },
                                color = ExtendedTheme.colors.textSecondary,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                Button(
                                    onClick = {
                                        try {
                                            val url = state.release.downloadUrl.ifEmpty {
                                                state.release.htmlUrl
                                            }
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                            context.startActivity(intent)
                                        } catch (e: Exception) {
                                            context.toastShort("无法打开链接")
                                        }
                                    },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = ExtendedTheme.colors.accent,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text("下载更新", fontWeight = FontWeight.Medium)
                                }
                                OutlinedButton(
                                    onClick = {
                                        try {
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(state.release.htmlUrl))
                                            context.startActivity(intent)
                                        } catch (e: Exception) {
                                            context.toastShort("无法打开链接")
                                        }
                                    },
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Icon(
                                        Icons.Rounded.OpenInNew,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("GitHub", fontWeight = FontWeight.Medium)
                                }
                            }
                        }
                        UpdateState.UpToDate -> {
                            Icon(
                                Icons.Rounded.CheckCircle,
                                contentDescription = null,
                                tint = Color(0xFF4CAF50),
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                "已是最新版本",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = ExtendedTheme.colors.text
                            )
                        }
                        is UpdateState.Error -> {
                            Text(
                                state.message,
                                color = MaterialTheme.colors.error,
                                fontSize = 13.sp,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TextButton(onClick = { checkUpdate() }) {
                                Text("重试")
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // GitHub Repo Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(16.dp),
                backgroundColor = ExtendedTheme.colors.text.copy(alpha = 0.06f),
                elevation = 0.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "项目地址",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = ExtendedTheme.colors.textSecondary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextButton(
                        onClick = {
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/min09577/TiebaLite"))
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                context.toastShort("无法打开链接")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            "github.com/min09577/TiebaLite",
                            color = ExtendedTheme.colors.accent,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            Icons.Rounded.OpenInNew,
                            contentDescription = null,
                            tint = ExtendedTheme.colors.accent,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.tip_about, BuildConfig.VERSION_NAME),
                style = MaterialTheme.typography.caption,
                color = ExtendedTheme.colors.textSecondary
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
