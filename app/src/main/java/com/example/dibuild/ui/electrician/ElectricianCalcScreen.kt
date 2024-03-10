package com.example.dibuild.ui.electrician

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
fun ElectricianCalcScreen(
    navController: NavHostController
) {

    val ElectricianParams = listOf(
        ParamsBlock(
            "Параметры помещения", listOf(
                Param("Количество\nрозеток", "10", "шт"),
                Param("Количество\nвыключателей", "10", "шт"),
            )
        ),

        ParamsBlock(
            "Параметры электрики", listOf(
                Param("Длина провода", "2.15", "м"),
                Param("Цена провода", "600", "₽/м"),
                Param("Длина\nпластикового\nкороба", "2", "м"),
                Param("Цена\nпластикового\nкороба", "678", "₽/м"),
                Param("Цена розетки", "500", "₽"),
                Param("Цена выключателя", "500", "₽"),
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
                text = "Электрика",
                fontSize = 30.sp,
            )

            IconButton(
                onClick = { navController.navigate(DibuildScreens.ElectricianHelp.name) }
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
            inputCalcCard(paramsBlockList = ElectricianParams)
        }

        Box() {
            CalculatePageBottomBar(navController, DibuildScreens.ElectricianRes.name)
        }

    }
}

@Composable
fun ElectricianCalcResult(
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
                        text = "Стоимость провода",
                        fontSize = 20.sp,
                    )

                    Text(
                        text = "5000 ₽",
                        fontSize = 20.sp,
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Стоимость короба",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "2456 ₽",
                        fontSize = 20.sp,
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Стоимость выключателей",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "2100 ₽",
                        fontSize = 20.sp,
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Стоимость розеток",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "3200 ₽",
                        fontSize = 20.sp,
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Итоговая стоимость",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "12756 ₽",
                        fontSize = 20.sp,
                    )
                }
            }
        }
        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ){
            CalculateResultsPageBottomBar(navController, DibuildScreens.ElectricianCalc.name)
        }
    }
}

@Composable
fun ElectricianCalcHelp(
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
                    text = "Справка\n\nЭлектрика",
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center
                )

            }


            val ElectricianHelpParams = listOf(
                Help(
                    "Стоимость провода =\nдлина провода * цена провода"
                ),

                Help(
                    "Стоимость короба =\n длина короба * цена короба"
                ),

                Help(
                    "Стоимость выключателей = количество выключателей * цена выключателя"
                ),

                Help(
                    "Стоимость розеток = количество розеток * цена розетки"
                ),

                Help(
                    "Итого:\nСтоимость провода + Стоимость короба + Стоимость выключателей + Стоимость розеток"
                )
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.weight(1f)) {
                items(ElectricianHelpParams) {
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
fun ElectricianCalcResultPreview() {
    DibuildTheme {
        ElectricianCalcResult(rememberNavController())
    }
}

@Preview
@Composable
fun ElectricianCalcPreview() {
    DibuildTheme {
        ElectricianCalcScreen(rememberNavController())
    }
}

@Preview
@Composable
fun ElectricianCalcHelpPreview() {
    DibuildTheme {
        ElectricianCalcHelp(rememberNavController())
    }
}

