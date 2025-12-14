package cat.dam.andy.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
// Importació genèrica per resoldre la majoria d'icones bàsiques
import androidx.compose.material.icons.filled.* import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// Les importacions de R i AppTheme es mantenen comentades per evitar errors d'entorn
// import cat.dam.andy.app.R
// import cat.dam.andy.app.ui.theme.AppTheme

// --- PANTALLA PRINCIPAL DE TRUCADES (ESTÀTICA) ---

@Composable
fun Layout3() {
    // Definició directa dels colors principals (Verd de WhatsApp)
    val waPrimary = Color(0xFF075E54)

    // Utilitzem un Column per simular la llista estàtica
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Espai superior per simular la TopBar (opcional segons la integració)
        Spacer(modifier = Modifier.height(56.dp))

        // Capçalera d'informació (Crea Enllaç de Trucada)
        CallInfoHeader(waPrimary = waPrimary)

        Divider(color = Color(0xFFEEEEEE), thickness = 0.5.dp)

        // Títol de les trucades recents
        Text(
            text = "Recents",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp)
        )

        // --- LLISTA ESTÀTICA DE TRUCADES (SIMULACIÓ) ---

        // 1. Trucada perduda (Entrant, Veu)
        CallListItem(
            contactName = "Jordi Camps",
            time = "Avui, 14:30",
            isMissed = true,
            isIncoming = true,
            isVoice = true,
            waPrimary = waPrimary
        )

        // 2. Trucada entrant (Veu, contestada)
        CallListItem(
            contactName = "Anna Piera",
            time = "Avui, 11:00",
            isMissed = false,
            isIncoming = true,
            isVoice = true,
            waPrimary = waPrimary
        )

        // 3. Trucada sortint (Vídeo)
        CallListItem(
            contactName = "Projecte X",
            time = "Ahir, 20:15",
            isMissed = false,
            isIncoming = false,
            isVoice = false,
            waPrimary = waPrimary
        )

        // 4. Trucada entrant (Vídeo, contestada)
        CallListItem(
            contactName = "Pau Garcés",
            time = "05/09/2025, 18:00",
            isMissed = false,
            isIncoming = true,
            isVoice = false,
            waPrimary = waPrimary
        )
    }
}

// --- CAPÇALERA D'INFORMACIÓ (ENLLAÇ DE TRUCADA) ---

@Composable
fun CallInfoHeader(waPrimary: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Acció simulada */ }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icona de Creació d'Enllaç
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(waPrimary), // Fons de color primari
            contentAlignment = Alignment.Center
        ) {
            // Utilitzem Icons.Filled.Share en lloc d'Info/Link
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = "Crea Enllaç",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Crea un enllaç de trucada",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = "Comparteix un enllaç per a la teva trucada de WhatsApp",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

// --- ELEMENT DE LA LLISTA DE TRUCADES ---

@Composable
fun CallListItem(
    contactName: String,
    time: String,
    isMissed: Boolean,
    isIncoming: Boolean,
    isVoice: Boolean,
    waPrimary: Color
) {
    // Defineix el color de l'estat (vermell per perduda, primari per normal)
    val statusColor = if (isMissed) Color.Red else waPrimary
    // Defineix el color del nom (vermell per perduda, negre per normal)
    val nameColor = if (isMissed) Color.Red else Color.Black

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Acció simulada */ }
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar Placeholder (usant la primera lletra del contacte)
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0E0E0)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = contactName.first().toString(),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Text principal
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = contactName,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = nameColor
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Icona de direcció de trucada
            Row(verticalAlignment = Alignment.CenterVertically) {

                // Utilitzem KeyboardArrowDown (Entrant) i KeyboardArrowUp (Sortint)
                val directionIcon = if (isIncoming) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp

                Icon(
                    imageVector = directionIcon,
                    contentDescription = if (isIncoming) "Entrant" else "Sortint",
                    tint = statusColor, // Usa el color d'estat
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                // Hora
                Text(
                    text = time,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        // Icona de trucada a la dreta (Veu o Vídeo)
        IconButton(onClick = { /* Trucar */ }) {
            Icon(
                // Utilitzem Phone i Refresh (en lloc de FiberManualRecord)
                imageVector = if (isVoice) Icons.Filled.Phone else Icons.Filled.Refresh,
                contentDescription = if (isVoice) "Trucada de veu" else "Trucada de vídeo",
                tint = waPrimary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewLayout3() {
    // Utilitzem Surface en lloc d'AppTheme per a la previsualització
    Surface(color = MaterialTheme.colorScheme.background) {
        Layout3()
    }
}