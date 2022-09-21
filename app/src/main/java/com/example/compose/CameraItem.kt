package com.example.compose

import com.example.compose.models.Camera
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.Circle
import com.example.compose.ui.theme.HeadersColor
import com.example.compose.ui.theme.TextColor
import com.example.compose.ui.theme.White

@Composable
fun CameraItem(camera: Camera) {
    val context = LocalContext.current
    val alfa = if (camera.rec) 100f else 0f
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
            .pointerInput(Unit){

            }
    ) {
        Column {
            Text(
                modifier = Modifier
                    .padding(
                        start = 2.dp,
                        bottom = 16.dp
                    ),
                text = camera.room,
                fontFamily = Circle,
                color = HeadersColor,
                fontSize = 21.sp
            )
            Card {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            shape = RoundedCornerShape(
                                topStart = 10.dp,
                                topEnd = 10.dp
                            )
                        ),
                    painter = painterResource(camera.image),
                    contentScale = ContentScale.Crop,
                    contentDescription = "camera's preview"
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .height(230.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Image(
                        modifier = Modifier
                            .size(60.dp)
                            .padding(top = 5.dp)
                            .clickable(
                                onClick = {
                                    Toast
                                        .makeText(
                                            context,
                                            "${camera.name} не отвечает",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                            ),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(
                            id = R.drawable.play_button
                        ),
                        contentDescription = "play"
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(20.dp)
                            .alpha(alfa),
                        painter = painterResource(
                            id = R.drawable.rec
                        ),
                        contentDescription = "record"
                    )
                    if (camera.favorite) {
                        Image(
                            modifier = Modifier
                                .padding(7.dp)
                                .size(20.dp),
                            painter = painterResource(
                                id = R.drawable.star
                            ),
                            contentDescription = "star"
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(
                            bottomStart = 10.dp,
                            bottomEnd = 10.dp,
                        )
                    )
                    .background(White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 26.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = camera.name,
                        fontSize = 17.sp,
                        color = TextColor,
                        fontFamily = Circle
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CameraItemPreview() {
    CameraItem(Camera("Камера", R.drawable.test_img, "Комната", true, rec = true))
}
