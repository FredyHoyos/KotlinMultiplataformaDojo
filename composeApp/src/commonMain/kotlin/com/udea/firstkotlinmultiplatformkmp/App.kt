package com.udea.firstkotlinmultiplatformkmp
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "app") {
        composable("App") { RunApp(navController = navController) }
        composable("Screen2/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            Screen2(name = name, navController = navController)
        }
    }
}

@Composable
fun RunApp(navController: NavController) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Enter your name", style = MaterialTheme.typography.h6)

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = name,
            onValueChange = {
                name = it
                errorMessage = ""
            },
            modifier = Modifier.width(200.dp)
        )

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (name.text.isNotBlank()) {
                navController.navigate("screen2/${name.text}")
            } else {
                errorMessage = "The name field cannot be empty"
            }
        }) {
            Text(text = "Click me")
        }
    }
}