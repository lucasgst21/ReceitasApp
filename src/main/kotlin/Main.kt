import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

sealed class ScreenItem(
    val title: String,
    val icon: ImageVector,
    val iconSelected: ImageVector,
    val label: String
) {
    data object Receitas : ScreenItem(
        title = "Receitas",
        icon = Icons.Outlined.Home,
        iconSelected = Icons.Filled.Home,
        label = "Receitas"
    )
    data object Categorias : ScreenItem(
        title = "Categorias",
        icon = Icons.Outlined.List,
        iconSelected = Icons.Filled.List,
        label = "Categorias"
    )
    data object Favoritos : ScreenItem(
        title = "Favoritos",
        icon = Icons.Outlined.FavoriteBorder,
        iconSelected = Icons.Filled.Favorite,
        label = "Favoritos"
    )
    data object Perfil : ScreenItem(
        title = "Perfil",
        icon = Icons.Outlined.Person,
        iconSelected = Icons.Filled.Person,
        label = "Perfil"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val screens = remember {
        listOf(
            ScreenItem.Receitas,
            ScreenItem.Categorias,
            ScreenItem.Favoritos,
            ScreenItem.Perfil
        )
    }
    var currentScreen by remember { mutableStateOf<ScreenItem>(ScreenItem.Receitas) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(currentScreen.title) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar {
                screens.forEach { screen ->
                    NavigationBarItem(
                        selected = screen == currentScreen,
                        onClick = { currentScreen = screen },
                        icon = {
                            Icon(
                                if (screen == currentScreen) screen.iconSelected else screen.icon,
                                contentDescription = screen.label
                            )
                        },
                        label = { Text(screen.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            when (currentScreen) {
                ScreenItem.Receitas -> ReceitasScreen()
                ScreenItem.Categorias -> CategoriasScreen()
                ScreenItem.Favoritos -> FavoritosScreen()
                ScreenItem.Perfil -> PerfilScreen()
            }
        }
    }
}

@Composable
fun ReceitasScreen() {
    Box(modifier = Modifier.fillMaxSize())
}

@Composable
fun CategoriasScreen() {
    Box(modifier = Modifier.fillMaxSize())
}

@Composable
fun FavoritosScreen() {
    Box(modifier = Modifier.fillMaxSize())
}

@Composable
fun PerfilScreen() {
    Box(modifier = Modifier.fillMaxSize())
}

val AppColorScheme = lightColorScheme(
    primary = Color(0xFF6650A4),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE8DEF8),
    onPrimaryContainer = Color(0xFF22005D),
    secondary = Color(0xFF625B71),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE8DEF8),
    onSecondaryContainer = Color(0xFF1E192B),
    tertiary = Color(0xFF7D5260),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFD9E3),
    onTertiaryContainer = Color(0xFF31101D),
    error = Color(0xFFBA1A1A),
    surface = Color(0xFFFFFBFE),
    onSurface = Color(0xFF1C1B1F),
    onSurfaceVariant = Color(0xFF49454F),
    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),
    outline = Color(0xFF79747E),
)

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ReceitaApp",
        state = rememberWindowState(width = 400.dp, height = 780.dp)
    ) {
        MaterialTheme(colorScheme = AppColorScheme) {
            App()
        }
    }
}