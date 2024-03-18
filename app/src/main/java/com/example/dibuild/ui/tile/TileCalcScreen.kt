package com.example.dibuild.ui.tile

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
import androidx.compose.material3.ExperimentalMaterial3Api
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
fun TileCalcScreen(
    navController: NavHostController,
    tileViewModel: TileViewModel = TileViewModel(),
    historyViewModel: HistoryViewModel = HistoryViewModel()
) {
    val tileUiState by tileViewModel.uiState.collectAsState()

    var room_length by remember { mutableStateOf(tileUiState.room_length) }
    var room_width by remember { mutableStateOf(tileUiState.room_width) }
    var tile_length by remember { mutableStateOf(tileUiState.tile_length) }
    var tile_width by remember { mutableStateOf(tileUiState.tile_width) }
    var tile_package_num by remember { mutableStateOf(tileUiState.tile_package_num) }
    var tile_price by remember { mutableStateOf(tileUiState.tile_price) }
    var gluepackage_weight by remember { mutableStateOf(tileUiState.gluepackage_weight) }
    var gluepackage_price by remember { mutableStateOf(tileUiState.gluepackage_price) }
    var glue_consumption by remember { mutableStateOf(tileUiState.glue_consumption) }

    val TileParams = listOf(
        ParamsBlock(
            "Параметры помещения", listOf(
                Param("Длина комнаты", room_length, "м"){
                    room_length = it
                    tileViewModel.updateRoomLength(it)
                },
                Param("Ширина комнаты", room_width, "м"){
                    room_width = it
                    tileViewModel.updateRoomWidth(it)
                },
            )
        ),

        ParamsBlock(
            "Параметры плитки", listOf(
                Param("Длина одной\nплитки", tile_length, "м"){
                    tile_length = it
                    tileViewModel.updateTileLength(it)
                },
                Param("Ширина одной\nплитки", tile_width, "м"){
                    tile_width = it
                    tileViewModel.updateTileWidth(it)
                },
                Param("Количество в\nупаковке", tile_package_num, "шт"){
                    tile_package_num = it
                    tileViewModel.updateTilePackageNum(it)
                },
                Param("Цена", tile_price, "₽/м2"){
                    tile_price = it
                    tileViewModel.updateTilePrice(it)
                },
            )
        ),

        ParamsBlock(
            "Параметры клея", listOf(
                Param("Масса упаковки\nклея", gluepackage_weight, "г"){
                    gluepackage_weight = it
                    tileViewModel.updateGluepackageWeight(it)
                },
                Param("Цена упаковки\nклея", gluepackage_price, "₽"){
                    gluepackage_price = it
                    tileViewModel.updateGluepackagePrice(it)
                },
                Param("Расход клея", glue_consumption, "г/м2"){
                    glue_consumption = it
                    tileViewModel.updateGlueConsumption(it)
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
                    modifier = Modifier
                        .size(50.dp)
                )
            }

            Text(
                text = "Плитка",
                fontSize = 30.sp,
            )

            IconButton(
                onClick = { navController.navigate(DibuildScreens.TileHelp.name) }
            ) {
                Icon(
                    imageVector = Icons.Sharp.Info,
                    contentDescription = null,
                    tint = Color(0xFF01ACC6),
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        }

        Box(modifier = Modifier.weight(1f)) {
            inputCalcCard(paramsBlockList = TileParams)
        }

        Box() {
            CalculatePageBottomBar(navController,
                DibuildScreens.TileRes.name,
                { tileViewModel.validate() },
                {
                    tileViewModel.clearValues()
                    navController.popBackStack()
                    navController.navigate(DibuildScreens.TileCalc.name)
                },
                { historyViewModel.updateHistory(tileViewModel.getTileCalcHistory()) },
                { tileViewModel.countTotal() })
        }

    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun TileCalcResult(
    navController: NavHostController,
    tileViewModel: TileViewModel = TileViewModel(),
    historyViewModel: HistoryViewModel = HistoryViewModel(),
){

    val TileCalculatedResult = listOf(
        CalculatedResults(
            listOf(
                CalculatedResult("Количество упаковок\nплитки",
                    "%s".format(tileViewModel.uiState.value.packages_num), "шт"),
                CalculatedResult("Стоимость плитки",
                    "%.2f".format(tileViewModel.uiState.value.tile_total), "₽"),
                CalculatedResult("Излишки упаковок плитки",
                    "%.2f".format(tileViewModel.uiState.value.tile_excess), "шт"),
            )
        ),

        CalculatedResults(
            listOf(
                CalculatedResult("Количество упаковок клея",
                    "%s".format(tileViewModel.uiState.value.gluepackages_num), "шт"),
                CalculatedResult("Стоимость клея",
                    "%.2f".format(tileViewModel.uiState.value.glue_total), "₽"),
                CalculatedResult("Излишки упаковок клея",
                    "%.2f".format(tileViewModel.uiState.value.glue_excess), "шт"),
            )
        ),

        CalculatedResults(
            listOf(
                CalculatedResult("Итоговая стоимость",
                    "%.2f".format(tileViewModel.uiState.value.total), "₽")
            )
        )
    )

    Box(
        contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxSize()
    ) {

        resultCalcCard(TileCalculatedResult)

        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ){
            CalculateResultsPageBottomBar(navController,
                DibuildScreens.TileCalc.name,
                historyViewModel)
        }
    }

}

@Composable
fun TileCalcHelp(
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
                    text = "Справка\n\nПлитка",
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center
                )

            }
            val TileHelpParams = listOf(
                Help(
                    "Длина плитки * Ширина плитки * Количество штук в упаковке = Площадь одной упаковки"
                ),

                Help(
                    "Количество упаковок = Площадь пола / площадь одной упаковки (округляем вверх)"
                ),

                Help(
                    "Стоимость плитки = количество упаковок * площадь одной упаковки * цена за м^2"
                ),

                Help(
                    "Излишек = Количество упаковок - Количество упаковок без округления вверх"
                ),

                Help(
                    "Излишек стоимости = Излишек * площадь одной упаковки * цена за м^2"
                ),

                Help(
                    "Количество упаковок клея = Площадь пола * Расход клея / Масса одной упаковки клея (Округлить вверх)"
                ),

                Help(
                    "Стоимость клея = Количество упаковок клея * Стоимость одной упаковки клея"
                ),

                Help(
                    "Излишек = Количество упаковок клея - Количество упаковок клея без округления вверх"
                ),

                Help(
                    "Излишек стоимости = Излишек * Стоимость одной упаковки клея"
                ),

                Help(
                    "Итого: стоимость клея + стоимость плитки"
                )
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.weight(1f)) {
                items(TileHelpParams) {
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
fun TileCalcResultPreview() {
    DibuildTheme {
        TileCalcResult(rememberNavController())
    }
}

@Preview
@Composable
fun TileCalcPreview() {
    DibuildTheme {
        TileCalcScreen(rememberNavController())
    }
}

@Preview
@Composable
fun TileCalcHelpPreview() {
    DibuildTheme {
        TileCalcHelp(rememberNavController())
    }
}