package com.example.app_mascotascris.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_mascotascris.ui.theme.PrimaryPurple
import com.example.app_mascotascris.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RescuesScreen(navController: NavController) {
    val recentRescues = listOf(
        RescueItem("Perrito herido en la calle 5", "Hace 2 horas", "En proceso"),
        RescueItem("Gatito atrapado en árbol", "Hace 5 horas", "Rescatado"),
        RescueItem("Ave con ala rota", "Ayer", "En clínica")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Rescates", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryPurple)
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /* Formulario de nuevo rescate */ },
                containerColor = PrimaryPurple,
                contentColor = Color.White,
                icon = { Icon(Icons.Default.Add, contentDescription = null) },
                text = { Text("Reportar Rescate") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Últimos Rescates",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = PrimaryPurple
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(recentRescues) { rescue ->
                    RescueCard(rescue)
                }
            }
        }
    }
}

data class RescueItem(val description: String, val time: String, val status: String)

@Composable
fun RescueCard(rescue: RescueItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(rescue.description, fontWeight = FontWeight.SemiBold)
                Text(rescue.time, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
            Surface(
                color = if (rescue.status == "Rescatado") Color(0xFF4CAF50) else PrimaryPurple,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = rescue.status,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}
