package com.example.dibuild.ui.electrician

import android.annotation.SuppressLint
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElectricianCalcScreen(
    navController: NavHostController,
    electricianViewModel: ElectricianViewModel = viewModel()
) {
    val electricianUiState by electricianViewModel.uiState.collectAsState()

    var socket_num by remember { mutableStateOf(electricianUiState.socket_num) }
    var switch_num by remember { mutableStateOf(electricianUiState.switch_num) }
    var wire_length by remember { mutableStateOf(electricianUiState.wire_length) }
    var wire_price by remember { mutableStateOf(electricianUiState.wire_price) }
    var duct_length by remember { mutableStateOf(electricianUiState.duct_length) }
    var duct_price by remember { mutableStateOf(electricianUiState.duct_price) }
    var socket_price by remember { mutableStateOf(electricianUiState.socket_price) }
    var switch_price by remember { mutableStateOf(electricianUiState.switch_price) }

    var ElectricianParams = listOf(
        ParamsBlock(
            "Параметры помещения", listOf(
                Param("Количество\nрозеток", socket_num, "шт") {
                    socket_num = it
                    electricianViewModel.updateSocketNum(it)
                },
                Param("Количество\nвыключателей", switch_num, "шт") {
                    switch_num = it
                    electricianViewModel.updateSwitchNum(it)
                },
            )
        ),

        ParamsBlock(
            "Параметры электрики", listOf(
                Param("Длина провода", wire_length, "м") {
                    wire_length = it
                    electricianViewModel.updateWireLength(it)
                },
                Param("Цена провода", wire_price, "₽/м") {
                    wire_price = it
                    electricianViewModel.updateWirePrice(it)
                },
                Param(
                    "Длина\nпластикового\nкороба",
                    duct_length,
                    "м",
                ) {
                    duct_length = it
                    electricianViewModel.updateDuctLength(it)
                },
                Param(
                    "Цена\nпластикового\nкороба",
                    duct_price,
                    "₽/м",
                ) {
                    duct_price = it
                    electricianViewModel.updateDuctPrice(it)
                },
                Param("Цена розетки", socket_price, "₽") {
                    socket_price = it
                    electricianViewModel.updateSocketPrice(it)
                },
                Param("Цена выключателя", switch_price, "₽") {
                    switch_price = it
                    electricianViewModel.updateSwitchPrice(it)
                },
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
            CalculatePageBottomBar(navController, DibuildScreens.ElectricianRes.name,
                { electricianViewModel.validate() })
        }

    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ElectricianCalcResult(
    navController: NavHostController,
    electricianViewModel: ElectricianViewModel = viewModel()
) {
    val electricianUiState by electricianViewModel.uiState.collectAsState()
    electricianViewModel.countTotal()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
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
                        text = "${"%.2f".format(electricianViewModel.uiState.value.wire_total)} ₽",
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
                        text = "${"%.2f".format(electricianViewModel.uiState.value.duct_total)} ₽",
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
                        text = "${"%.2f".format(electricianViewModel.uiState.value.switch_total)} ₽",
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
                        text = "${"%.2f".format(electricianViewModel.uiState.value.socket_total)} ₽",
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
                        text = "${"%.2f".format(electricianViewModel.uiState.value.total)} ₽",
                        fontSize = 20.sp,
                    )
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            CalculateResultsPageBottomBar(navController, DibuildScreens.ElectricianCalc.name)
        }
    }
}

@Composable
fun ElectricianCalcHelp(
    navController: NavHostController
) {
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

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.weight(1f)
            ) {
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

