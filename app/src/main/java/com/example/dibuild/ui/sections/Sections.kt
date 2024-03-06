package com.example.dibuild.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.History
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.dibuild.ui.theme.DibuildTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dibuild.DibuildScreens
import com.example.dibuild.model.ParamsBlock
import com.example.dibuild.ui.UITools.InfoPageBottomBar
import com.example.dibuild.ui.UITools.SectionsPageBottomBar


@Composable
fun SectionsCalcScreen(
    navController: NavHostController
) {
    val SectionsParams =
        listOf(
            Pair("Ламинат",DibuildScreens.LaminateCalc),
            Pair("Обои",DibuildScreens.WallpapersCalc),
            Pair("Плитка", DibuildScreens.TileCalc),
            Pair("Электрика", DibuildScreens.ElectricianCalc),
            Pair("Сантехника", DibuildScreens.PlumbingCalc),
            Pair("Think about it", DibuildScreens.About),
        )
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
                    text = "Строительный\n\nкалькулятор",
                    fontSize = 48.sp,
                    textAlign = TextAlign.Center
                )

            }

            Text(
                text = "Разделы",
                fontSize = 45.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(10.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(SectionsParams) {
                    Button(onClick = { navController.navigate(it.second.name) }) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(10.dp)
                        ) {

                            Text(
                                text = it.first,
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center
                            )

                        }
                    }
                }
            }




            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                SectionsPageBottomBar(navController)
            }

        }
    }
}


@Preview
@Composable
fun SectionsCalcPreview() {
    DibuildTheme {
        SectionsCalcScreen(rememberNavController())
    }
}
