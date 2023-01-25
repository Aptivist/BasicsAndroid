package com.aptivist.basicscourse

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

class MainActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: MainActivityComposeViewModel by viewModels()
            val context = LocalContext.current

            val name by remember {
                viewModel.pName
            }

            val surname by remember {
                viewModel.pSurname
            }

            LaunchedEffect(true) {
                viewModel.navigationAction.collect() {
                    withContext(Dispatchers.Main) {
                        when(it){
                            is Actions.Navigate -> {
                                val intent = Intent(context, MainActivity::class.java)
                                startActivity(intent)
                            }
                            Actions.ShowError -> Log.e("Error", " This is an error")
                            is Actions.ShowToast -> {
                                Toast.makeText(context, "Navigation", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }

            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){

                TextField(value = name, onValueChange = { viewModel.setNewName(it) }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal))
                TextField(value = surname, onValueChange = { viewModel.setNewSurname(it) })
                Button(onClick = { viewModel.savePerson() }) {
                    Text(text = getString(R.string.save_person))
                }
                Button(onClick = { viewModel.clearScreen()  }) {
                    Text(text = getString(R.string.clear_screen))
                }
                Button(onClick = { viewModel.getPerson() }) {
                    Text(text = getString(R.string.get_person))
                }
                Button(onClick = { /*TODO*/ }) {

                }
            }
        }
    }
}