package com.example.lenguajeseas

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lenguajeseas.data.Datasource
import com.example.lenguajeseas.model.Comida
import com.example.lenguajeseas.model.Mes
import com.example.lenguajeseas.model.Semana
import com.example.lenguajeseas.ui.theme.LenguajeSeasTheme
import com.example.lenguajeseas.viewmodel.FavoritesViewModel
// MainActivity es la actividad principal de la aplicación
class MainActivity : ComponentActivity() {

    // ViewModel para la gestión de favoritos
    private val favoritesViewModel: FavoritesViewModel by viewModels()

    // Método onCreate de la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            LenguajeSeasTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavigation(favoritesViewModel)
                }
            }
        }
    }
}

// AppNavigation contiene la estructura de navegación de la aplicación
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(favoritesViewModel: FavoritesViewModel) {
    // Controlador de navegación
    val navController = rememberNavController()

    // Scaffold con AppBar y BottomNavigationBar
    Scaffold(
        topBar = {
            // AppBar personalizada
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            if (currentDestination?.route != "Home") {
                // AppBar con botón de navegación hacia atrás
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.lenguaje_de_se_as),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {

                        // En lugar de usar popBackStack, podemos usar navigateUp para retroceder en la navegación
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                            }
                        ) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            } else {
                // AppBar estándar
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.lenguaje_de_se_as),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        },
        bottomBar = {
            // BottomNavigationBar
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        // Contenido del NavHost
        NavHost(navController = navController, startDestination = "Home", modifier = Modifier.padding(paddingValues)) {
            // Destinos de las pantallas
            composable("Home") { SelasApp(navController = navController) }
            composable("Abc") { AbcScreen() }
            composable("Categories") { CategoriesScreen(navController = navController) }
            composable("Comida") { ComidaScreen(favoritesViewModel) }
            composable("Semana") { SemanaScreen(favoritesViewModel) }
            composable("Mes") { MesScreen(favoritesViewModel) }
            composable("Favorites") { FavoritesScreen(favoritesViewModel) }
        }
    }
}

// SelasApp es la pantalla principal de la aplicación
@Composable
fun SelasApp(navController: NavHostController) {
    // Contenido de la pantalla principal
    Scaffold(
        content = { paddingValues ->
            Surface(modifier = Modifier.fillMaxSize(), color = Color.Unspecified) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Mensaje de bienvenida
                    Text(
                        text = stringResource(R.string.Bienvenida),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(45.dp),
                        textAlign = TextAlign.Center
                    )

                    // Botones de navegación
                    ButtonWithIconAndText(
                        iconResId = R.drawable.bloques,
                        buttonText = stringResource(id = R.string.abecedario_de_se_as),
                        onClick = { navController.navigate("Abc") }
                    )
                    Spacer(modifier = Modifier.height(60.dp))
                    ButtonWithIconAndText(
                        iconResId = R.drawable.lenguaje_de_senas,
                        buttonText = stringResource(id = R.string.aprende_lenguaje_de_se_as),
                        onClick = { navController.navigate("Categories") } // Navega a la pantalla de categorías
                    )
                }
            }
        }
    )
}


// Función que muestra un botón con icono y texto
@Composable
fun ButtonWithIconAndText(iconResId: Int, buttonText: String, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier
                .width(200.dp)
                .height(120.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = buttonText)
    }
}

// Pantalla para visualizar el abecedario de señas
@Composable
fun AbcScreen() {
    // Obtiene los nombres de las imágenes del abecedario
    val imaNames = stringArrayResource(id = R.array.ima_names)
    val context = LocalContext.current
    val icons = imaNames.map { iconName ->
        val resourceId = context.resources.getIdentifier(iconName, "drawable", context.packageName)
        resourceId
    }

    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Texto de bienvenida
        Text(
            text = "Bienvenido al carrusel del abecedario de señas. Visualiza las imágenes y practícalas.",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        if (icons.isNotEmpty()) {
            // Carrusel de imágenes del abecedario
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = icons[currentIndex]),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(335.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botones para navegar por las imágenes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { currentIndex = (currentIndex - 1 + icons.size) % icons.size }) {
                    Text(text = "Retroceder")
                }

                Button(onClick = { currentIndex = (currentIndex + 1) % icons.size }) {
                    Text(text = "Siguiente")
                }
            }
        } else {
            Text(text = "No hay imágenes disponibles.")
        }
    }
}

// Pantalla para seleccionar categorías
@Composable
fun CategoriesScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CategoryButton(text = "Comida", onClick = { navController.navigate("Comida") })
        CategoryButton(text = "Semana", onClick = { navController.navigate("Semana") })
        CategoryButton(text = "Meses del año", onClick = { navController.navigate("mes") })
    }
}

// Botón de categoría
@Composable
fun CategoryButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = text)
    }
}

// Pantalla para mostrar la lista de comida
@Composable
fun ComidaScreen(favoritesViewModel: FavoritesViewModel) {
    val comidasList = Datasource().cargaComida()
    ComidasList(comidasList, favoritesViewModel)
}

// Lista de comida
@Composable
fun ComidasList(comidasList: List<Comida>, favoritesViewModel: FavoritesViewModel, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(comidasList) { comida ->
            ComidaCard(comida = comida, favoritesViewModel = favoritesViewModel, modifier = Modifier.padding(20.dp))
        }
    }
}

