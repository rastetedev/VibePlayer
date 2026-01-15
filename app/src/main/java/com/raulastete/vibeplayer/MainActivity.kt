package com.raulastete.vibeplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raulastete.vibeplayer.features.home.HomeScreen
import com.raulastete.vibeplayer.features.permission.PermissionScreen
import com.raulastete.vibeplayer.features.player.PlayerScreen
import com.raulastete.vibeplayer.features.scanner.ScannerScreen
import com.raulastete.vibeplayer.features.search.SearchScreen
import com.raulastete.vibeplayer.ui.theme.VibePlayerTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VibePlayerTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = Destination.Permission) {
                    composable<Destination.Permission> {
                        PermissionScreen(
                            onPermissionGranted = {
                                navController.navigate(Destination.Home) {
                                    popUpTo(Destination.Permission)
                                }
                            }
                        )
                    }

                    composable<Destination.Home> {
                        HomeScreen(
                            onScanActionClick = {
                                navController.navigate(Destination.Scanner)
                            },
                            onSearchActionClick = {
                                navController.navigate(Destination.Search)
                            },
                            onTrackItemClick = { trackItemId ->
                                navController.navigate(Destination.Player(trackItemId))
                            }
                        )
                    }

                    composable<Destination.Scanner> {
                        ScannerScreen(
                            onBackActionClick = {
                                navController.navigateUp()
                            }
                        )
                    }

                    composable<Destination.Search> {
                        SearchScreen(
                            onNavigateBack = {
                                navController.navigateUp()
                            },
                            navigateToPlayer = { musicTrackId ->
                                navController.navigate(Destination.Player(musicTrackId))
                            }
                        )
                    }

                    composable<Destination.Player> {
                        PlayerScreen(
                            onBackActionClick = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}

sealed interface Destination {

    @Serializable
    object Home : Destination

    @Serializable
    object Permission : Destination

    @Serializable
    object Scanner : Destination

    @Serializable
    object Search : Destination

    @Serializable
    data class Player(val trackItemId: String) : Destination
}
