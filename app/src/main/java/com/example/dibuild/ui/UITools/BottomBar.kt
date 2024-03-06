package com.example.dibuild.ui.UITools

import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Download
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material.icons.sharp.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dibuild.DibuildScreens
import com.example.dibuild.ui.theme.DibuildTheme

@Composable
fun CalculatePageBottomBar(
    navController: NavHostController,
    resScreen: String
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
            onClick = { navController.navigate(resScreen) },
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
            onClick = { /*TODO*/ },
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
}

@Composable
fun CalculateResultsPageBottomBar(
    navController: NavHostController,
    calcScreen: String,
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
            onClick = { /*TODO*/ },
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
fun SectionsPageBottomBar(
    navController: NavHostController
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(100.dp)
        ) {
            Icon(
                imageVector = Icons.Sharp.Menu,
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
            )
        }
    }
}



@Composable
@Preview
fun CalculatePageBottomBarPreview(){
    DibuildTheme {
        CalculatePageBottomBar(rememberNavController(), DibuildScreens.WallpapersRes.name)
    }
}

@Composable
@Preview
fun CalculateResultsPageBottomBarPreview(){
    DibuildTheme {
        CalculateResultsPageBottomBar(rememberNavController(), DibuildScreens.WallpapersCalc.name)
    }
}

@Composable
@Preview
fun InfoPageBottomBarPreview(){
    DibuildTheme {
        InfoPageBottomBar(rememberNavController())
    }
}

@Composable
@Preview
fun SectionsPageBottomBarPreview(){
    DibuildTheme {
        SectionsPageBottomBar(rememberNavController())
    }
}