// Tarjeta de comida
@Composable
fun ComidaCard(comida: Comida, favoritesViewModel: FavoritesViewModel, modifier: Modifier = Modifier) {
    var isFavorite by remember { mutableStateOf(favoritesViewModel.isFavorite(comida.imageResourceId)) }

    Card(modifier = modifier.clickable {}) {
        Column {
            Image(
                painter = painterResource(comida.imageResourceId),
                contentDescription = stringResource(comida.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(335.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(comida.stringResourceId),
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            IconButton(
                onClick = {
                    isFavorite = !isFavorite
                    if (isFavorite) {
                        favoritesViewModel.addFavorite(comida.imageResourceId)
                    } else {
                        favoritesViewModel.removeFavorite(comida.imageResourceId)
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                //boton para agregar a favoritos
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Favoritos",
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }
        }
    }
}

// Pantalla para mostrar la lista de días de la semana
@Composable
fun SemanaScreen(favoritesViewModel: FavoritesViewModel) {
    val semanaList = Datasource().cargaSemana()
    SemanaList(semanaList, favoritesViewModel)
}

// Lista de días de la semana
@Composable
fun SemanaList(semanaList: List<Semana>, favoritesViewModel: FavoritesViewModel, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(semanaList) { semana ->
            SemanaCard(semana = semana, favoritesViewModel = favoritesViewModel, modifier = Modifier.padding(20.dp))
        }
    }
}

// Tarjeta de día de la semana
@Composable
fun SemanaCard(semana: Semana, favoritesViewModel: FavoritesViewModel, modifier: Modifier = Modifier) {
    var isFavorite by remember { mutableStateOf(favoritesViewModel.isFavorite(semana.imageResourceId)) }

    Card(modifier = modifier.clickable {}) {
        Column {
            Image(
                painter = painterResource(semana.imageResourceId),
                contentDescription = stringResource(semana.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(335.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(semana.stringResourceId),
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            IconButton(
                onClick = {
                    isFavorite = !isFavorite
                    if (isFavorite) {
                        favoritesViewModel.addFavorite(semana.imageResourceId)
                    } else {
                        favoritesViewModel.removeFavorite(semana.imageResourceId)
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                //boton para agregar a favoritos
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Favoritos",
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }
        }
    }
}

//Pantalla de Mes
@Composable
fun MesScreen(favoritesViewModel: FavoritesViewModel) {
    val mesList = Datasource().cargaMeses()
    MesList(mesList, favoritesViewModel)
}

//Muestra la lista de los meses
@Composable
fun MesList(mesList: List<Mes>, favoritesViewModel: FavoritesViewModel, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(mesList) { mes ->
            MesCard(mes = mes, favoritesViewModel = favoritesViewModel, modifier = Modifier.padding(20.dp))
        }
    }
}

//Tarjetas donde se muestran las listas
@Composable
fun MesCard(mes: Mes, favoritesViewModel: FavoritesViewModel, modifier: Modifier = Modifier) {
    var isFavorite by remember { mutableStateOf(favoritesViewModel.isFavorite(mes.imageResourceId)) }

    Card(modifier = modifier.clickable {}) {
        Column {
            Image(
                painter = painterResource(mes.imageResourceId),
                contentDescription = stringResource(mes.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(460.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(mes.stringResourceId),
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            IconButton(
                onClick = {
                    isFavorite = !isFavorite
                    if (isFavorite) {
                        favoritesViewModel.addFavorite(mes.imageResourceId)
                    } else {
                        favoritesViewModel.removeFavorite(mes.imageResourceId)
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                //boton para agregar a favoritos
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Favoritos",
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }
        }
    }
}


//Pantalla de favoritos
@Composable
fun FavoritesScreen(favoritesViewModel: FavoritesViewModel) {
    val favorites by favoritesViewModel.favorites.collectAsState()

    LazyColumn {
        items(favorites) { resourceId ->
            FavoriteCard(resourceId = resourceId, favoritesViewModel = favoritesViewModel)
        }
    }
}

//Muestra las tarjetas de favoritos agregados a favoritos
@Composable
fun FavoriteCard(resourceId: Int, favoritesViewModel: FavoritesViewModel, modifier: Modifier = Modifier) {
    var isFavorite by remember { mutableStateOf(favoritesViewModel.isFavorite(resourceId)) }

    Card(modifier = modifier.clickable {}) {
        Column {
            Image(
                painter = painterResource(resourceId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(460.dp),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = {
                    isFavorite = !isFavorite
                    if (isFavorite) {
                        favoritesViewModel.addFavorite(resourceId)
                    } else {
                        favoritesViewModel.removeFavorite(resourceId)
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                //boton de favritos, para eliminar y estar agregado
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Favoritos",
                    tint = if (isFavorite) Color.Red else Color.Red
                )
            }
        }
    }
}

//Barra de navegacion
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home, "Home"),
        BottomNavItem("Favorites", Icons.Default.Favorite, "Favoritos"),
        BottomNavItem("Search", Icons.Default.Search, "Search")
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentDestination?.route == item.route,
                onClick = { navController.navigate(item.route) }

            )
        }
    }
}

//Para la funcionalidad de la barra de navegacion
data class BottomNavItem(val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val title: String)

@Preview(showBackground = true)
@Composable
fun LenguajePreview() {
    val favoritesViewModel: FavoritesViewModel = viewModel() // Usa viewModel() para obtener una instancia
    LenguajeSeasTheme {
        AppNavigation(favoritesViewModel)
    }
}