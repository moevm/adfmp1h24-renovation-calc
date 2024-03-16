package com.example.dibuild

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.compose.NavHost
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dibuild.ui.about.AboutCalcScreen
import com.example.dibuild.ui.electrician.ElectricianCalcHelp
import com.example.dibuild.ui.electrician.ElectricianCalcResult
import com.example.dibuild.ui.electrician.ElectricianCalcScreen
import com.example.dibuild.ui.electrician.ElectricianViewModel
import com.example.dibuild.ui.history.HistoryCalcScreen
import com.example.dibuild.ui.history.HistoryViewModel
import com.example.dibuild.ui.laminate.LaminateCalcHelp
import com.example.dibuild.ui.laminate.LaminateCalcResult
import com.example.dibuild.ui.laminate.LaminateCalcScreen
import com.example.dibuild.ui.laminate.LaminateViewModel
import com.example.dibuild.ui.plumbing.PlumbingCalcHelp
import com.example.dibuild.ui.plumbing.PlumbingCalcResult
import com.example.dibuild.ui.plumbing.PlumbingCalcScreen
import com.example.dibuild.ui.plumbing.PlumbingViewModel
import com.example.dibuild.ui.sections.SectionsCalcScreen
import com.example.dibuild.ui.theme.DibuildTheme
import com.example.dibuild.ui.tile.TileCalcHelp
import com.example.dibuild.ui.tile.TileCalcResult
import com.example.dibuild.ui.tile.TileCalcScreen
import com.example.dibuild.ui.wallpapers.WallpapersCalcHelp
import com.example.dibuild.ui.wallpapers.WallpapersCalcResult
import com.example.dibuild.ui.wallpapers.WallpapersCalcScreen

enum class DibuildScreens() {
    Sections,
    About,
    History,
    LaminateCalc,
    LaminateRes,
    LaminateHelp,
    WallpapersCalc,
    WallpapersRes,
    WallpapersHelp,
    TileCalc,
    TileRes,
    TileHelp,
    ElectricianCalc,
    ElectricianRes,
    ElectricianHelp,
    PlumbingCalc,
    PlumbingRes,
    PlumbingHelp,
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DibuildTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DibuildApp()
                }
            }
        }
    }
}

@Composable
fun DibuildApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val historyViewModel = HistoryViewModel()

    val electricianViewModel = ElectricianViewModel()

    val plumbingViewModel = PlumbingViewModel()

    val laminateViewModel = LaminateViewModel()

    NavHost(
        navController = navController,
        startDestination = DibuildScreens.Sections.name,
    ) {
        composable(route = DibuildScreens.Sections.name) {
            SectionsCalcScreen(navController)
        }

        composable(route = DibuildScreens.About.name) {
            AboutCalcScreen(navController)
        }

        composable(route = DibuildScreens.History.name) {
            HistoryCalcScreen(navController, historyViewModel)
        }

        composable(route = DibuildScreens.LaminateCalc.name) {
            LaminateCalcScreen(navController, laminateViewModel)
        }

        composable(route = DibuildScreens.LaminateHelp.name) {
            LaminateCalcHelp(navController)
        }

        composable(route = DibuildScreens.LaminateRes.name) {
            LaminateCalcResult(navController, laminateViewModel)
        }

        composable(route = DibuildScreens.WallpapersCalc.name) {
            WallpapersCalcScreen(navController)
        }

        composable(route = DibuildScreens.WallpapersHelp.name) {
            WallpapersCalcHelp(navController)
        }

        composable(route = DibuildScreens.WallpapersRes.name) {
            WallpapersCalcResult(navController)
        }

        composable(route = DibuildScreens.TileCalc.name) {
            TileCalcScreen(navController)
        }

        composable(route = DibuildScreens.TileHelp.name) {
            TileCalcHelp(navController)
        }

        composable(route = DibuildScreens.TileRes.name) {
            TileCalcResult(navController)
        }

        composable(route = DibuildScreens.ElectricianCalc.name) {
            ElectricianCalcScreen(navController, electricianViewModel, historyViewModel)
        }

        composable(route = DibuildScreens.ElectricianHelp.name) {
            ElectricianCalcHelp(navController)
        }

        composable(route = DibuildScreens.ElectricianRes.name) {
            ElectricianCalcResult(navController, electricianViewModel, historyViewModel)
        }


        composable(route = DibuildScreens.PlumbingCalc.name) {
            PlumbingCalcScreen(navController, plumbingViewModel)
        }

        composable(route = DibuildScreens.PlumbingHelp.name) {
            PlumbingCalcHelp(navController)
        }

        composable(route = DibuildScreens.PlumbingRes.name) {
            PlumbingCalcResult(navController, plumbingViewModel)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DibuildPreview() {
    DibuildTheme {
        DibuildApp()
    }
}