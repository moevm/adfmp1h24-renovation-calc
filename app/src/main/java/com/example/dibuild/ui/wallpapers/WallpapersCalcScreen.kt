package com.example.dibuild.ui.wallpapers

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.History
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dibuild.DibuildScreens
import com.example.dibuild.model.CalculatedResult
import com.example.dibuild.model.CalculatedResults
import com.example.dibuild.model.Help
import com.example.dibuild.model.Param
import com.example.dibuild.model.ParamsBlock
import com.example.dibuild.ui.UITools.CalculatePageBottomBar
import com.example.dibuild.ui.UITools.CalculateResultsPageBottomBar
import com.example.dibuild.ui.UITools.InfoPageBottomBar
import com.example.dibuild.ui.UITools.inputCalcCard
import com.example.dibuild.ui.UITools.resultCalcCard
import com.example.dibuild.ui.history.HistoryViewModel
import com.example.dibuild.ui.theme.DibuildTheme

@SuppressLint("UnrememberedMutableState")
@Composable
fun WallpapersCalcScreen(
    navController: NavHostController,
    wallpapersViewModel: WallpapersViewModel = WallpapersViewModel(),
    historyViewModel: HistoryViewModel = HistoryViewModel()
) {
    val wallpapersUiState by wallpapersViewModel.uiState.collectAsState()

    var room_length by remember { mutableStateOf(wallpapersUiState.room_length) }
    var room_width by remember { mutableStateOf(wallpapersUiState.room_width) }
    var room_height by remember { mutableStateOf(wallpapersUiState.room_height) }
    var roll_length by remember { mutableStateOf(wallpapersUiState.roll_length) }
    var roll_width by remember { mutableStateOf(wallpapersUiState.roll_width) }
    var roll_price by remember { mutableStateOf(wallpapersUiState.roll_price) }
    var gluepackage_weight by remember { mutableStateOf(wallpapersUiState.gluepackage_weight) }
    var gluepackage_price by remember { mutableStateOf(wallpapersUiState.gluepackage_price) }
    var glue_consumption by remember { mutableStateOf(wallpapersUiState.glue_consumption) }

    val WallpaperParams = listOf(
        ParamsBlock(
            "Параметры помещения", listOf(
                Param("Длина комнаты", room_length, "м"){
                    room_length = it
                    wallpapersViewModel.updateRoomLength(it)
                },
                Param("Ширина комнаты", room_width, "м"){
                    room_width = it
                    wallpapersViewModel.updateRoomWidth(it)
                },
                Param("Высота комнаты", room_height, "м"){
                    room_height = it
                    wallpapersViewModel.updateRoomHeight(it)
                },
            )
        ),

        ParamsBlock(
            "Параметры обоев", listOf(
                Param("Длина рулона", roll_length, "м"){
                    roll_length = it
                    wallpapersViewModel.updateRollLength(it)
                },
                Param("Ширина рулона", roll_width, "м"){
                    roll_width = it
                    wallpapersViewModel.updateRollWidth(it)
                },
                Param("Цена за рулон", roll_price, "₽"){
                    roll_price = it
                    wallpapersViewModel.updateRollPrice(it)
                },
                )
        ),

        ParamsBlock(
            "Параметры клея", listOf(
                Param("Масса упаковки\nклея", gluepackage_weight, "г"){
                    gluepackage_weight = it
                    wallpapersViewModel.updateGluepackageWeight(it)
                },
                Param("Цена упаковки\nклея", gluepackage_price, "₽"){
                    gluepackage_price = it
                    wallpapersViewModel.updateGluepackagePrice(it)
                },
                Param("Расход клея", glue_consumption, "г/м2"){
                    glue_consumption = it
                    wallpapersViewModel.updateGlueConsumption(it)
                },
            )
        )
    )

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            IconButton(onClick = { navController.navigate(DibuildScreens.History.name) }) {
                Icon(
                    imageVector = Icons.Sharp.History,
                    contentDescription = null,
                    tint = Color(0xFF01ACC6),
                    modifier = Modifier.size(50.dp)
                )
            }

            Text(
                text = "Обои",
                fontSize = 30.sp,
            )

            IconButton(onClick = { navController.navigate(DibuildScreens.WallpapersHelp.name) }) {
                Icon(
                    imageVector = Icons.Sharp.Info,
                    contentDescription = null,
                    tint = Color(0xFF01ACC6),
                    modifier = Modifier.size(50.dp)
                )
            }
        }


        Box(modifier = Modifier.weight(1f)) {
            inputCalcCard(paramsBlockList = WallpaperParams)
        }

        Box() {
            CalculatePageBottomBar(navController,
                DibuildScreens.WallpapersRes.name,
                { wallpapersViewModel.validate() },
                {
                    wallpapersViewModel.clearValues()
                    navController.popBackStack()
                    navController.navigate(DibuildScreens.WallpapersCalc.name)
                },
                { historyViewModel.updateHistory(wallpapersViewModel.getTileCalcHistory()) },
                { wallpapersViewModel.countTotal() })
        }


    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun WallpapersCalcResult(
    navController: NavHostController,
    wallpapersViewModel: WallpapersViewModel = WallpapersViewModel(),
    historyViewModel: HistoryViewModel = HistoryViewModel()
) {

    val WallpaperCalculatedResult = listOf(
        CalculatedResults(
            listOf(
                CalculatedResult("Количество рулонов обоев",
                    "%s".format(wallpapersViewModel.uiState.value.rolls_num), "шт"),
                CalculatedResult("Стоимость рулонов",
                    "%.2f".format(wallpapersViewModel.uiState.value.rolls_total), "₽"),
                CalculatedResult("Излишки рулонов обоев",
                    "%.2f".format(wallpapersViewModel.uiState.value.rolls_excess), "шт"),
            )
        ),

        CalculatedResults(
            listOf(
                CalculatedResult("Количество упаковок клея",
                    "%s".format(wallpapersViewModel.uiState.value.gluepackages_num), "шт"),
                CalculatedResult("Стоимость клея",
                    "%.2f".format(wallpapersViewModel.uiState.value.glue_total), "₽"),
                CalculatedResult("Излишки упаковок клея",
                    "%.2f".format(wallpapersViewModel.uiState.value.glue_excess), "шт"),
            )
        ),

        CalculatedResults(
            listOf(
                CalculatedResult("Итоговая стоимость",
                    "%.2f".format(wallpapersViewModel.uiState.value.total), "₽")
            )
        )
    )

    Column {
        Box(
            contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxSize()
        ) {
            resultCalcCard(WallpaperCalculatedResult)

            Column(verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ){
                CalculateResultsPageBottomBar(navController,
                    DibuildScreens.WallpapersCalc.name,
                    historyViewModel)
            }
        }
    }
}

