package com.csci448.lsherburne.lsherburne_a3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.csci448.konchar.jkonchar_a3.ui.navigation.DiscoTopBar
import com.csci448.lsherburne.lsherburne_a3.ui.navigation.DiscoBottomBar
import com.csci448.lsherburne.lsherburne_a3.ui.navigation.DiscoNavHost
import com.csci448.lsherburne.lsherburne_a3.ui.theme.Lsherburne_A3Theme
import com.csci448.lsherburne.lsherburne_a3.viewModel.DiscoFactory
import com.csci448.lsherburne.lsherburne_a3.viewModel.DiscoViewModel
import com.csci448.lsherburne.lsherburne_a3.viewModel.I_DiscoViewModel


class MainActivity : ComponentActivity() {
    private lateinit var viewModel: DiscoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = DiscoFactory(this)
        viewModel = ViewModelProvider(this, factory).get(factory.getViewModelClass())

        setContent {
            MainActivityContent(model = viewModel)
        }
    }
}



@Composable
private fun MainActivityContent(model: I_DiscoViewModel){
    Lsherburne_A3Theme() {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            val navController = rememberNavController()
            Scaffold(
                topBar = { DiscoTopBar(navController = navController) },
                content = {DiscoNavHost(navController = navController, viewModel = model )},
                bottomBar = { DiscoBottomBar(navController = navController)},
            )
        }
    }
}

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            Lsherburne_A3Theme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
//    }
//}

//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    Lsherburne_A3Theme {
//        Greeting("Android")
//    }
//}