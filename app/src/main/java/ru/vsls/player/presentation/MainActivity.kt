package ru.vsls.player.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ru.vsls.player.Utils
import ru.vsls.player.presentation.local.LocalScreen
import ru.vsls.player.presentation.player.PlayerScreen
import ru.vsls.player.presentation.remote.RemoteScreen
import ru.vsls.player.ui.theme.PlayerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlayerTheme {
                val navController = rememberNavController()
                Surface {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {

                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route

                            val shouldShowBottomBar = when {
                                currentRoute?.startsWith(Screen.PlayerScreen.route) == true -> false // Скрываем для плеера
                                currentRoute in setOf(
                                    Screen.LocalScreen.route,
                                    Screen.RemoteScreen.route
                                ) -> true

                                else -> false
                            }

                            if (shouldShowBottomBar) {
                                NavigationBar {
                                    Utils.BottomNavItems.forEach { topLevelRoute ->
                                        NavigationBarItem(
                                            icon = {
                                                Icon(
                                                    topLevelRoute.icon,
                                                    contentDescription = topLevelRoute.title
                                                )
                                            },
                                            label = { Text(topLevelRoute.title) },
                                            selected = currentRoute == topLevelRoute.route,
                                            onClick = {
                                                navController.navigate(topLevelRoute.route) {
                                                    popUpTo(navController.graph.findStartDestination().id) {
                                                        saveState = true
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }
                                            }
                                        )
                                    }
                                }
                            }
                        },
                    ) { innerPadding ->
                        NavHost(
                            navController,
                            startDestination = Screen.RemoteScreen.route,
                            Modifier.padding(innerPadding)
                        ) {
                            composable(Screen.RemoteScreen.route) { RemoteScreen(navController) }
                            composable(Screen.LocalScreen.route) { LocalScreen() }

                            composable(
                                Screen.PlayerScreen.route + "/{trackId}?position={position}",
                                arguments = listOf(navArgument("trackId") {
                                    type = NavType.LongType
                                }, navArgument("position") {
                                    type = NavType.IntType
                                })
                            ) { backStackEntry ->
                                val trackId = backStackEntry.arguments?.getLong("trackId") ?: 0L
                                PlayerScreen(onClick = { navController.popBackStack() })
                            }

                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlayerTheme {

    }
}