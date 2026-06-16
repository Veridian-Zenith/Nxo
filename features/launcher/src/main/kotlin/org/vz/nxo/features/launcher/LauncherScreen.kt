package org.vz.nxo.features.launcher

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel
import org.vz.nxo.core.common.Resource
import org.vz.nxo.core.designsystem.DeepBlack
import org.vz.nxo.core.designsystem.NeonViolet
import org.vz.nxo.core.designsystem.glassmorphism
import org.vz.nxo.features.aiengine.db.AppEntity

@Composable
fun LauncherScreen(viewModel: LauncherViewModel = koinViewModel()) {
    val state by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlack),
        contentAlignment = Alignment.Center
    ) {
        if (!state.isPermissionGranted) {
            Button(onClick = { viewModel.requestUsagePermission() }) {
                Text("Grant Usage Access")
            }
        } else if (state.isLoading && state.apps.isEmpty()) {
            Text("AI Sorting...", color = NeonViolet, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                AppList(apps = state.apps, listState = listState)
                AlphabetSidebar(apps = state.apps, listState = listState)
            }
        }
    }
}

@Composable
fun AppList(apps: List<AppEntity>, listState: LazyListState) {
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 64.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(apps) { app ->
            AppItem(app)
        }
    }
}

@Composable
fun AlphabetSidebar(apps: List<AppEntity>, listState: LazyListState) {
    val alphabet = ('A'..'Z').toList()
    
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(end = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        alphabet.forEach { char ->
            Text(
                text = char.toString(),
                color = Color.White.copy(alpha = 0.4f),
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .clickable {
                        // Scroll logic to be implemented
                    }
            )
        }
    }
}

@Composable
fun AppItem(app: AppEntity) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .glassmorphism()
            .clickable { 
                val launchIntent = context.packageManager.getLaunchIntentForPackage(app.packageName)
                if (launchIntent != null) {
                    context.startActivity(launchIntent)
                }
            }
            .padding(vertical = 12.dp, horizontal = 20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = app.appName,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "AI",
                color = NeonViolet,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0.1f))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }
    }
}
