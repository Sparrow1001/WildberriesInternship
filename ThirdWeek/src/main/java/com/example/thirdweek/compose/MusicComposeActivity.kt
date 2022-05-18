package com.example.thirdweek.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdweek.R
import com.example.thirdweek.compose.ui.theme.Boloto
import com.example.thirdweek.compose.ui.theme.Grey
import com.example.thirdweek.compose.ui.theme.WildberriesInternshipTheme

class MusicComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WildberriesInternshipTheme {
                MainScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Boloto)
    ) {

        Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(1.0f),
                verticalAlignment = Alignment.CenterVertically

            ) {
                ImageButton(16, 0, 16, R.drawable.ic_arrow_down)

                Spacer(modifier = Modifier.fillMaxWidth(0.25f))

                Column(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Сейчас играет", textAlign = TextAlign.Center,
                        modifier = Modifier.wrapContentWidth()
                    )
                    Text(
                        text = "Название плейлиста", textAlign = TextAlign.Center,
                        modifier = Modifier.wrapContentWidth()
                    )
                }

                Spacer(modifier = Modifier.fillMaxWidth(0.4f))

                ImageButton(8, 0, 16, R.drawable.ic_airplay)
                ImageButton(8, 0, 16, R.drawable.ic_list)


            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(250.dp)
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {

                }
            }

            Row(
                modifier = Modifier
                    .offset(y = 32.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.wrapContentWidth(),
                ) {
                    Text(
                        text = "Smells like teen spirit", textAlign = TextAlign.Center,
                        modifier = Modifier.wrapContentWidth(),
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Text(
                        text = "Nirvana", textAlign = TextAlign.Center,
                        modifier = Modifier.wrapContentWidth(),
                        color = Grey
                    )
                }

                ImageButton(16, 8, 0, R.drawable.ic_reply)
                ImageButton(16, 8, 0, R.drawable.ic_more)

            }

            Slider(
                value = 0.0f, onValueChange = {}, modifier = Modifier.padding(32.dp),
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Grey,
                    inactiveTrackColor = Grey
                )
            )

            Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
                ImageButton(
                    paddingStart = 16,
                    paddingEnd = 0,
                    paddingTop = 8,
                    icon = R.drawable.ic_unfavorite
                )
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(60.dp)
                        .padding(start = 0.dp, top = 8.dp, end = 0.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_previous), contentDescription = "", tint = Grey)
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(100.dp)
                        .padding(start = 0.dp, top = 8.dp, end = 0.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_play), contentDescription = "", tint = Grey)
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(60.dp)
                        .padding(start = 0.dp, top = 8.dp, end = 0.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_next), contentDescription = "", tint = Grey)
                }
                ImageButton(
                    paddingStart = 16,
                    paddingEnd = 0,
                    paddingTop = 8,
                    icon = R.drawable.ic_favorite
                )
            }

            Row(modifier = Modifier.fillMaxWidth().offset(y= 80.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
                ImageButton(
                    paddingStart = 16,
                    paddingEnd = 0,
                    paddingTop = 8,
                    icon = R.drawable.ic_renew
                )

                ImageButton(
                    paddingStart = 0,
                    paddingEnd = 0,
                    paddingTop = 8,
                    icon = R.drawable.ic_high_quality
                )

                ImageButton(
                    paddingStart = 0,
                    paddingEnd = 0,
                    paddingTop = 8,
                    icon = R.drawable.ic_baseline_timer_24
                )

                ImageButton(
                    paddingStart = 0,
                    paddingEnd = 16,
                    paddingTop = 8,
                    icon = R.drawable.ic_shuffle
                )
            }


        }


    }
}


@Composable
fun ImageButton(paddingStart: Int, paddingEnd: Int, paddingTop: Int, icon: Int) {
    IconButton(
        onClick = {},
        modifier = Modifier
            .size(40.dp, 40.dp)
            .padding(start = paddingStart.dp, top = paddingTop.dp, end = paddingEnd.dp)
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = "", tint = Grey)
    }
}