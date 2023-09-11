package com.example.coinapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coinapp.R

@Preview
@Composable
fun TransactionTile(
    isDeposit: Boolean = true,
    date: String = "06-09-2023 17:08",
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isDeposit) Color.Green.copy(alpha = 0.5f) else Color.Red.copy(alpha = 0.7f),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = if (isDeposit) stringResource(R.string.logs_label_deposit) else stringResource(
                    R.string.logs_label_withdraw
                ), fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = date, fontSize = 16.sp, color = Color.White
        )
    }
}