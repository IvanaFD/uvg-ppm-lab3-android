package com.example.laboratorio_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.laboratorio_3.ui.theme.Laboratorio_3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppPreview()

        }
    }
}
//estructura con todos los elementos de la app
@Composable
fun AppElements(){
    // Estado para la lista de tareas (inicialmente vacía)
    val taskList = remember { mutableStateListOf<String>() }

    // Estado para el texto de la nueva tarea
    var newTask by remember { mutableStateOf("") }

    // Estado del Scaffold para poder mostrar Snackbar
    val scaffoldState = rememberScaffoldState()

    // Alcance de corrutinas para lanzar el Snackbar
    val coroutineScope = rememberCoroutineScope()

    //Scaffold envuelve todo y permite manejar el snackbarHostState
    Scaffold(scaffoldState = scaffoldState) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Título visible en la parte superior
            Text(
                text = stringResource(id = R.string.app_title), // Título desde strings.xml
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Campo para ingresar nueva tarea
            TextField(
                value = newTask,
                onValueChange = { newTask = it },
                label = { Text(stringResource(id = R.string.new_task_label)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // List de tareas usando LazyColumn
            LazyColumn {
                items(taskList) { task ->
                    Text(
                        text = task,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}


//Preview de los elementos
@Preview(showBackground = true)
@Composable
fun AppPreview() {

}