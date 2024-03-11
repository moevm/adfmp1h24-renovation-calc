package com.example.dibuild.ui.UITools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dibuild.model.ParamsBlock


@Composable
fun inputCalcCard(paramsBlockList: List<ParamsBlock>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(paramsBlockList) {
            Card() {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = it.name, modifier = Modifier.padding(10.dp), fontSize = 30.sp
                    )

                    it.params.forEach { param ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Text(
                                text = param.name,
                                fontSize = 25.sp,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            OutlinedTextField(
                                value = param.value,
                                onValueChange = param.onValueChanged,
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Next
                                ),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.width(100.dp)
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            Text(
                                text = param.unit,
                                fontSize = 25.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}