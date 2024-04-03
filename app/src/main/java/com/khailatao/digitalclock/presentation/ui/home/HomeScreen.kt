package com.khailatao.digitalclock.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.khailatao.digitalclock.presentation.ui.component.DigitalClock
import com.khailatao.digitalclock.presentation.ui.theme.DigitalClockTheme
import com.khailatao.digitalclock.presentation.ui.theme.Purple80
import com.khailatao.digitalclock.presentation.ui.theme.PurpleGrey40
import com.khailatao.digitalclock.presentation.ui.theme.PurpleGrey80
import com.khailatao.digitalclock.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    val homeViewModel: HomeViewModel = koinViewModel()
    HomeScreen(
        modifier = Modifier.fillMaxSize(),
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    var currentTime by remember { mutableLongStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = Clock.System.now().toEpochMilliseconds()
            delay(1000L) // Update every second (1000 milliseconds)
        }
    }
    val dateTimeHomeDisplay =
        Constants.convertMillisToDateHome(currentTime)
    ConstraintLayout(
        modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Gray,
                    Color.Black
                )
            )
        )
    ) {
        val (header, body, footer) = createRefs()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .constrainAs(header) {
                    top.linkTo(parent.top)
                },
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.End) {
                Text(text = dateTimeHomeDisplay.monthString, fontWeight = FontWeight.Medium)
                Text(
                    modifier = Modifier.padding(horizontal = 30.dp),
                    text = dateTimeHomeDisplay.date,
                    fontWeight = FontWeight.Normal,
                    fontSize = 40.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = PurpleGrey80
                )
            }
        }
        DigitalClock(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp, vertical = 100.dp)
                .aspectRatio(1f)
                .constrainAs(body) {
                    top.linkTo(header.bottom)
                },
            timeStamp = Clock.System.now().toEpochMilliseconds(),
            colorHand = Purple80,
            sizeMoreOutline = 50.dp,
            sizeHandSecond = 5.dp
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
                .constrainAs(footer) {
                    bottom.linkTo(parent.bottom)
                },
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.End) {
                Text(text = dateTimeHomeDisplay.typeTime, fontWeight = FontWeight.Medium, color = Color.White)
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 100.sp,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 10.sp,
                            color = PurpleGrey80
                        )
                    ) {
                        append(dateTimeHomeDisplay.hour)
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 100.sp,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 5.sp,
                            color = PurpleGrey40
                        )
                    ) {
                        append(":")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 100.sp,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 10.sp,
                            color = PurpleGrey80
                        )
                    ) {
                        append(dateTimeHomeDisplay.minute)
                    }
                })
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun HomeScreenPreview() {
    DigitalClockTheme {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
        )
    }
}