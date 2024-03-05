package com.example.dibuild.ui.wallpapers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.History
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material3.Card
import com.example.dibuild.ui.theme.DibuildTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dibuild.model.Param
import com.example.dibuild.model.ParamsBlock


@Composable
fun WallpapersCalcScreen() {
    val WallpaperParams = listOf(
        ParamsBlock(
            "Параметры помещения", listOf(
                Param("Длина комнаты", "10", "м"),
                Param("Ширина комнаты", "10", "м"),
                Param("Высота комнаты", "10", "м"),
            )
        ),

        ParamsBlock(
            "Параметры обоев", listOf(
                Param("Длина рулона", "2", "м"),
                Param("Ширина рулона", "0.45", "м"),
                Param("Цена за рулон", "458", "₽"),
                )
        ),

        ParamsBlock(
            "Параметры клея", listOf(
                Param("Масса упаковки\nклея", "1000", "г"),
                Param("Цена упаковки\nклея", "236", "₽"),
                Param("Расход клея", "60", "г/м2"),
            )
        )
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
                    modifier = Modifier.size(50.dp)
                )
            }

            Text(
                text = "Обои",
                fontSize = 30.sp,
            )

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Sharp.Info,
                    contentDescription = null,
                    tint = Color(0xFF01ACC6),
                    modifier = Modifier.size(50.dp)
                )
            }
        }


        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(WallpaperParams) {
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
}

@Composable
fun WallpapersCalcResult() {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
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
                    text = "Расчёт", fontSize = 50.sp, modifier = Modifier.padding(20.dp)
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Количество упаковок",
                        fontSize = 20.sp,
                    )

                    Text(
                        text = "4 упаковки",
                        fontSize = 20.sp,
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Стоимость",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "2456 р.",
                        fontSize = 20.sp,
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Излишки упаковок ламината",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "1 упаковка",
                        fontSize = 20.sp,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun WallpapersCalcResultPreview() {
    DibuildTheme {
        WallpapersCalcResult()
    }
}

@Preview
@Composable
fun WallpapersCalcPreview() {
    DibuildTheme {
        WallpapersCalcScreen()
    }
}
