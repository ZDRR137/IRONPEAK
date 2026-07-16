package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import kotlin.math.pow
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                GymApp()
            }
        }
    }
}

enum class Screen{
    HOME,
    WORKOUT,
    CONTENT,
    GOALS,
    QUESTIONNAIRE,
    ASSESSMENT,
    RESERVATIONS,
    QRCODE
}

data class MenuItem(

    val title:String,

    val icon:ImageVector,

    val screen:Screen

)

@Composable
fun GymApp(){

    var currentScreen by remember {

        mutableStateOf(Screen.HOME)

    }

    when(currentScreen){

        Screen.HOME->HomeScreen{

            currentScreen=it

        }

        Screen.WORKOUT->WorkoutScreen{

            currentScreen=Screen.HOME

        }

        Screen.CONTENT->ContentScreen{

            currentScreen=Screen.HOME

        }

        Screen.GOALS->GoalsScreen{

            currentScreen=Screen.HOME

        }

        Screen.QUESTIONNAIRE->QuestionnaireScreen{

            currentScreen=Screen.HOME

        }

        Screen.ASSESSMENT->AssessmentScreen{

            currentScreen=Screen.HOME

        }

        Screen.RESERVATIONS->ReservationsScreen{

            currentScreen=Screen.HOME

        }
        Screen.QRCODE -> QRCodeScreen {
            currentScreen = Screen.HOME
        }

    }

}

@Composable
fun HomeScreen(
    open: (Screen) -> Unit
) {

    val menus = listOf(

        MenuItem(
            "Conteúdos",
            Icons.Default.MenuBook,
            Screen.CONTENT
        ),

        MenuItem(
            "Plano de Treino",
            Icons.Default.FitnessCenter,
            Screen.WORKOUT
        ),

        MenuItem(
            "Questionários",
            Icons.Default.Description,
            Screen.QUESTIONNAIRE
        ),

        MenuItem(
            "Metas",
            Icons.Default.Flag,
            Screen.GOALS
        ),

        MenuItem(
            "Avaliação Física",
            Icons.Default.Person,
            Screen.ASSESSMENT
        ),

        MenuItem(
            "Reservas",
            Icons.Default.CalendarMonth,
            Screen.RESERVATIONS
        )

    )

    Scaffold(

        containerColor = Color.Black,

        bottomBar = {
            BottomMenu()
        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.Black)
                .padding(16.dp)

        ) {

            UserCard(open)

            Spacer(modifier = Modifier.height(20.dp))

            LazyVerticalGrid(

                columns = GridCells.Fixed(2),

                horizontalArrangement = Arrangement.spacedBy(12.dp),

                verticalArrangement = Arrangement.spacedBy(12.dp)

            ) {

                items(menus) { menu ->

                    Card(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .clickable {

                                open(menu.screen)

                            },

                        colors = CardDefaults.cardColors(

                            containerColor = Color.Red

                        ),

                        shape = RoundedCornerShape(20.dp)

                    ) {

                        Column(

                            modifier = Modifier.fillMaxSize(),

                            horizontalAlignment = Alignment.CenterHorizontally,

                            verticalArrangement = Arrangement.Center

                        ) {

                            Icon(

                                imageVector = menu.icon,

                                contentDescription = null,

                                tint = Color.White,

                                modifier = Modifier.size(55.dp)

                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(

                                text = menu.title,

                                color = Color.White,

                                fontWeight = FontWeight.Bold,

                                fontSize = 18.sp

                            )

                        }

                    }

                }

            }

        }

    }

}

@Composable
fun UserCard(
    open: (Screen) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(25.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.size(70.dp)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Utilizador",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Text("Sócio nº10932")
            }

            Icon(
                imageVector = Icons.Default.QrCode,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.clickable {
                    open(Screen.QRCODE)
                }
            )

            Spacer(modifier = Modifier.width(10.dp))

            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}

@Composable
fun BottomMenu(){

    NavigationBar(

        containerColor=Color.Red

    ){

        NavigationBarItem(

            selected=true,

            onClick={},

            icon={

                Icon(Icons.Default.Home,null)

            }

        )

        NavigationBarItem(

            selected=false,

            onClick={},

            icon={

                Icon(Icons.Default.Notifications,null)

            }

        )

        NavigationBarItem(

            selected=false,

            onClick={},

            icon={

                Icon(Icons.Default.Person,null)

            }

        )

    }

}
//==============================
// MODELO EXERCÍCIO
//==============================

data class Exercise(
    val name: String
)

