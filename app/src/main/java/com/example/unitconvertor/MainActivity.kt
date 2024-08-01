package com.example.unitconvertor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {

                    UnitConvertor()
                }

            }
        }
    }
}

@Composable
fun UnitConvertor(){
    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf("Meters")}
    var iExpanded by remember { mutableStateOf(false)}
    var oExpanded by remember { mutableStateOf(false)}
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oconversionFactor = remember { mutableStateOf(1.00) }


    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0/oconversionFactor.value).roundToInt() /100.0
        outputValue = result.toString()
    }


    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("Unit Convertor"/*, modifier = Modifier.padding(50.dp) Another way to add padding*/,
            style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
            inputValue = it
            convertUnits()
        },
        label = { Text("Enter value")},)
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //Input Box
            Box{
                //Input Button
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                    DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded = false  }) {
                        DropdownMenuItem(
                            text = { Text(text = "Centimeters")},
                            onClick = {
                                inputUnit="Centimeters"
                                iExpanded = false
                                conversionFactor.value = 0.01
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Meters")},
                            onClick = {
                                inputUnit="Meters"
                                iExpanded = false
                                conversionFactor.value = 1.0
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Feet")},
                            onClick = {
                                inputUnit="Feet"
                                iExpanded = false
                                conversionFactor.value = 0.3048
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Millimeters")},
                            onClick = {
                                inputUnit="Millimeters"
                                iExpanded = false
                                conversionFactor.value = 0.001
                                convertUnits()
                            }
                        )
                        
                    }


            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output Box
            Box {
                //Output Button
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded , onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters")},
                        onClick = {
                            outputUnit="Centimeters"
                            oExpanded = false
                            oconversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters")},
                        onClick = {
                            outputUnit="Meters"
                            oExpanded = false
                            oconversionFactor.value = 1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet")},
                        onClick = {
                            outputUnit="Feet"
                            oExpanded = false
                            oconversionFactor.value = 0.3048
                            convertUnits() }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters")},
                        onClick = {
                            outputUnit="Millimeters"
                            oExpanded = false
                            oconversionFactor.value = 0.001
                            convertUnits()
                        }
                    )

                }


            }

            }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result : $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium)
        }


    }


@Composable
@Preview(showBackground = true)
fun UnitConvertorPreview(){
    UnitConvertor()
}