package com.example.composetemplate.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.SubcomposeAsyncImage

@Composable
fun PhotoDetailScreen(title: String?, url: String?) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        val (imageRef, titleRef) = createRefs()
        SubcomposeAsyncImage(
            modifier = Modifier.constrainAs(imageRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.ratio("1:1")
            },
            model = url,
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier.padding(25.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            },
            contentDescription = "thumbnail"
        )
        Text(
            modifier = Modifier.constrainAs(titleRef) {
                top.linkTo(imageRef.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = title ?: "",
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}