//==============================
// PLANO DE TREINO
//==============================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(
    back: () -> Unit
) {

    var exercise by remember {
        mutableStateOf("")
    }

    val exercises = remember {

        mutableStateListOf(

            Exercise("Supino"),
            Exercise("Agachamento"),
            Exercise("Remada")

        )

    }

    Scaffold(

        containerColor = Color.Black,

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        "Plano de Treino",
                        color = Color.White
                    )

                },

                navigationIcon = {

                    IconButton(
                        onClick = back
                    ) {

                        Icon(
                            Icons.Default.ArrowBack,
                            null,
                            tint = Color.White
                        )

                    }

                },

                colors = TopAppBarDefaults.topAppBarColors(

                    containerColor = Color.Red

                )

            )

        }

    ) { padding ->

        Column(

            modifier = Modifier

                .fillMaxSize()

                .padding(padding)

                .padding(16.dp)

        ) {

            OutlinedTextField(

                value = exercise,

                onValueChange = {

                    exercise = it

                },

                modifier = Modifier.fillMaxWidth(),

                label = {

                    Text("Novo Exercício")

                }

            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(

                modifier = Modifier.fillMaxWidth(),

                colors = ButtonDefaults.buttonColors(

                    containerColor = Color.Red

                ),

                onClick = {

                    if (exercise.isNotBlank()) {

                        exercises.add(

                            Exercise(exercise)

                        )

                        exercise = ""

                    }

                }

            ) {

                Icon(Icons.Default.Add, null)

                Spacer(modifier = Modifier.width(8.dp))

                Text("Adicionar")

            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn {

                items(exercises.size) { index ->

                    val item = exercises[index]

                    Card(

                        modifier = Modifier

                            .fillMaxWidth()

                            .padding(vertical = 5.dp),

                        colors = CardDefaults.cardColors(

                            containerColor = Color(0xFF202020)

                        )

                    ) {

                        Row(

                            modifier = Modifier

                                .fillMaxWidth()

                                .padding(16.dp),

                            verticalAlignment = Alignment.CenterVertically,

                            horizontalArrangement = Arrangement.SpaceBetween

                        ) {

                            Row(

                                verticalAlignment = Alignment.CenterVertically

                            ) {

                                Icon(

                                    Icons.Default.FitnessCenter,

                                    null,

                                    tint = Color.Red

                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Text(

                                    item.name,

                                    color = Color.White,

                                    fontSize = 18.sp

                                )

                            }

                            IconButton(

                                onClick = {

                                    exercises.removeAt(index)

                                }

                            ) {

                                Icon(

                                    Icons.Default.Delete,

                                    null,

                                    tint = Color.Red

                                )

                            }

                        }

                    }

                }

            }

        }

    }

}

//==============================
// CONTEÚDOS
//==============================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentScreen(

    back: () -> Unit

) {

    val contents = listOf(

        "Treino de Peito",

        "Treino de Costas",

        "Treino de Pernas",

        "Treino de Ombros",

        "Treino de Bíceps",

        "Treino de Tríceps",

        "Alongamentos",

        "Cardio",

        "Nutrição"

    )

    Scaffold(

        containerColor = Color.Black,

        topBar = {

            TopAppBar(

                title = {

                    Text(

                        "Conteúdos",

                        color = Color.White

                    )

                },

                navigationIcon = {

                    IconButton(

                        onClick = back

                    ) {

                        Icon(

                            Icons.Default.ArrowBack,

                            null,

                            tint = Color.White

                        )

                    }

                },

                colors = TopAppBarDefaults.topAppBarColors(

                    containerColor = Color.Red

                )

            )

        }

    ) { padding ->

        LazyColumn(

            modifier = Modifier

                .fillMaxSize()

                .padding(padding)

                .padding(16.dp)

        ) {

            items(contents.size) { index ->

                Card(

                    modifier = Modifier

                        .fillMaxWidth()

                        .padding(vertical = 6.dp),

                    colors = CardDefaults.cardColors(

                        containerColor = Color(0xFF202020)

                    )

                ) {

                    Row(

                        modifier = Modifier

                            .fillMaxWidth()

                            .padding(18.dp),

                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Icon(

                            Icons.Default.PlayCircle,

                            null,

                            tint = Color.Red

                        )

                        Spacer(modifier = Modifier.width(15.dp))

                        Text(

                            contents[index],

                            color = Color.White,

                            fontSize = 18.sp

                        )

                    }

                }

            }

        }

    }

}
//==============================
// METAS
//==============================

data class Goal(
    val title: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalsScreen(
    back: () -> Unit
) {

    var text by remember { mutableStateOf("") }

    val goals = remember {

        mutableStateListOf(

            Goal("Treinar 4 vezes por semana"),
            Goal("Perder 2 kg")

        )

    }

    Scaffold(

        containerColor = Color.Black,

        topBar = {

            TopAppBar(

                title = {

                    Text("Metas", color = Color.White)

                },

                navigationIcon = {

                    IconButton(onClick = back) {

                        Icon(Icons.Default.ArrowBack, null, tint = Color.White)

                    }

                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red
                )

            )

        }

    ) { padding ->

        Column(

            modifier = Modifier

                .fillMaxSize()

                .padding(padding)

                .padding(16.dp)

        ) {

            OutlinedTextField(

                value = text,

                onValueChange = {

                    text = it

                },

                modifier = Modifier.fillMaxWidth(),

                label = {

                    Text("Nova Meta")

                }

            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(

                modifier = Modifier.fillMaxWidth(),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ),

                onClick = {

                    if (text.isNotBlank()) {

                        goals.add(Goal(text))

                        text = ""

                    }

                }

            ) {

                Text("Adicionar")

            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn {

                items(goals.size) { index ->

                    Card(

                        modifier = Modifier

                            .fillMaxWidth()

                            .padding(vertical = 5.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF202020)
                        )

                    ) {

                        Row(

                            modifier = Modifier

                                .fillMaxWidth()

                                .padding(15.dp),

                            horizontalArrangement = Arrangement.SpaceBetween,

                            verticalAlignment = Alignment.CenterVertically

                        ) {

                            Text(

                                goals[index].title,

                                color = Color.White

                            )

                            IconButton(

                                onClick = {

                                    goals.removeAt(index)

                                }

                            ) {

                                Icon(

                                    Icons.Default.Delete,

                                    null,

                                    tint = Color.Red

                                )

                            }

                        }

                    }

                }

            }

        }

    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionnaireScreen(
    back: () -> Unit
) {

    var cardio by remember { mutableStateOf(false) }
    var weights by remember { mutableStateOf(false) }

    Scaffold(

        containerColor = Color.Black,

        topBar = {

            TopAppBar(

                title = {

                    Text("Questionário", color = Color.White)

                },

                navigationIcon = {

                    IconButton(onClick = back) {

                        Icon(Icons.Default.ArrowBack, null, tint = Color.White)

                    }

                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red
                )

            )

        }

    ) { padding ->

        Column(

            Modifier

                .fillMaxSize()

                .padding(padding)

                .padding(16.dp)

        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Checkbox(
                    checked = cardio,
                    onCheckedChange = {
                        cardio = it
                    }
                )

                Text("Pratica Cardio", color = Color.White)

            }

            Row(verticalAlignment = Alignment.CenterVertically) {

                Checkbox(
                    checked = weights,
                    onCheckedChange = {
                        weights = it
                    }
                )

                Text("Pratica Musculação", color = Color.White)

            }

        }

    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssessmentScreen(
    back: () -> Unit
) {

    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }

    val imc = run {

        val p = peso.toDoubleOrNull()

        val a = altura.toDoubleOrNull()

        if (p != null && a != null) {

            p / (a / 100).pow(2)

        } else null

    }

    Scaffold(

        containerColor = Color.Black,

        topBar = {

            TopAppBar(

                title = {

                    Text("Avaliação Física", color = Color.White)

                },

                navigationIcon = {

                    IconButton(onClick = back) {

                        Icon(Icons.Default.ArrowBack, null, tint = Color.White)

                    }

                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red
                )

            )

        }

    ) { padding ->

        Column(

            Modifier

                .fillMaxSize()

                .padding(padding)

                .padding(16.dp)

        ) {

            OutlinedTextField(

                value = peso,

                onValueChange = {

                    peso = it

                },

                label = {

                    Text("Peso")

                }

            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(

                value = altura,

                onValueChange = {

                    altura = it

                },

                label = {

                    Text("Altura")

                }

            )

            Spacer(modifier = Modifier.height(20.dp))

            if (imc != null) {

                Text(

                    "IMC: %.2f".format(imc),

                    color = Color.White,

                    fontSize = 24.sp

                )

            }

        }

    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationsScreen(
    back: () -> Unit
) {

    val aulas = listOf(

        "CrossFit",

        "Pilates",

        "Spinning",

        "Yoga"

    )

    Scaffold(

        containerColor = Color.Black,

        topBar = {

            TopAppBar(

                title = {

                    Text("Reservas", color = Color.White)

                },

                navigationIcon = {

                    IconButton(onClick = back) {

                        Icon(Icons.Default.ArrowBack, null, tint = Color.White)

                    }

                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red
                )

            )

        }

    ) { padding ->

        LazyColumn(

            modifier = Modifier

                .fillMaxSize()

                .padding(padding)

                .padding(16.dp)

        ) {

            items(aulas.size) { index ->

                Card(

                    modifier = Modifier

                        .fillMaxWidth()

                        .padding(vertical = 5.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF202020)
                    )

                ) {

                    Row(

                        modifier = Modifier

                            .fillMaxWidth()

                            .padding(16.dp),

                        horizontalArrangement = Arrangement.SpaceBetween,

                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Text(

                            aulas[index],

                            color = Color.White

                        )

                        Button(

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red
                            ),

                            onClick = {}

                        ) {

                            Text("Reservar")

                        }

                    }

                }

            }

        }

    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QRCodeScreen(
    back: () -> Unit
) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text("Código QR", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = back) {
                        Icon(
                            Icons.Default.ArrowBack,
                            null,
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            /*
            Image(
                painter = painterResource(id = R.drawable.qrcode),
                contentDescription = "QR Code",
                modifier = Modifier.size(250.dp)
            )
*/
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Apresente este QR na entrada do ginásio",
                color = Color.White
            )
        }
    }
}