@Composable
fun WallpapersCalcHelp(
    navController: NavHostController
){

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(10.dp)
            ) {

                Text(
                    text = "Справка\n\nОбои",
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center
                )

            }
            val WallpaperHelpParams = listOf(
                Help(
                    "Общая площадь стен = 2*(длина комнаты + ширина комнаты)*высота комнаты"
                ),

                Help(
                    "Требуемое количество рулонов = Общая площадь стен / (ширина рулона * длина рулона) (округляем вверх)"
                ),

                Help(
                    "Стоимость рулонов = Требуемое количество рулонов * Цена за рулон"
                ),

                Help(
                    "Излишек = Количество рулонов - Количество рулонов без округления вверх"
                ),

//                Help(
//                    "Излишек стоимости = Излишек * Цена за рулон"
//                ),

                Help(
                    "Количество упаковок клея = Общая площадь стен * Расход клея / масса одной упаковки клея (Округлить вверх)"
                ),

                Help(
                    "Стоимость клея = Количество упаковок клея * Стоимость одной упаковки клея"
                ),

                Help(
                    "Излишек = Количество упаковок клея - Количество упаковок клея без округления вверх"
                ),

                Help(
                    "Общая стоимость = Стоимость рулонов + Стоимость клея"
                ),

//                Help(
//                    "Излишек стоимости = Излишек * Стоимость одной упаковки клея"
//                )
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.weight(1f)) {
                items(WallpaperHelpParams) {
                    Text(
                        text = it.info, modifier = Modifier.padding(10.dp), fontSize = 25.sp
                    )
                }
            }

            Box() {
                InfoPageBottomBar(navController)
            }
        }
    }
}

@Preview
@Composable
fun WallpapersCalcResultPreview() {
    DibuildTheme {
        WallpapersCalcResult(rememberNavController())
    }
}

@Preview
@Composable
fun WallpapersCalcPreview() {
    DibuildTheme {
        WallpapersCalcScreen(rememberNavController())
    }
}

@Preview
@Composable
fun WallpapersCalcHelpPreview() {
    DibuildTheme {
        WallpapersCalcHelp(rememberNavController())
    }
}
