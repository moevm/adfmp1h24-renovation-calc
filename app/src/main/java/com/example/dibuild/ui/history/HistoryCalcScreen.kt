package com.example.dibuild.ui.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.dibuild.ui.theme.DibuildTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dibuild.DibuildScreens
import com.example.dibuild.model.Help
import com.example.dibuild.ui.UITools.CalculateResultsPageBottomBar
import com.example.dibuild.ui.UITools.InfoPageBottomBar


@Composable
fun HistoryCalcScreen(
    navController: NavHostController,
    historyViewModel: HistoryViewModel
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
                    text = "История\n\nпоследнего\n\nвычисления",
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center
                )

            }

            Box(modifier = Modifier.weight(1f)) {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(historyViewModel.uiState.value.history) {
                        Text(
                            text = it,
                            modifier = Modifier.padding(10.dp),
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Box() {
                CalculateResultsPageBottomBar(navController, "")
            }
        }
    }
}


@Preview
@Composable
fun HistoryPreview() {
    DibuildTheme {
        HistoryCalcScreen(rememberNavController(), HistoryViewModel())
    }
}
