package com.example.dibuild.ui.laminate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.History
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun LaminateCalcScreen() {
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
                text = "Ламинат",
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

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xffdae5e3),
            ),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text(
                    text = "Параметры помещения",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 30.sp
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Длина комнаты",
                        fontSize = 25.sp,
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    OutlinedTextField(
                        value = "10",
                        onValueChange = {/*TODO*/ },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.width(100.dp)
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "м",
                        fontSize = 25.sp,
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Ширина комнаты",
                        fontSize = 25.sp,
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    OutlinedTextField(
                        value = "10",
                        onValueChange = {/*TODO*/ },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.width(100.dp)
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "м",
                        fontSize = 25.sp,
                    )
                }
            }
        }
    }
}