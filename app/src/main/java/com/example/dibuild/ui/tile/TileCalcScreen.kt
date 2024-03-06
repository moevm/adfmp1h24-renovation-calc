package com.example.dibuild.ui.tile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.History
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.dibuild.model.Help
import com.example.dibuild.model.Param
import com.example.dibuild.model.ParamsBlock
import com.example.dibuild.ui.UITools.CalculatePageBottomBar
import com.example.dibuild.ui.UITools.CalculateResultsPageBottomBar
import com.example.dibuild.ui.UITools.inputCalcCard
import com.example.dibuild.ui.theme.DibuildTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TileCalcScreen(
    navController: NavHostController
) {
    val TileParams = listOf(
        ParamsBlock(
            "Параметры помещения", listOf(
                Param("Длина комнаты", "10", "м"),
                Param("Ширина комнаты", "10", "м"),
            )
        ),

        ParamsBlock(
            "Параметры плитки", listOf(
                Param("Длина одной\nплитки", "2", "м"),
                Param("Ширина одной\nплитки", "1", "м"),
                Param("Количество в\nупаковке", "20", "шт"),
                Param("Цена за упаковку", "3000", "₽"),
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
            IconButton(onClick = { /*TODO*/ }) {
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
                onClick = { /*TODO*/ }
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

        Box(modifier = Modifier.weight(0.9f)) {
            inputCalcCard(paramsBlockList = TileParams)
        }

        Box(modifier = Modifier.weight(0.135f)) {
            CalculatePageBottomBar(navController, DibuildScreens.TileRes.name)
        }
    }
}

@Composable
fun TileCalcResult(
    navController: NavHostController
){

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Card() {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Расчёт",
                    fontSize = 50.sp,
                    modifier = Modifier.padding(20.dp)
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Количество упаковок\nплитки",
                        fontSize = 20.sp,
                    )

                    Text(
                        text = "3 упаковки",
                        fontSize = 20.sp,)
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Стоимость",
                        fontSize = 20.sp,
                    )
                    Text(text = "14499 р.",
                        fontSize = 20.sp,
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Излишки упаковок плитки",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "1 упаковка",
                        fontSize = 20.sp,
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Количество упаковок клея",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "1 упаковка",
                        fontSize = 20.sp,
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Стоимость",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "677 р.",
                        fontSize = 20.sp,
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Излишки упаковок клея",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "1 упаковка",
                        fontSize = 20.sp,
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Итоговая стоимость",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "15156 р.",
                        fontSize = 20.sp,
                    )
                }
            }
        }

        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ){
            CalculateResultsPageBottomBar(navController, DibuildScreens.TileCalc.name)
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

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(TileHelpParams) {
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