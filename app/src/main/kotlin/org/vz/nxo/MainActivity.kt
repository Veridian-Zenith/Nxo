package org.vz.nxo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.androidx.compose.koinViewModel
import org.vz.nxo.core.designsystem.NxoTheme
import org.vz.nxo.features.launcher.LauncherScreen
import org.vz.nxo.features.launcher.LauncherViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            NxoTheme {
                val viewModel: LauncherViewModel = koinViewModel()
                LauncherScreen(viewModel)
            }
        }
    }
}
