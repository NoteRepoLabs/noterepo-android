package dev.noterepo.app.presentation.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScreenLayout(
    modifier: Modifier = Modifier,
    shouldCenter: Boolean = false,
    innerPadding: PaddingValues,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(
            modifier = modifier
                .padding(12.dp)
                .align(if (shouldCenter) Alignment.Center else Alignment.TopCenter)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}