package com.example.dibuild.ui.UITools

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
import com.example.dibuild.ui.theme.DibuildTheme

@Composable
fun CalculatePageBottomBar() {
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
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = { /*TODO*/ },
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
fun CalculateResultsPageBottomBar() {
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
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(100.dp),
        ) {
            Icon(
                imageVector = Icons.Sharp.Download,
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
                imageVector = Icons.Sharp.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
            )
        }
    }

}

@Composable
fun InfoPageBottomBar() {
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
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = { /*TODO*/ },
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
fun SectionsPageBottomBar() {
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
        CalculatePageBottomBar()
    }
}

@Composable
@Preview
fun CalculateResultsPageBottomBarPreview(){
    DibuildTheme {
        CalculateResultsPageBottomBar()
    }
}

@Composable
@Preview
fun InfoPageBottomBarPreview(){
    DibuildTheme {
        InfoPageBottomBar()
    }
}

@Composable
@Preview
fun SectionsPageBottomBarPreview(){
    DibuildTheme {
        SectionsPageBottomBar()
    }
}
