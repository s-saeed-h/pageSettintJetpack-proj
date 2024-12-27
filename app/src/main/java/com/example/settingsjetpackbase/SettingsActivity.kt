package com.example.settingsjetpackbase

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SettingsActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val pref = getSharedPreferences("pref", MODE_PRIVATE)
            val selectedItem = remember {
                mutableStateOf(pref.getString("color", "شب"))
            }
            val statecheck = remember {
                mutableStateOf(pref.getBoolean("statecheck", false))
            }
            val statecheck1 = remember {
                mutableStateOf(pref.getBoolean("statecheck1", false))
            }
            val statecheck2 = remember {
                mutableStateOf(pref.getBoolean("statecheck2", false))
            }
            val stateAds = remember {
                mutableStateOf(pref.getBoolean("stateAds", false))
            }
            val stateseek = remember {
                mutableStateOf(pref.getFloat("stateseek", .12f))
            }
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    "تنظیمات:",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    style = TextStyle(textDirection = TextDirection.Rtl)

                )
                Spacer(modifier = Modifier.height(20.dp))
                ColorThems(selectedItem)
                Spacer(modifier = Modifier.height(20.dp))
                Topic(statecheck, statecheck1, statecheck2)
                Spacer(modifier = Modifier.height(20.dp))
                Ads(stateAds)
                Spacer(modifier = Modifier.height(20.dp))
                FontSizeApp(stateseek)
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    val edit = pref.edit()
                    edit.putString("color", selectedItem.value)
                    edit.putBoolean("statecheck", statecheck.value)
                    edit.putBoolean("statecheck1", statecheck1.value)
                    edit.putBoolean("statecheck2", statecheck2.value)
                    edit.putBoolean("stateAds", stateAds.value)
                    edit.putFloat("stateseek", stateseek.value)
                    edit.apply()
                }) {
                    Text(
                        "ذخیره سازی",
                        fontSize = 22.sp
                    )

                }

            }
        }
    }

    @Composable
    fun ColorThems(selectedItem: MutableState<String?>) {
        val radiosButton = listOf("روز", "شب", "غروب")
        Row {
            radiosButton.forEach {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = it,
                        fontSize = 22.sp

                    )

                    RadioButton(
                        selected = (selectedItem.value == it),
                        onClick = {
                            selectedItem.value = it
                        }
                    )

                }

            }

        }
    }

    @Composable
    fun Topic(
        statecheck: MutableState<Boolean>,
        statecheck1: MutableState<Boolean>,
        statecheck2: MutableState<Boolean>
    ) {

        Row() {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "شنبه",
                    fontSize = 22.sp,

                    )
                Checkbox(
                    checked = statecheck.value,
                    onCheckedChange = {
                        statecheck.value = it
                    })

            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "یکشنبه",
                    fontSize = 22.sp
                )
                Checkbox(
                    checked = statecheck1.value,
                    onCheckedChange = {
                        statecheck1.value = it
                    })

            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "دوشنبه",
                    fontSize = 22.sp
                )
                Checkbox(
                    checked = statecheck2.value,
                    onCheckedChange = {
                        statecheck2.value = it
                    })


            }
        }

    }

    @Composable
    fun Ads(
        stateAds: MutableState<Boolean>
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {


            Switch(
                checked = stateAds.value, onCheckedChange = {
                    stateAds.value = it
                },
                modifier = Modifier.scale(.5f)
            )

            Text(
                "تبلیغات برنامه",
                fontSize = 22.sp
            )

        }
    }

    @Composable
    fun FontSizeApp(
        stateseek: MutableState<Float>
    ) {

        Slider(
            value = stateseek.value,
            onValueChange = {
                stateseek.value = it
            },
            colors = SliderDefaults.colors(
                activeTrackColor = Color.Green,
                thumbColor = Color.Blue
            )

        )
        Text(Getfont(stateseek.value).toString())
        Text(
            "متن نمونه",
            fontSize = Getfont(stateseek.value).sp
        )

    }

    private fun Getfont(size: Float): Int {
        val fs = "%.2f".format(size)//تا دورقم اعشار برمیگرداند
        return fs.replace(".", "").toInt()
    }

}



