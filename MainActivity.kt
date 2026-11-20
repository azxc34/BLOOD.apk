package com.blood.performance

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val BloodRed = Color(0xFFFF2638)
private val Background = Color(0xFF09090C)
private val Panel = Color(0xFF17171D)
private val Muted = Color(0xFFB7B7C2)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = BloodRed,
                    background = Background,
                    surface = Panel,
                    onPrimary = Color.White,
                    onBackground = Color.White,
                    onSurface = Color.White
                )
            ) {
                BloodHome(
                    onOpenRoblox = { openRoblox() },
                    onAppSettings = { openOwnSettings() }
                )
            }
        }
    }

    private fun openRoblox() {
        val launch = packageManager.getLaunchIntentForPackage("com.roblox.client")
        if (launch != null) {
            startActivity(launch)
        } else {
            startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=com.roblox.client")))
        }
    }

    private fun openOwnSettings() {
        startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:$packageName")))
    }
}

@Composable
private fun BloodHome(onOpenRoblox: () -> Unit, onAppSettings: () -> Unit) {
    var showTips by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize().background(Background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 28.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("BLO", color = Color.White, fontSize = 34.sp, fontWeight = FontWeight.Black)
                Text("OD", color = BloodRed, fontSize = 34.sp, fontWeight = FontWeight.Black)
                Spacer(Modifier.weight(1f))
                Text("v0.1.0", color = Muted, fontSize = 12.sp)
            }

            Text("ROBLOX PERFORMANCE COMPANION", color = Muted, fontSize = 11.sp,
                fontWeight = FontWeight.Bold, letterSpacing = 1.3.sp)

            Card(
                colors = CardDefaults.cardColors(containerColor = Panel),
                shape = RoundedCornerShape(22.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Icon(Icons.Default.Bolt, contentDescription = null,
                        tint = BloodRed, modifier = Modifier.size(34.dp))
                    Text("Jogue com mais consciência.", fontSize = 23.sp, fontWeight = FontWeight.Bold)
                    Text(
                        "Atalhos e dicas para preparar seu celular antes de abrir o Roblox.",
                        color = Muted, fontSize = 14.sp
                    )
                    Button(
                        onClick = onOpenRoblox,
                        modifier = Modifier.fillMaxWidth().height(54.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BloodRed),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(Icons.Default.Gamepad, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("ABRIR ROBLOX", fontWeight = FontWeight.ExtraBold)
                    }
                }
            }

            Text("CENTRAL DE DESEMPENHO", color = BloodRed, fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp, fontSize = 12.sp)

            InfoCard(
                title = "Modo leve",
                body = "Dentro do Roblox, abra as configurações durante o jogo e reduza o modo gráfico para Manual e um nível baixo."
            )
            InfoCard(
                title = "Antes de jogar",
                body = "Feche aplicativos que não está usando e deixe espaço livre no armazenamento do celular."
            )
            InfoCard(
                title = "Conexão estável",
                body = "Prefira uma conexão Wi-Fi estável. Ping alto e perda de pacotes podem parecer travamentos."
            )

            OutlinedButton(
                onClick = { showTips = true },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
            ) {
                Icon(Icons.Default.Info, contentDescription = null, tint = BloodRed)
                Spacer(Modifier.width(8.dp))
                Text("VER AVISO E LIMITAÇÕES")
            }

            TextButton(onClick = onAppSettings, modifier = Modifier.fillMaxWidth()) {
                Text("Configurações do BLOOD", color = Muted)
            }

            Text(
                "BLOOD é um projeto independente e não é afiliado à Roblox Corporation. " +
                "Ele não altera o cliente, não desbloqueia FPS e não garante aumento de desempenho.",
                color = Muted, fontSize = 11.sp, lineHeight = 16.sp
            )
        }
    }

    if (showTips) {
        AlertDialog(
            onDismissRequest = { showTips = false },
            title = { Text("O que o BLOOD consegue fazer?") },
            text = {
                Text(
                    "Esta primeira versão abre o aplicativo oficial do Roblox e reúne orientações seguras. " +
                    "Ela não consegue mudar os gráficos internos, encerrar processos do sistema ou garantir mais FPS. " +
                    "Nunca digite sua senha Roblox no BLOOD."
                )
            },
            confirmButton = {
                TextButton(onClick = { showTips = false }) { Text("ENTENDI") }
            },
            containerColor = Panel,
            titleContentColor = Color.White,
            textContentColor = Muted
        )
    }
}

@Composable
private fun InfoCard(title: String, body: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Panel),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(body, color = Muted, fontSize = 13.sp, lineHeight = 18.sp)
        }
    }
}
