package com.example.mvvm1.guardar.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun GuardarDatos(ViewModel: ViewModel) {

    val db = FirebaseFirestore.getInstance()

    var nombre_coleccion = "repe"

    val nombre_tropa:String by ViewModel.nombre_tropa.observeAsState(initial = "")
    val espacio_tropa:String by ViewModel.espacio_tropa.observeAsState(initial = "")
    val nivel_tropa:String by ViewModel.nivel_tropa.observeAsState(initial = "")
    val danio_tropa:String by ViewModel.danio_tropa.observeAsState(initial = "")
    val vida_tropa:String by ViewModel.vida_tropa.observeAsState(initial = "")


    val isButtonEnable:Boolean by ViewModel.isButtonEnable.observeAsState (initial = false)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 112.dp,
        shape = MaterialTheme.shapes.small,
        backgroundColor = Color.White,
        contentColor = Color.DarkGray,
        border = BorderStroke(1.dp, Color.Blue)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
                .padding(start = 10.dp)
                .padding(end = 10.dp)

        ) {

            Text(
                text = "Guardar tropa",
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.size(20.dp))

            OutlinedTextField(
                value = nombre_tropa,
                onValueChange = { ViewModel.onCompletedFields(nombre_tropa = it, espacio_tropa=espacio_tropa, nivel_tropa = nivel_tropa, danio_tropa = danio_tropa, vida_tropa = vida_tropa) },
                label = { Text("Introduce el nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            OutlinedTextField(
                value = espacio_tropa,
                onValueChange = { ViewModel.onCompletedFields(nombre_tropa = nombre_tropa, espacio_tropa=it, nivel_tropa = nivel_tropa, danio_tropa = danio_tropa, vida_tropa = vida_tropa) },
                label = { Text("Introduce el espacio") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )
            Spacer(modifier = Modifier.size(5.dp))

            OutlinedTextField(
                value = nivel_tropa,
                onValueChange = { ViewModel.onCompletedFields(nombre_tropa = nombre_tropa, espacio_tropa=espacio_tropa, nivel_tropa = it, danio_tropa = danio_tropa, vida_tropa = vida_tropa) },
                label = { Text("Introduce el nivel") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )
            Spacer(modifier = Modifier.size(5.dp))
            OutlinedTextField(
                value = danio_tropa,
                onValueChange = { ViewModel.onCompletedFields(nombre_tropa = nombre_tropa, espacio_tropa=espacio_tropa, nivel_tropa = nivel_tropa, danio_tropa = it, vida_tropa = vida_tropa) },
                label = { Text("Introduce el daño/segundo") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )
            OutlinedTextField(
                value = vida_tropa,
                onValueChange = { ViewModel.onCompletedFields(nombre_tropa = nombre_tropa, espacio_tropa=espacio_tropa, nivel_tropa = nivel_tropa, danio_tropa = danio_tropa, vida_tropa = it) },
                label = { Text("Introduce la vida") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))


            var mensaje_confirmacion by remember { mutableStateOf("") }
            val dato = hashMapOf(
                "nombre" to nombre_tropa.toString(),
                "espacio" to espacio_tropa.toString(),
                "nivel" to nivel_tropa.toString(),
                "danio" to danio_tropa.toString(),
                "vida" to vida_tropa.toString(),
            )
            Button(

                onClick = {

                    db.collection(nombre_coleccion)
                        .document(nombre_tropa)
                        .set(dato)
                        .addOnSuccessListener {
                            mensaje_confirmacion ="Datos guardados correctamente"

                        }
                        .addOnFailureListener {
                            mensaje_confirmacion ="No se ha podido guardar"

                        }
                },

                // EJEMPLO DE VIEWMODEL PARA HABILITAR EL BOTÓN
                enabled= isButtonEnable,

                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White,
                    disabledBackgroundColor = Color(0xFF78C8F9),
                    disabledContentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            )
            {

                Text(text = "Guardar")

            }
            Spacer(modifier = Modifier.size(5.dp))
            Text(text = mensaje_confirmacion)
        }
    }
}