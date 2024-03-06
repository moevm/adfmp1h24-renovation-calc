package com.example.dibuild.ui.laminate

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


@Composable
fun LaminateCalcScreen(
    navController: NavHostController
) {
    val LaminateParams = listOf(
        ParamsBlock(
            "Параметры помещения", listOf(
                Param("Длина комнаты", "10", "м"),
                Param("Ширина комнаты", "10", "м"),
            )
        ),

        ParamsBlock(
            "Параметры ламината", listOf(
                Param("Длина доски", "2", "м"),
                Param("Ширина доски", "1", "м"),
                Param("Количество в\nупаковке", "20", "шт"),
                Param("Цена", "3000", "₽/м2"),
            )
        ),
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
                text = "Ламинат",
                fontSize = 30.sp,
            )

            IconButton(
                onClick = { navController.navigate(DibuildScreens.LaminateHelp.name) }
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

        inputCalcCard(paramsBlockList = LaminateParams)

        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ){
            CalculatePageBottomBar(navController, DibuildScreens.LaminateRes.name)
        }
    }
}

@Composable
fun LaminateCalcResult(
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
                        text = "Количество упаковок",
                        fontSize = 20.sp,
                        )

                    Text(
                        text = "4 упаковки",
                        fontSize = 20.sp,)
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Итоговая стоимость",
                        fontSize = 30.sp,
                        )
                    Text(text = "2456 р.",
                        fontSize = 20.sp,
                        )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Излишки упаковок ламината",
                        fontSize = 20.sp,
                        )
                    Text(
                        text = "1 упаковка",
                        fontSize = 20.sp,
                        )
                }
            }
        }
        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ){
            CalculateResultsPageBottomBar(navController, DibuildScreens.LaminateCalc.name)
        }
    }
}

@Composable
fun LaminateCalcHelp(
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
                    text = "Справка\n\nЛаминат",
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center
                )

            }
            val LaminateHelpParams = listOf(
                Help(
                    "Длина ламината * Ширина Ламината * Количество штук в упаковке = Площадь одной упаковки"
                ),

                Help(
                    "Количество упаковок = Площадь комнаты / площадь одной упаковки (округляем вверх)"
                ),

                Help(
                    "Итого = количество упаковок * площадь одной упаковки * цена за м^2"
                ),

                Help(
                    "Излишек = Количество упаковок - Количество упаковок без округления вверх"
                ),

                Help(
                    "Излишек стоимости = Излишек * площадь одной упаковки * цена за м^2"
                )
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(LaminateHelpParams) {
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
fun LaminateCalcPreview() {
    DibuildTheme {
        LaminateCalcScreen(rememberNavController())
    }
}

@Preview
@Composable
fun LaminateCalcResultPreview(){
    DibuildTheme {
        LaminateCalcResult(rememberNavController())
    }
}

@Preview
@Composable
fun LaminateCalcHelpPreview(){
    DibuildTheme {
        LaminateCalcHelp(rememberNavController())
    }
}
