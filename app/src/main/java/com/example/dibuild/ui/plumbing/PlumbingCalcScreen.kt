package com.example.dibuild.ui.plumbing

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
import com.example.dibuild.ui.UITools.InfoPageBottomBar
import com.example.dibuild.ui.UITools.inputCalcCard
import com.example.dibuild.ui.theme.DibuildTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlumbingCalcScreen(
    navController: NavHostController
) {
    val PlumbingParams = listOf(
        ParamsBlock(
            "Параметры сантехники", listOf(
                Param("Длина труб", "10", "м"),
                Param("Цена труб", "10", "₽/м"),
                Param("Количество\nвентилей", "10", "шт"),
                Param("Цена вентиля", "10", "₽"),
                Param("Цена счетчика", "10", "₽"),
                Param("Цена фильтра", "10", "₽"),
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
                text = "Сантехника",
                fontSize = 30.sp,
            )

            IconButton(
                onClick = { navController.navigate(DibuildScreens.PlumbingHelp.name) }
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

        inputCalcCard(paramsBlockList = PlumbingParams)

        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ){
            CalculatePageBottomBar(navController, DibuildScreens.PlumbingRes.name)
        }
    }
}

@Composable
fun PlumbingCalcResult(
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
                        text = "Стоимость труб",
                        fontSize = 20.sp,
                        )

                    Text(
                        text = "50 ₽",
                        fontSize = 20.sp,)
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Стоимость вентилей",
                        fontSize = 20.sp,
                        )
                    Text(text = "50 ₽",
                        fontSize = 20.sp,
                        )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Итоговая стоимость",
                        fontSize = 30.sp,
                        )
                    Text(
                        text = "100 ₽",
                        fontSize = 20.sp,
                        )
                }
            }
        }

        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ){
            CalculateResultsPageBottomBar(navController, DibuildScreens.PlumbingCalc.name)
        }
    }
}

@Composable
fun PlumbingCalcHelp(
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
                    text = "Справка\n\nСантехника",
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center
                )

            }
            val PlumbingHelpParams = listOf(
                Help(
                    "Стоимость труб =\nдлина труб * цена трубы"
                ),

                Help(
                    "Стоимость вентилей =\nколичество вентилей * цена вентиля"
                ),

                Help(
                    "Итого:\nСтоимость труб + Стоимость вентилей + Стоимость счетчика + Стоимость фильтра"
                )
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.weight(1f)) {
                items(PlumbingHelpParams) {
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
fun PlumbingCalcResultPreview() {
    DibuildTheme {
        PlumbingCalcResult(rememberNavController())
    }
}

@Preview
@Composable
fun PlumbingCalcPreview() {
    DibuildTheme {
        PlumbingCalcScreen(rememberNavController())
    }
}

@Preview
@Composable
fun PlumbingCalcHelpPreview() {
    DibuildTheme {
        PlumbingCalcHelp(rememberNavController())
    }
}
