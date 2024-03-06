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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.History
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.dibuild.ui.theme.DibuildTheme
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
import com.example.dibuild.model.Param
import com.example.dibuild.model.ParamsBlock
import com.example.dibuild.ui.UITools.cardParamLazy


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
                text = "Электрика",
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

        cardParamLazy(paramsBlockList = ElectricianParams)
    }
}

@Composable
fun ElectricianCalcResult(){

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
                        fontSize = 20.sp,)
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Стоимость короба",
                        fontSize = 20.sp,
                    )
                    Text(text = "2456 ₽",
                        fontSize = 20.sp,
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Стоимость выключателей",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "2100 ₽",
                        fontSize = 20.sp,
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Стоимость розеток",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "3200 ₽",
                        fontSize = 20.sp,
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Итоговая стоимость",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "12756 ₽",
                        fontSize = 20.sp,
                    )
                }
            }
        }
    }
}

@Composable
fun ElectricianCalcHelp(){

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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(20.dp)
            ) {

                Text(
                    text = "Стоимость провода =\n длина провода * цена провода",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(10.dp)
            ) {

                Text(
                    text = "Стоимость короба =\n длина короба * цена короба",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(10.dp)
            ) {

                Text(
                    text = "Стоимость выключателей = количество выключателей * цена выключателя",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(10.dp)
            ) {

                Text(
                    text = "Стоимость розеток = количество розеток * цена розетки",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(10.dp)
            ) {

                Text(
                    text = "Итого:\n Стоимость провода + Стоимость короба + Стоимость выключателей + Стоимость розеток",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )

            }
        }
    }
}
@Preview
@Composable
fun ElectricianCalcPreview(){
    DibuildTheme {
        ElectricianCalcScreen(rememberNavController())
//        ElectricianCalcResult()
    }
}
