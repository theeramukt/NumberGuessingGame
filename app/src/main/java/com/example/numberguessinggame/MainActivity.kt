package com.example.numberguessinggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numberguessinggame.ui.theme.NumberGuessingGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessingGameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GuessNumberApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun GuessNumberApp() {
    GuessNumButton(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessNumButton(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    var randNum by remember{ mutableStateOf(Random.nextInt(1, 1001)) }
    var hint by remember { mutableStateOf("") }
    var count_round by remember { mutableStateOf(0) }
    var play by remember { mutableStateOf(true) }


    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(R.string.guess),
            fontSize = 24.sp
        )
        
        Spacer(modifier = Modifier.height(220.dp))
        
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Your Guess") }
        )

        Text(text = hint,
            fontSize = 24.sp,
        )
        
        Spacer(modifier = Modifier.height(200.dp))
        
        Text(text = stringResource(R.string.count, count_round),
        )

        Spacer(modifier = Modifier.height(20.dp))
        
        Button(
            onClick = {
                if (play){
                    if (text.toInt() < randNum) {
                        count_round++
                        hint = "Hint : Too Low"
                    } else if (text.toInt() > randNum) {
                        count_round++
                        hint = "Hint : Too High"
                    }
                    else {
                        hint = "Correct"
                        play = false
                    }
                } else {
                    count_round = 0
                    play = true
                    randNum = (1..1000).random()
                }
            },
        ) {
            if (play){
                Text(text = stringResource(R.string.enter), fontSize = 24.sp)
            } else {
                Text(text = stringResource(R.string.new_game), fontSize = 24.sp)
            }
        }

    }
}
