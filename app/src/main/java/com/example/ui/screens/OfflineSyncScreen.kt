package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.DownloadDone
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.FrenchDataRepository
import com.example.data.models.DownloadedContentEntity
import com.example.data.models.PendingSyncEntity
import com.example.ui.theme.ForestPrimary
import com.example.ui.theme.NaturalBorder
import com.example.ui.theme.SageContainer
import com.example.ui.theme.SurfaceLight
import com.example.ui.theme.TerracottaFlame
import com.example.ui.theme.WarmAmber
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.UiLanguage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfflineSyncScreen(viewModel: MainViewModel) {
    val uiLanguage by viewModel.uiLanguage.collectAsStateWithLifecycle()
    val isUrdu = uiLanguage == UiLanguage.URDU

    val userProgress by viewModel.userProgress.collectAsStateWithLifecycle()
    val downloadedList by viewModel.downloadedContentList.collectAsStateWithLifecycle()
    val downloadedIds by viewModel.downloadedIdsSet.collectAsStateWithLifecycle()
    val pendingQueue by viewModel.pendingSyncQueue.collectAsStateWithLifecycle()
    val isSyncing by viewModel.isSyncing.collectAsStateWithLifecycle()

    val isOfflineSimulated = userProgress.isOfflineModeSimulated
    val totalSizeKb = downloadedList.sumOf { it.sizeKb }
    val totalSizeMbFormatted = String.format(Locale.ENGLISH, "%.2f MB", totalSizeKb / 1024.0)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isUrdu) "آف لائن ڈاؤن لوڈز اور سنک center" else "Offline Downloads & Sync",
                        fontWeight = FontWeight.Bold,
                        color = ForestPrimary,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.navigateTo(com.example.ui.viewmodel.AppScreen.HOME) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = ForestPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceLight)
            )
        },
        containerColor = SurfaceLight
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Section 1: Connection & Sync Status Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, NaturalBorder, RoundedCornerShape(24.dp)),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(12.dp)
                                        .clip(CircleShape)
                                        .background(if (isOfflineSimulated) TerracottaFlame else ForestPrimary)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isOfflineSimulated) "Offline Mode" else "Online & Connected",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = if (isOfflineSimulated) TerracottaFlame else ForestPrimary
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = if (isUrdu) "آف لائن سمیلیشن" else "Simulate Offline",
                                    fontSize = 12.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.padding(end = 6.dp)
                                )
                                Switch(
                                    checked = isOfflineSimulated,
                                    onCheckedChange = { viewModel.toggleOfflineModeSimulation() },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = Color.White,
                                        checkedTrackColor = TerracottaFlame,
                                        uncheckedThumbColor = Color.White,
                                        uncheckedTrackColor = SageContainer
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Sync info status
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(SageContainer)
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = if (pendingQueue.isNotEmpty()) {
                                        if (isUrdu) "${pendingQueue.size} پیش رفت مقامی طور پر محفوظ" else "${pendingQueue.size} pending items to sync"
                                    } else {
                                        if (isUrdu) "تمام ڈیٹا بادل سے مطابقت رکھتا ہے" else "All progress synced to cloud"
                                    },
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = ForestPrimary
                                )
                                val dateFormat = SimpleDateFormat("HH:mm, dd MMM", Locale.ENGLISH)
                                val lastSyncStr = dateFormat.format(Date(userProgress.lastSyncedTimestamp))
                                Text(
                                    text = if (isUrdu) "آخری سنک: $lastSyncStr" else "Last sync: $lastSyncStr",
                                    fontSize = 11.sp,
                                    color = Color.Gray
                                )
                            }

                            Button(
                                onClick = { viewModel.syncPendingProgress() },
                                enabled = !isOfflineSimulated && !isSyncing,
                                colors = ButtonDefaults.buttonColors(containerColor = ForestPrimary),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                if (isSyncing) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(16.dp),
                                        color = Color.White,
                                        strokeWidth = 2.dp
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.Sync,
                                        contentDescription = "Sync",
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = if (isUrdu) "سنک کریں" else "Sync",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Section 2: Storage Summary Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (isUrdu) "ڈاؤن لوڈ شدہ آف لائن مواد" else "Downloaded Offline Content",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = ForestPrimary
                        )
                        Text(
                            text = if (isUrdu) "کل آف لائن اسٹوریج: $totalSizeMbFormatted" else "Total Offline Storage: $totalSizeMbFormatted",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(SageContainer)
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "${downloadedList.size} ${if (isUrdu) "پیک" else "Packs"}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = ForestPrimary
                        )
                    }
                }
            }

            // Section 3: Downloaded Items List
            if (downloadedList.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, NaturalBorder, RoundedCornerShape(20.dp)),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.CloudOff,
                                contentDescription = null,
                                tint = WarmAmber,
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = if (isUrdu) "کوئی آف لائن مواد ڈاؤن لوڈ نہیں ہوا" else "No offline content downloaded yet",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = ForestPrimary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (isUrdu) "بغیر انٹرنیٹ سیکھنے کے لیے نیچے دیے گئے اسباق یا گائیڈ ڈک ڈاؤن لوڈ کریں۔" else "Download lessons and vocabulary below to learn anywhere without internet.",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            } else {
                items(downloadedList) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, NaturalBorder, RoundedCornerShape(16.dp)),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.DownloadDone,
                                    contentDescription = null,
                                    tint = ForestPrimary,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = if (isUrdu) item.titleUr else item.titleEn,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = ForestPrimary
                                    )
                                    Text(
                                        text = "${item.contentType} • ${item.sizeKb} KB",
                                        fontSize = 11.sp,
                                        color = Color.Gray
                                    )
                                }
                            }

                            IconButton(onClick = { viewModel.removeDownloadedContent(item.id) }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Remove",
                                    tint = TerracottaFlame,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Section 4: Available Lessons to Download
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isUrdu) "ڈاؤن لوڈ کے لیے دستیاب اسباق" else "Available Lessons to Download",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = ForestPrimary
                )
            }

            items(FrenchDataRepository.lessons) { lesson ->
                val lessonDownloadId = "lesson_${lesson.id}"
                val isDownloaded = downloadedIds.contains(lessonDownloadId)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, NaturalBorder, RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isUrdu) lesson.titleUr else lesson.titleEn,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = ForestPrimary
                            )
                            Text(
                                text = if (isUrdu) lesson.descriptionUr else lesson.descriptionEn,
                                fontSize = 12.sp,
                                color = Color.Gray,
                                maxLines = 1
                            )
                        }

                        if (isDownloaded) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(SageContainer)
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = ForestPrimary,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (isUrdu) "آف لائن تیار" else "Downloaded",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = ForestPrimary
                                )
                            }
                        } else {
                            OutlinedButton(
                                onClick = { viewModel.downloadLesson(lesson) },
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CloudDownload,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (isUrdu) "ڈاؤن لوڈ" else "Download",
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                }
            }

            // Section 5: Themed Flashcard Decks & Phonetic Guide
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isUrdu) "موضوعاتی الفاظ اور صوتیاتی رہنما پیک" else "Themed Decks & Pronunciation Packs",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = ForestPrimary
                )
            }

            // Pronunciation Guide Download Card
            item {
                val guideId = "pronunciation_guide"
                val isGuideDownloaded = downloadedIds.contains(guideId)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, NaturalBorder, RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isUrdu) "اردو فرانسیسی صوتیاتی آڈیو گائیڈ" else "French Phonetic & Pronunciation Guide",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = ForestPrimary
                            )
                            Text(
                                text = if (isUrdu) "تمام 26 حروفِ تہجی کی صوتیاتی آوازیں اور اردو کی مماثلت" else "All 26 French alphabet sounds + Urdu phonetic analogies (1.25 MB)",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }

                        if (isGuideDownloaded) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(SageContainer)
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = ForestPrimary,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (isUrdu) "تیار ہے" else "Ready",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = ForestPrimary
                                )
                            }
                        } else {
                            Button(
                                onClick = { viewModel.downloadPronunciationGuide() },
                                colors = ButtonDefaults.buttonColors(containerColor = ForestPrimary),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Download,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (isUrdu) "ڈاؤن لوڈ گائیڈ" else "Get Guide",
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                }
            }

            // Section 6: Local Sync Log
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isUrdu) "مقامی طور پر محفوظ پیش رفت (Room DB)" else "Unsynced Local Activity Log",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = ForestPrimary
                )
            }

            if (pendingQueue.isEmpty()) {
                item {
                    Text(
                        text = if (isUrdu) "کوئی پینڈنگ سرگرمی نہیں، آپ کی تمام پیش رفت آن لائن محفوظ ہے۔" else "No pending offline activity. All progress synced with server.",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )
                }
            } else {
                items(pendingQueue) { syncItem ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, NaturalBorder, RoundedCornerShape(12.dp)),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(WarmAmber)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = syncItem.details,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = ForestPrimary
                                    )
                                    val timeStr = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(Date(syncItem.timestamp))
                                    Text(
                                        text = if (isUrdu) "مقامی وقت: $timeStr" else "Saved locally at $timeStr",
                                        fontSize = 10.sp,
                                        color = Color.Gray
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(SageContainer)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = if (isUrdu) "پینڈنگ سنک" else "Pending Sync",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TerracottaFlame
                                )
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}
