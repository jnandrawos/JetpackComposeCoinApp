package com.example.coinapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


enum class LoaderStatus {
    LOADING, ERROR, SUCCESS
}

@Composable
fun LazyLoader(
    status: LoaderStatus?,
    content: @Composable () -> Unit,
) = when (status) {
    LoaderStatus.LOADING -> Box {
        content()
        CircularProgressIndicator(
            color = Color.White, modifier = Modifier.align(Alignment.Center)
        )
    }

    LoaderStatus.ERROR -> {
        Box {
            content()
            Icon(
                imageVector = Icons.Rounded.Error,
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    else -> {
        content()
    }
}