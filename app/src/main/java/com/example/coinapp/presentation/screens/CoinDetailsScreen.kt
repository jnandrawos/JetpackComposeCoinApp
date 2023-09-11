package com.example.coinapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.coinapp.base.extensions.decodeUrl

@Composable
fun CoinsDetailsScreen(
    modifier: Modifier,
    image: String?,
    name: String?,
    symbol: String?,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = image?.decodeUrl()),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = name.toString(),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = symbol.toString(), fontSize = 14.sp, color = Color.White,
            textAlign = TextAlign.Center,
        )
    }
}