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
import androidx.navigation.NavDestination
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dibuild.ui.about.AboutCalcScreen
import com.example.dibuild.ui.electrician.ElectricianCalcScreen
import com.example.dibuild.ui.history.HistoryCalcScreen
import com.example.dibuild.ui.laminate.LaminateCalcHelp
import com.example.dibuild.ui.laminate.LaminateCalcResult
import com.example.dibuild.ui.laminate.LaminateCalcScreen
import com.example.dibuild.ui.plumbing.PlumbingCalcScreen
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

    NavHost(
        navController = navController,
        startDestination = DibuildScreens.Sections.name,
    ) {
        composable(route = DibuildScreens.Sections.name){
            SectionsCalcScreen(navController)
        }

        composable(route = DibuildScreens.About.name){
            AboutCalcScreen(navController)
        }

        composable(route = DibuildScreens.History.name){
            HistoryCalcScreen(navController)
        }

        composable(route = DibuildScreens.LaminateCalc.name){
            LaminateCalcScreen(navController)
        }

        composable(route = DibuildScreens.LaminateHelp.name){
            LaminateCalcHelp(navController)
        }

        composable(route = DibuildScreens.LaminateRes.name){
            LaminateCalcResult(navController)
        }

        composable(route = DibuildScreens.WallpapersCalc.name){
            WallpapersCalcScreen(navController)
        }

        composable(route = DibuildScreens.WallpapersHelp.name){
            WallpapersCalcHelp(navController)
        }

        composable(route = DibuildScreens.WallpapersRes.name){
            WallpapersCalcResult(navController)
        }

        composable(route = DibuildScreens.TileCalc.name){
            TileCalcScreen(navController)
        }

        composable(route = DibuildScreens.TileHelp.name){
            TileCalcHelp(navController)
        }

        composable(route = DibuildScreens.TileRes.name){
            TileCalcResult(navController)
        }

        composable(route = DibuildScreens.ElectricianCalc.name){
            ElectricianCalcScreen(navController)
        }

        composable(route = DibuildScreens.PlumbingCalc.name){
            PlumbingCalcScreen(navController)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DibuildTheme {
        DibuildApp()
    }
}