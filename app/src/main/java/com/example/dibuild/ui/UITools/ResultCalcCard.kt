package com.example.dibuild.ui.UITools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dibuild.model.CalculatedResults

@Composable
fun resultCalcCard(paramsBlockList:List<CalculatedResults>){
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
                paramsBlockList.forEach{item ->
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
}