package cat.dam.andy.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.shadow

// --- PANTALLA PRINCIPAL D'ESTATS (UPDATES) ---

@Composable
fun Layout4() {
    // Definició dels colors (Verd de WhatsApp)
    val waPrimary = Color(0xFF075E54)
    val waLightGray = Color(0xFFEEEEEE)

    Scaffold(
        topBar = { StatusTopBar() }, // Barra superior completa
        floatingActionButton = {
            // Posicionem els FABs dins d'un Box per a la columna d'elements
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                FloatingActionButtonSection(waPrimary = waPrimary)
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues) // Aplicar padding de la Scaffold
            ) {
                // 1. EL MEU ESTAT
                item {
                    MyStatusItem(waPrimary = waPrimary)
                    Divider(color = waLightGray, thickness = 0.5.dp, modifier = Modifier.padding(top = 8.dp))
                }

                // 2. SECCIÓ D'ESTATS RECENTS (NO VISTS)
                item {
                    StatusSectionTitle(title = "Recents")
                }
                item {
                    StatusListItem(contactName = "Jordi Camps", time = "Fa 30 min", isSeen = false, count = 1, waPrimary = waPrimary)
                }
                item {
                    StatusListItem(contactName = "Anna Piera", time = "Fa 1 hora", isSeen = false, count = 3, waPrimary = waPrimary)
                }
                item {
                    Divider(color = waLightGray, thickness = 0.5.dp, modifier = Modifier.padding(top = 8.dp))
                }

                // 3. SECCIÓ D'ESTATS VISTS
                item {
                    StatusSectionTitle(title = "Vists")
                }
                item {
                    StatusListItem(contactName = "Projecte X", time = "Avui, 10:00", isSeen = true, count = 1, waPrimary = waPrimary)
                }
                item {
                    StatusListItem(contactName = "Pau Garcés", time = "Ahir, 23:00", isSeen = true, count = 2, waPrimary = waPrimary)
                }
                item {
                    StatusListItem(contactName = "Maria Soldevila", time = "04/09/2025, 12:00", isSeen = true, count = 1, waPrimary = waPrimary)
                }

                // 4. SECCIÓ DE CANALS (Nou element de detall)
                item {
                    Divider(color = waLightGray, thickness = 0.5.dp, modifier = Modifier.padding(top = 16.dp))
                    ChannelsSection()
                }
            }
        }
    )
}

// --- BARRA SUPERIOR PERSONALITZADA ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusTopBar() {
    val waPrimary = Color(0xFF075E54)
    TopAppBar(
        title = {
            Text(
                text = "Actualitzacions",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = waPrimary
        ),
        actions = {
            IconButton(onClick = { /* Cercar */ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Cercar",
                    tint = Color.White
                )
            }
            IconButton(onClick = { /* Menú */ }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Menú",
                    tint = Color.White
                )
            }
        }
    )
}

// --- SECCIÓ EL MEU ESTAT ---

@Composable
fun MyStatusItem(waPrimary: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Obre el meu estat */ }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar amb icona de càmera (per afegir un estat)
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape),
            contentAlignment = Alignment.BottomEnd // Col·loca la icona de càmera a baix a la dreta
        ) {
            // Placeholder d'Avatar
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0E0E0)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "M", // 'M' per 'Meu'
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Icona de càmera per afegir un nou estat
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(waPrimary)
                    .border(2.dp, Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Afegeix estat",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "El meu estat",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = "Toca per afegir una actualització d'estat",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

// --- TÍTOL DE SECCIÓ (Recents/Vists) ---

@Composable
fun StatusSectionTitle(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = Color.Gray,
        modifier = Modifier.padding(start = 16.dp, top = 12.dp, bottom = 8.dp)
    )
}

// --- ELEMENT DE LA LLISTA D'ESTATS ---

@Composable
fun StatusListItem(
    contactName: String,
    time: String,
    isSeen: Boolean,
    count: Int, // Nombre d'actualitzacions
    waPrimary: Color
) {
    // Color de la vora (Verd per no vist, Gris per vist)
    val borderColor = if (isSeen) Color.LightGray else waPrimary
    val borderThickness = if (isSeen) 1.dp else 2.dp

    // Per a estats no vists, creem un gruix de vora per cada actualització
    val borderModifier = Modifier
        .padding(4.dp)
        .size(56.dp)
        .border(borderThickness, borderColor, CircleShape)
        .padding(if (isSeen) 0.dp else 2.dp) // Espai entre vores
        .border(1.dp, Color.White, CircleShape) // Vora interior blanca

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Veure l'estat */ }
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar Placeholder amb Vora d'Estat
        Box(
            modifier = borderModifier.clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
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
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Text principal
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = contactName,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = if (isSeen) "$time" else "$count actualització${if (count > 1) "ns" else ""} · $time",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

// --- SECCIÓ DE CANALS (NOU) ---

@Composable
fun ChannelsSection() {
    val waPrimary = Color(0xFF075E54)

    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Canals",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { /* Navegar a la llista de canals */ }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Cercar Canals",
                    tint = waPrimary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    // S'ha canviat ArrowForwardIos per ArrowForward per evitar errors de compatibilitat d'Icones.
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Més Canals",
                    tint = waPrimary,
                    modifier = Modifier.size(14.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Fila de desplaçament horitzontal (simulada per mostrar l'estructura)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ChannelItem(name = "Notícies CAT")
            ChannelItem(name = "Esports BCN")
            ChannelItem(name = "Tecnologia")
            ChannelItem(name = "Cuina Fàcil")
        }
    }
}

@Composable
fun ChannelItem(name: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { /* Obrir Canal */ }
    ) {
        // Placeholder per a la imatge del canal
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color(0xFF075E54).copy(alpha = 0.8f)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = name.first().toString(), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = name,
            fontSize = 12.sp,
            color = Color.Black,
            maxLines = 1,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.widthIn(max = 70.dp)
        )
    }
}


// --- BOTÓ FLOTANT (FAB) I EL SEU COMPLEMENT ---

@Composable
fun FloatingActionButtonSection(waPrimary: Color) {
    Column(horizontalAlignment = Alignment.End) {
        // FAB Complementari (Llapis per text)
        SmallFloatingActionButton(
            icon = Icons.Filled.Edit, // Llapis
            contentDescription = "Estat de Text",
            onClick = { /* Obre l'editor de text */ },
            backgroundColor = Color(0xFFF0F0F0),
            iconColor = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // FAB Principal (Configuració/Substitut de Photo)
        FloatingActionButton(
            onClick = { /* Obre la càmera */ },
            containerColor = waPrimary,
            contentColor = Color.White,
            modifier = Modifier.shadow(8.dp, CircleShape)
        ) {
            Icon(
                // Utilitzem Settings com a substitut bàsic de Photo/Camera
                imageVector = Icons.Filled.Settings,
                contentDescription = "Nou Estat (Càmera)",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun SmallFloatingActionButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    backgroundColor: Color,
    iconColor: Color
) {
    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = backgroundColor,
        modifier = Modifier.size(40.dp),
        shadowElevation = 4.dp
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = iconColor,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewLayout4() {
    // Utilitzem Surface en lloc d'AppTheme per a la previsualització
    Surface(color = MaterialTheme.colorScheme.background) {
        Layout4()
    }
}