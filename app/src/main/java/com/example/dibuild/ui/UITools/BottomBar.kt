package com.example.dibuild.ui.UITools

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Download
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material.icons.sharp.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dibuild.DibuildScreens
import com.example.dibuild.ui.electrician.ElectricianViewModel
import com.example.dibuild.ui.history.HistoryViewModel
import com.example.dibuild.ui.theme.DibuildTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatePageBottomBar(
    navController: NavHostController,
    resScreen: String,
    validateFunc: () -> Pair<Boolean, List<String>> = { Pair(true, emptyList()) },
    clearFunc: () -> Unit = {},
    updateHistoryFunc: () -> Unit = {},
    calculateFunc: () -> Unit = {}
) {

    var invalidFields by remember { mutableStateOf(emptyList<String>()) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { navController.navigate(DibuildScreens.Sections.name) },
            modifier = Modifier.size(100.dp)
        ) {
            Icon(
                imageVector = Icons.Sharp.Menu,
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = {
                val d = validateFunc()
                if (d.first) {
                    calculateFunc()
                    updateHistoryFunc()
                    navController.navigate(resScreen)
                } else {
                    invalidFields = d.second
                }
            },
            modifier = Modifier.size(100.dp),
            contentPadding = PaddingValues(0.dp),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "=",
                    fontSize = 70.sp,
                    modifier = Modifier
                )
            }

        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = clearFunc,
            modifier = Modifier.size(100.dp),
            contentPadding = PaddingValues(0.dp),
        ) {
            Text(
                text = "C",
                fontSize = 70.sp,
                modifier = Modifier.clip(CircleShape)
            )
        }
    }

    if (invalidFields.isNotEmpty()) {
        AlertDialog(
            onDismissRequest = { invalidFields = emptyList<String>() },

            ) {
            Box(contentAlignment = Alignment.Center) {
                Card() {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Вы неправильно заполнили следующие поля",
                            modifier = Modifier.padding(10.dp),
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                        LazyColumn(
                            contentPadding = PaddingValues(18.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(invalidFields) { it ->
                                Card(
                                    modifier = Modifier.padding(10.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.onError,
                                    ),
                                ) {
                                    Text(
                                        text = it,
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(10.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CalculateResultsPageBottomBar(
    navController: NavHostController,
    calcScreen: String,
    historyViewModel: HistoryViewModel = HistoryViewModel(),
) {
    val shareLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { navController.navigate(DibuildScreens.Sections.name) },
            modifier = Modifier.size(100.dp)
        ) {
            Icon(
                imageVector = Icons.Sharp.Menu,
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        historyViewModel.uiState.value.history.joinToString(separator = "\n")
                    )
                    putExtra(Intent.EXTRA_SUBJECT, "Результат вычисления Dibuild")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                shareLauncher.launch(shareIntent)
            },
            modifier = Modifier.size(100.dp),
        ) {
            Icon(
                imageVector = Icons.Sharp.Share,
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        if (calcScreen == "")
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(100.dp),

                ) {
                Icon(
                    imageVector = Icons.Sharp.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(70.dp)
                )
            }
        else
            Button(
                onClick = { navController.popBackStack(calcScreen, false) },
                modifier = Modifier.size(100.dp),

                ) {
                Icon(
                    imageVector = Icons.Sharp.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(70.dp)
                )
            }
    }

}

@Composable
fun InfoPageBottomBar(
    navController: NavHostController
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { navController.navigate(DibuildScreens.Sections.name) },
            modifier = Modifier.size(100.dp)
        ) {
            Icon(
                imageVector = Icons.Sharp.Menu,
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            // DibuildScreens.Sections.name, false
            onClick = { navController.popBackStack() },
            modifier = Modifier.size(100.dp),

            ) {
            Icon(
                imageVector = Icons.Sharp.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
            )
        }
    }

}


@Composable
@Preview
fun CalculatePageBottomBarPreview() {
    DibuildTheme {
        CalculatePageBottomBar(rememberNavController(), DibuildScreens.WallpapersRes.name)
    }
}

@Composable
@Preview
fun CalculateResultsPageBottomBarPreview() {
    DibuildTheme {
        CalculateResultsPageBottomBar(rememberNavController(), DibuildScreens.WallpapersCalc.name)
    }
}

@Composable
@Preview
fun InfoPageBottomBarPreview() {
    DibuildTheme {
        InfoPageBottomBar(rememberNavController())
    }
}

