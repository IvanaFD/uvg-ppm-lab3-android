package com.example.laboratorio_3

import ads_mobile_sdk.h4
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.laboratorio_3.ui.theme.Laboratorio_3Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio_3Theme {

                AppElements()
            }
        }
    }
}
//estructura con todos los elementos de la app
@Composable
fun AppElements(modifier: Modifier = Modifier){
    val mensajeError = stringResource(id = R.string.empty_task_error)
    val taskList = remember { mutableStateListOf<String>() }
    var newTaskText by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()




    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) } // Aquí se mostrarán los Snackbars
    )  { paddingValues ->


        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título visible en la parte superior
            Text(
                text = stringResource(id = R.string.app_title),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Campo para ingresar nueva tarea
            OutlinedTextField(
                value = newTaskText,
                onValueChange = { newTaskText = it },
                label = { Text(stringResource(id = R.string.new_task_label)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            //Boton

            Button(
                onClick = {
                    if (newTaskText.isNotBlank()) {
                        taskList.add(0, newTaskText)
                        newTaskText = ""
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = mensajeError,duration = SnackbarDuration.Short
                            )
                        }
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {

                Text(stringResource(id = R.string.add_task_button)) // AQUÍ
            }


            Spacer(modifier = Modifier.height(16.dp))

            // List de tareas usando LazyColumn
            if (taskList.isEmpty()) {
                Text(
                    text = "No hay tareas pendientes.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 16.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(taskList) { task ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Text(
                                text = task,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}


//Preview de los elementos
@Preview(showBackground = true)
@Composable
fun AppPreview() {
    Laboratorio_3Theme {
        AppElements()
    }
}