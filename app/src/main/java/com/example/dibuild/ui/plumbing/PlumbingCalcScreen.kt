package com.example.dibuild.ui.plumbing

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
import com.example.dibuild.ui.electrician.ElectricianViewModel
import com.example.dibuild.ui.history.HistoryViewModel
import com.example.dibuild.ui.theme.DibuildTheme


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlumbingCalcScreen(
    navController: NavHostController,
    plumbingViewModel: PlumbingViewModel = PlumbingViewModel(),
    historyViewModel: HistoryViewModel = HistoryViewModel()
) {

    val plumbingUiState by plumbingViewModel.uiState.collectAsState()

    var pipe_length by remember { mutableStateOf(plumbingUiState.pipe_length) }
    var pipe_price by remember { mutableStateOf(plumbingUiState.pipe_price) }
    var valve_num by remember { mutableStateOf(plumbingUiState.valve_num) }
    var valve_price by remember { mutableStateOf(plumbingUiState.valve_price) }
    var meter_price by remember { mutableStateOf(plumbingUiState.meter_price) }
    var filter_price by remember { mutableStateOf(plumbingUiState.filter_price) }

    val PlumbingParams = listOf(
        ParamsBlock(
            "Параметры сантехники", listOf(
                Param("Длина труб", pipe_length, "м"){
                    pipe_length = it
                    plumbingViewModel.updatePipeLength(it)
                },
                Param("Цена труб", pipe_price, "₽/м"){
                    pipe_price = it
                    plumbingViewModel.updatePipePrice(it)
                },
                Param("Количество\nвентилей", valve_num, "шт"){
                    valve_num = it
                    plumbingViewModel.updateValveNum(it)
                },
                Param("Цена вентиля", valve_price, "₽"){
                    valve_price = it
                    plumbingViewModel.updateValvePrice(it)
                },
                Param("Цена счетчика", meter_price, "₽"){
                    meter_price = it
                    plumbingViewModel.updateMeterPrice(it)
                },
                Param("Цена фильтра", filter_price, "₽"){
                    filter_price = it
                    plumbingViewModel.updateFilterPrice(it)
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
            CalculatePageBottomBar(navController,
                DibuildScreens.PlumbingRes.name,
                { plumbingViewModel.validate() },
                {
                    plumbingViewModel.clearValues()
                    navController.popBackStack()
                    navController.navigate(DibuildScreens.PlumbingCalc.name)
                },
                { historyViewModel.updateHistory(plumbingViewModel.getPlumbingCalcHistory()) },
                { plumbingViewModel.countTotal() })
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PlumbingCalcResult(
    navController: NavHostController,
    plumbingViewModel: PlumbingViewModel = PlumbingViewModel(),
    historyViewModel: HistoryViewModel = HistoryViewModel(),
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
                        text = "${"%.2f".format(plumbingViewModel.uiState.value.pipe_total)} ₽",
                        fontSize = 20.sp,)
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Стоимость вентилей",
                        fontSize = 20.sp,
                        )
                    Text(text = "${"%.2f".format(plumbingViewModel.uiState.value.valve_total)} ₽",
                        fontSize = 20.sp,
                        )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Итоговая стоимость",
                        fontSize = 30.sp,
                        )
                    Text(
                        text = "${"%.2f".format(plumbingViewModel.uiState.value.total)} ₽",
                        fontSize = 20.sp,
                        )
                }
            }
        }

        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ){
            CalculateResultsPageBottomBar(
                navController,
                DibuildScreens.PlumbingCalc.name,
                historyViewModel
            )
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
