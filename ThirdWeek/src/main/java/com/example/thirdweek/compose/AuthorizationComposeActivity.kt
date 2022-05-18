package com.example.thirdweek.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdweek.R
import com.example.thirdweek.compose.ui.theme.Blue
import com.example.thirdweek.compose.ui.theme.LightBlue
import com.example.thirdweek.compose.ui.theme.Grey
import com.example.thirdweek.compose.ui.theme.WildberriesInternshipTheme

class AuthorizationComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WildberriesInternshipTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(0.dp, 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    FacebookIcon(resId = R.drawable.facebook)
                    TextInput(
                        "Введите номер телефона или адресс электронной почты", 32
                    )
                    TextInput(
                        "Введите пароль", 8
                    )
                    LoginButton()
                    Text(
                        text = "Забыли\nпароль?", color = Blue, modifier = Modifier
                            .padding(top = 16.dp),
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }
    }
}

@Composable
fun FacebookIcon(@DrawableRes resId: Int) {
    val image = painterResource(id = resId)
    Column(
        modifier = Modifier.clip(RoundedCornerShape(40.dp))
    ) {
        Image(
            painter = image,
            modifier = Modifier.height(80.dp),
            contentDescription = null
        )
    }
}


@Composable
fun TextInput(text: String, topPadding: Int) {
    var value by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier
            .padding(end = 32.dp, start = 32.dp, top = topPadding.dp)
            .fillMaxWidth(),
        value = value,
        onValueChange = { value = it },
        placeholder = {
            Text(
                text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        visualTransformation = PasswordVisualTransformation(),
        maxLines = 1,
        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = LightBlue,
            backgroundColor = Color.White,
            focusedIndicatorColor = LightBlue
        )
    )
}

@Composable
fun LoginButton() {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Blue
        ),
        modifier = Modifier
            .padding(end = 32.dp, start = 32.dp, top = 32.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Войти", color = Grey, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WildberriesInternshipTheme {
        LoginButton()
    }
}