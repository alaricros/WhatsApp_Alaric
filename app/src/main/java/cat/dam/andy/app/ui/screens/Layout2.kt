package cat.dam.andy.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
// Eliminem la importació de recursos si usem colors directes, o la mantenim si R.color és correcte
// import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cat.dam.andy.app.R // Mantenim la importació per si es necessita per a altres recursos (encara que eliminem colorResource)
import cat.dam.andy.app.ui.theme.AppTheme

// --- PANTALLA PRINCIPAL DE XAT (ESTÀTICA) ---

@Composable
fun Layout2() {
    // Definició directa dels colors de WhatsApp (Solució ràpida per evitar errors de R.color)
    // waPrimary: Verd fosc per la barra superior
    val waPrimary = Color(0xFF075E54)
    // waSecondary: Verd clar per les bombolles de l'usuari
    val waSecondary = Color(0xFFDCF8C6)

    // Un color de fons lleuger per simular el fons de xat
    val chatBackground = Color(0xFFF0F0F0)

    Scaffold(
        topBar = { ChatTopBar(waPrimary = waPrimary, contactName = "Joan Coll") },
        // Truquem a la barra d'entrada simplificada
        bottomBar = { StaticMessageInputBar(waSecondary = waSecondary) },
        containerColor = chatBackground // Fons de les bombolles
    ) { paddingValues ->

        // UTITZEM COLUMN ESTÀTIC AMB MODIFIER.VERTICALSCROLL
        // Això substitueix el LazyColumn. El scroll és manual.
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                // Afegeix un espai per simular que el contingut va al final
                .verticalScroll(rememberScrollState()),
            // Utilitzem un separador per forçar els missatges a la part inferior
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.height(10.dp)) // Espai inicial per estètica

            // 1. Missatge rebut (isUser = false)
            MessageBubble(
                text = "Hola! He vist el Layout1 i ha quedat perfecte.",
                isUser = false,
                time = "10:00",
                waSecondary = waSecondary
            )
            // 2. Missatge enviat (isUser = true)
            MessageBubble(
                text = "Molt bé, gràcies! M'alegro que t'agradi. Ara estem amb el Layout2.",
                isUser = true,
                time = "10:01",
                waSecondary = waSecondary
            )
            // 3. Missatge rebut
            MessageBubble(
                text = "Fantàstic! Aquesta pantalla de conversa és clau. Necessitem bombolles estètiques.",
                isUser = false,
                time = "10:03",
                waSecondary = waSecondary
            )
            // 4. Missatge enviat
            MessageBubble(
                text = "Sí, les farem amb el color secundari per als missatges enviats (els nostres).",
                isUser = true,
                time = "10:04",
                waSecondary = waSecondary
            )
            // 5. Missatge rebut
            MessageBubble(
                text = "Perfecte. Afegeix un botó de micròfon/enviament també. :)",
                isUser = false,
                time = "10:05",
                waSecondary = waSecondary
            )
            // 6. Missatge enviat
            MessageBubble(
                text = "Fet!",
                isUser = true,
                time = "10:06",
                waSecondary = waSecondary
            )
            // 7. Missatge rebut (extra)
            MessageBubble(
                text = "Aquesta és una versió estàtica amb el mínim codi.",
                isUser = false,
                time = "10:07",
                waSecondary = waSecondary
            )
            // 8. Missatge enviat (extra)
            MessageBubble(
                text = "Perfecte! Això és exactament el que volia.",
                isUser = true,
                time = "10:08",
                waSecondary = waSecondary
            )
        }
    }
}

// --- BARRA SUPERIOR DE LA CONVERSA ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(waPrimary: Color, contactName: String) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Placeholder de l'Avatar
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
                Spacer(Modifier.width(8.dp))
                Column {
                    Text(
                        contactName,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                    Text(
                        "en línia",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 12.sp
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = { /* Tornar enrere */ }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Tornar enrere",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = waPrimary,
            titleContentColor = Color.White
        ),
        actions = {
            // Icona de trucada
            IconButton(onClick = { /* Acció de trucada */ }) {
                Icon(
                    Icons.Filled.Call,
                    contentDescription = "Trucada",
                    tint = Color.White
                )
            }
            // Icona de menú (tres punts)
            IconButton(onClick = { /* Acció de Menú */ }) {
                Icon(
                    Icons.Filled.MoreVert,
                    contentDescription = "Menú",
                    tint = Color.White
                )
            }
        }
    )
}

// --- BOMBOLLA DE MISSATGE SIMPLIFICADA (sense Message data class) ---

@Composable
fun MessageBubble(
    text: String,
    isUser: Boolean,
    time: String,
    waSecondary: Color
) {
    // Nota: El color de la bombolla del contacte rebut (no-usuari) és blanc.
    val bubbleColor = if (isUser) waSecondary else Color(0xFFFFFFFF)
    val textColor = if (isUser) Color.Black else Color.Black // Hem canviat a Negre per a la bombolla clara

    // Alineació de la bombolla (dreta per l'usuari, esquerra per l'altre)
    val horizontalAlignment = if (isUser) Alignment.End else Alignment.Start

    // Forma de la bombolla
    val shape = if (isUser) {
        RoundedCornerShape(12.dp, 12.dp, 0.dp, 12.dp) // Puntera a baix a la dreta
    } else {
        RoundedCornerShape(12.dp, 12.dp, 12.dp, 0.dp) // Puntera a baix a l'esquerra
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        // Utilitzem la variable local horizontalAlignment directament
        horizontalAlignment = horizontalAlignment
    ) {
        Card(
            shape = shape,
            colors = CardDefaults.cardColors(containerColor = bubbleColor),
            modifier = Modifier.widthIn(max = 300.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Text(
                    text = text,
                    color = textColor,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(end = 40.dp) // Espai per a l'hora
                )

                // Hora del missatge
                Text(
                    text = time,
                    color = textColor.copy(alpha = 0.6f),
                    fontSize = 10.sp,
                    modifier = Modifier
                        .align(Alignment.End)
                        .offset(y = (-4).dp) // Ajustar visualment
                )
            }
        }
    }
}

// --- BARRA PER ESCRIURE MISSATGES (ESTÀTICA I SIMPLIFICADA) ---

@Composable
fun StaticMessageInputBar(waSecondary: Color) {

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            // Camp de text d'entrada (Sense estat, només disseny)
            OutlinedTextField(
                value = "", // Valor buit
                onValueChange = { /* Acció buida */ },
                placeholder = { Text("Escriu un missatge...") },
                modifier = Modifier.weight(1f),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFE8E8E8),
                    focusedContainerColor = Color(0xFFE8E8E8),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                ),
                shape = RoundedCornerShape(25.dp)
            )

            Spacer(Modifier.width(8.dp))

            // Botó d'Enviar (Sempre enviar, eliminant l'opció Mic)
            FloatingActionButton(
                onClick = { /* Acció simulada */ },
                containerColor = waSecondary,
                modifier = Modifier.size(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Send, // Hem canviat a Send estàtic
                    contentDescription = "Enviar",
                    tint = Color.White
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewLayout2() {
    AppTheme {
        // Crida directa al Layout2 principal
        Layout2()
    }
}