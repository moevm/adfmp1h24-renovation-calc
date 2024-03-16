package com.example.dibuild.ui.laminate

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
import com.example.dibuild.model.Help
import com.example.dibuild.model.Param
import com.example.dibuild.model.ParamsBlock
import com.example.dibuild.ui.UITools.CalculatePageBottomBar
import com.example.dibuild.ui.UITools.CalculateResultsPageBottomBar
import com.example.dibuild.ui.UITools.InfoPageBottomBar
import com.example.dibuild.ui.UITools.inputCalcCard
import com.example.dibuild.ui.history.HistoryViewModel
import com.example.dibuild.ui.theme.DibuildTheme


@SuppressLint("UnrememberedMutableState")
@Composable
fun LaminateCalcScreen(
    navController: NavHostController,
    laminateViewModel: LaminateViewModel = LaminateViewModel(),
    historyViewModel: HistoryViewModel = HistoryViewModel()
) {

    val laminateUiState by laminateViewModel.uiState.collectAsState()

    var room_length by remember { mutableStateOf(laminateUiState.room_length) }
    var room_width by remember { mutableStateOf(laminateUiState.room_width) }
    var board_length by remember { mutableStateOf(laminateUiState.board_length) }
    var board_width by remember { mutableStateOf(laminateUiState.board_width) }
    var board_num by remember { mutableStateOf(laminateUiState.board_num) }
    var board_price by remember { mutableStateOf(laminateUiState.board_price) }

    val LaminateParams = listOf(
        ParamsBlock(
            "Параметры помещения", listOf(
                Param("Длина комнаты", room_length, "м"){
                    room_length = it
                    laminateViewModel.updateRoomLength(it)
                },
                Param("Ширина комнаты", room_width, "м"){
                    room_width = it
                    laminateViewModel.updateRoomWidth(it)
                },
            )
        ),

        ParamsBlock(
            "Параметры ламината", listOf(
                Param("Длина доски", board_length, "м"){
                    board_length = it
                    laminateViewModel.updateBoardLength(it)
                },
                Param("Ширина доски", board_width, "м"){
                    board_width = it
                    laminateViewModel.updateBoardWidth(it)
                },
                Param("Количество в\nупаковке", board_num, "шт"){
                    board_num = it
                    laminateViewModel.updateBoardNum(it)
                },
                Param("Цена", board_price, "₽/м2"){
                    board_price = it
                    laminateViewModel.updateBoardPrice(it)
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
            CalculatePageBottomBar(navController,
                DibuildScreens.LaminateRes.name,
                { laminateViewModel.validate() },
                {
                    laminateViewModel.clearValues()
                    navController.popBackStack()
                    navController.navigate(DibuildScreens.LaminateCalc.name)
                },
                { historyViewModel.updateHistory(laminateViewModel.getLaminateCalcHistory()) },
                { laminateViewModel.countTotal() })
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LaminateCalcResult(
    navController: NavHostController,
    laminateViewModel: LaminateViewModel = LaminateViewModel(),
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
                        text = "Количество упаковок",
                        fontSize = 20.sp,
                        )

                    Text(
                        text = "${"%s".format(laminateViewModel.uiState.value.laminate_num)} упаковок",
                        fontSize = 20.sp,)
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Итоговая стоимость",
                        fontSize = 30.sp,
                        )
                    Text(text = "${"%.2f".format(laminateViewModel.uiState.value.total)} ₽",
                        fontSize = 20.sp,
                        )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Излишки упаковок ламината",
                        fontSize = 20.sp,
                        )
                    Text(
                        text = "${"%.2f".format(laminateViewModel.uiState.value.excess)} упаковок",
                        fontSize = 20.sp,
                        )
                }
            }
        }
        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ){
            CalculateResultsPageBottomBar(navController,
                DibuildScreens.LaminateCalc.name,
                historyViewModel)
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
                )
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.weight(1f)) {
                items(LaminateHelpParams) {
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
