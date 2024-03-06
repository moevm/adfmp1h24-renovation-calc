package com.example.dibuild.ui.wallpapers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.History
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material3.Card
import com.example.dibuild.ui.theme.DibuildTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
import com.example.dibuild.ui.UITools.inputCalcCard


@Composable
fun WallpapersCalcScreen(
    navController: NavHostController
) {
    val WallpaperParams = listOf(
        ParamsBlock(
            "Параметры помещения", listOf(
                Param("Длина комнаты", "10", "м"),
                Param("Ширина комнаты", "10", "м"),
                Param("Высота комнаты", "10", "м"),
            )
        ),

        ParamsBlock(
            "Параметры обоев", listOf(
                Param("Длина рулона", "2", "м"),
                Param("Ширина рулона", "0.45", "м"),
                Param("Цена за рулон", "458", "₽"),
                )
        ),

        ParamsBlock(
            "Параметры клея", listOf(
                Param("Масса упаковки\nклея", "1000", "г"),
                Param("Цена упаковки\nклея", "236", "₽"),
                Param("Расход клея", "60", "г/м2"),
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


        Box(modifier = Modifier.weight(0.9f)) {
            inputCalcCard(paramsBlockList = WallpaperParams)
//            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
//                items(WallpaperParams) {
//                    Card() {
//                        Column(
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            Text(
//                                text = it.name, modifier = Modifier.padding(10.dp), fontSize = 30.sp
//                            )
//
//                            it.params.forEach { param ->
//                                Row(
//                                    verticalAlignment = Alignment.CenterVertically,
//                                    modifier = Modifier.padding(vertical = 8.dp)
//                                ) {
//                                    Text(
//                                        text = param.name,
//                                        fontSize = 25.sp,
//                                        textAlign = TextAlign.Center
//                                    )
//                                    Spacer(modifier = Modifier.padding(10.dp))
//                                    OutlinedTextField(
//                                        value = param.value,
//                                        onValueChange = {/*TODO*/ },
//                                        keyboardOptions = KeyboardOptions.Default.copy(
//                                            keyboardType = KeyboardType.Number,
//                                            imeAction = ImeAction.Next
//                                        ),
//                                        shape = RoundedCornerShape(8.dp),
//                                        modifier = Modifier.width(100.dp)
//                                    )
//                                    Spacer(modifier = Modifier.padding(10.dp))
//                                    Text(
//                                        text = param.unit,
//                                        fontSize = 25.sp,
//                                    )
//                                }
//                            }
//                        }
//                    }
//                }
//            }
        }

        Box(modifier = Modifier.weight(0.135f)) {
            CalculatePageBottomBar(navController, DibuildScreens.WallpapersRes.name)
        }


    }
}

@Composable
fun WallpapersCalcResult(
    navController: NavHostController
) {

    val WallpaperCalculatedResult = listOf(
        CalculatedResults(
            listOf(
                CalculatedResult("Количество рулонов обоев", "100", "шт"),
                CalculatedResult("Стоимость", "50", "₽"),
                CalculatedResult("Излишки рулонов обоев", "1", "шт"),
            )
        ),

        CalculatedResults(
            listOf(
                CalculatedResult("Количество упаковок клея","50", "шт"),
                CalculatedResult("Стоимость","50", "₽"),
                CalculatedResult("Излишки упаковок клея","1", "шт"),
            )
        ),

        CalculatedResults(
            listOf(
                CalculatedResult("Итоговая стоимость", "100", "₽")
            )
        )
    )
    Column {
        Box(
            contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxSize()
        ) {
            Card() {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Расчёт", fontSize = 50.sp, modifier = Modifier.padding(20.dp)
                    )

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        WallpaperCalculatedResult.forEach{item ->
                            item.results.forEach {result ->
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = result.name,
                                        fontSize = 20.sp,
                                    )

                                    Text(
                                        text = "${result.value} ${result.unit}",
                                        fontSize = 20.sp,
                                    )
                                    Spacer(modifier = Modifier.padding(5.dp))
                                }
                            }
                            Spacer(modifier = Modifier.padding(20.dp))
                        }
                    }
                }
            }
            Column(verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ){
                CalculateResultsPageBottomBar(navController, DibuildScreens.WallpapersCalc.name)
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

                Help(
                    "Излишек стоимости = Излишек * Цена за рулон"
                ),

                Help(
                    "Количество упаковок клея = Общая площадь стен * Расход клея / масса одной упаковки клея (Округлить вверх)"
                ),

                Help(
                    "Стоимость = Количество упаковок клея * Стоимость одной упаковки клея"
                ),

                Help(
                    "Излишек = Количество упаковок клея - Количество упаковок клея без округления вверх"
                ),

                Help(
                    "Излишек стоимости = Излишек * Стоимость одной упаковки клея"
                )
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(WallpaperHelpParams) {
                    Text(
                        text = it.info, modifier = Modifier.padding(10.dp), fontSize = 25.sp
                    )
                }
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
