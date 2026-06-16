package org.vz.nxo.features.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import org.vz.nxo.core.designsystem.NxoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    NxoTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Settings") })
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Gemini API Key
                OutlinedTextField(
                    value = uiState.geminiApiKey,
                    onValueChange = { viewModel.setGeminiApiKey(it) },
                    label = { Text("Gemini API Key") },
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Obtain key from Google AI Studio",
                    style = MaterialTheme.typography.bodySmall
                )

                // AI Sorting Toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Enable AI Sorting")
                    Switch(
                        checked = uiState.aiSortingEnabled,
                        onCheckedChange = { viewModel.setAiSortingEnabled(it) }
                    )
                }

                // Top Apps Count
                Text("Top Apps to Display: ${uiState.topAppsCount}")
                Slider(
                    value = uiState.topAppsCount.toFloat(),
                    onValueChange = { viewModel.setTopAppsCount(it.toInt()) },
                    valueRange = 1f..10f,
                    steps = 8
                )
            }
        }
    }
}
