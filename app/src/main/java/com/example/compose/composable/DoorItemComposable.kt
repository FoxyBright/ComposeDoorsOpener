package com.example.compose.composable

import android.annotation.SuppressLint
import android.content.res.Resources
import android.widget.Toast
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose.R
import com.example.compose.models.Door
import com.example.compose.ui.theme.NetworkColor
import com.example.compose.ui.theme.TextColor
import com.example.compose.ui.theme.White
import kotlin.math.roundToInt

@Composable
@SuppressLint("UnusedTransitionTargetStateParameter")
fun DoorItem(door: Door, isRevealed: Boolean, onCollapse: () -> Unit, onExpand: () -> Unit) {
    val context = LocalContext.current
    val rounded: Int
    val statusBarPadding: Int
    val textPadding: Int
    if (door.snapshot != null) {
        rounded = 0
        statusBarPadding = 0
        textPadding = 0
    } else {
        rounded = 10
        statusBarPadding = 10
        textPadding = 5
    }
    val offsetTransition = updateTransition(
        remember {
            MutableTransitionState(isRevealed).apply { targetState = !isRevealed }
        },
        "Transition"
    ).animateFloat(
        { tween(durationMillis = 500) },
        "OffsetTransition",
        { if (isRevealed) -(112f * Resources.getSystem().displayMetrics.density + 0.5f) else 0f },
    ).value.roundToInt()
    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .offset { IntOffset(offsetTransition, 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    when {
                        dragAmount >= 6 -> onCollapse()
                        dragAmount < -6 -> onExpand()
                    }
                }
            }
    ) {
        Column {
            if (door.snapshot != null) {
                Card {
                    AsyncImage(
                        door.snapshot,
                        stringResource(id = R.string.camera_preview),
                        Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
                        painterResource(R.drawable.loading),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .height(230.dp),
                        Arrangement.SpaceAround,
                        Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(R.drawable.play_button),
                            stringResource(R.string.play),
                            Modifier
                                .size(60.dp)
                                .padding(top = 5.dp)
                                .clickable {
                                    //TODO: Запуск интеркома
                                },
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 10.dp,
                            bottomEnd = 10.dp,
                            topStart = rounded.dp,
                            topEnd = rounded.dp
                        )
                    )
                    .background(White)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    Arrangement.SpaceBetween
                ) {
                    Column(
                        Modifier
                            .padding(bottom = statusBarPadding.dp)
                    ) {
                        Text(
                            door.name,
                            Modifier
                                .padding(textPadding.dp),
                            TextColor,
                            17.sp
                        )
                        if (door.snapshot != null) {
                            Text(
                                stringResource(R.string.online),
                                color = NetworkColor,
                                fontSize = 14.sp,
                            )
                        }
                    }
                    Image(
                        painterResource(R.drawable.locker),
                        stringResource(R.string.door_lock),
                        Modifier
                            .size(32.dp)
                            .padding(top = 5.dp)
                            .clickable {
                                Toast
                                    .makeText(
                                        context,
                                        context.getString(
                                            R.string.door_is_opened,
                                            door.name
                                        ),
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DoorItemPreview() {
    DoorItem(
        Door(
            1,
            "Домофон",
            "image",
            "Гостинная",
            true
        ),
        false,
        {},
        {}
    )
}

@Preview
@Composable
fun DoorItemWithoutImagePreview() {
    DoorItem(
        Door(
            1,
            "Домофон",
            null,
            "Гостинная",
            true
        ),
        false,
        {},
        {}
    )
}