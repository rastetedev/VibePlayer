package com.raulastete.vibeplayer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raulastete.vibeplayer.presentation.features.home.HomeScreen
import com.raulastete.vibeplayer.presentation.features.permission.PermissionScreen
import com.raulastete.vibeplayer.presentation.features.player.PlayerScreen
import com.raulastete.vibeplayer.presentation.features.scanner.ScannerScreen
import com.raulastete.vibeplayer.presentation.features.search.SearchScreen


@Composable
fun RootNavGraph() {

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