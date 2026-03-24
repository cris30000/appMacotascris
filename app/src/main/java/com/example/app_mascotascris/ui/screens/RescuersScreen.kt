package com.example.app_mascotascris.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_mascotascris.ui.theme.PrimaryPurple
import com.example.app_mascotascris.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RescuersScreen(navController: NavController) {
    val rescuers = listOf(
        Rescuer("Fundación Huellitas", "Bogotá, Colombia", "4.8"),
        Rescuer("Rescatista Independiente - Juan", "Medellín, Colombia", "4.9"),
        Rescuer("Refugio Esperanza", "Cali, Colombia", "4.7")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Rescatistas y Fundaciones", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryPurple)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(rescuers) { rescuer ->
                RescuerCard(rescuer)
            }
        }
    }
}

data class Rescuer(val name: String, val location: String, val rating: String)

@Composable
fun RescuerCard(rescuer: Rescuer) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(48.dp).clip(CircleShape),
                color = LightGray
            ) {
                Icon(Icons.Default.Person, contentDescription = null, tint = PrimaryPurple, modifier = Modifier.padding(8.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(rescuer.name, fontWeight = FontWeight.Bold)
                Text(rescuer.location, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
            Text("★ ${rescuer.rating}", color = Color(0xFFFFB300), fontWeight = FontWeight.Bold)
        }
    }
}
