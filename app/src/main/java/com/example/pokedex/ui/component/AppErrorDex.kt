package com.example.pokedex.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokedex.data.enums.Error
import com.example.pokedex.ui.theme.Body3
import com.example.pokedex.ui.theme.Headline
import com.example.pokedex.ui.theme.primary
import com.example.pokedex.ui.theme.white


@Composable
fun AppErrorDex(
    type: Error = Error.error,
    onRetry: () -> Unit
) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                type.message,
                style = Headline
            )

            Spacer(Modifier.height(8.dp))

            Button(
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = white,
                    containerColor = primary
                ),
                onClick = onRetry
            ) {
                Text(
                    "Retry",
                    style = Body3
                )
            }
        }
    }
}