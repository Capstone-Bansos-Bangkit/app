package com.bangkit.genaidclean

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.bangkit.genaidclean.ui.theme.GenAidCleanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GenAidCleanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    // pengecekan apakah udah login sebagai user atau belom || kalo belom, nanti intent ke login page
                    // kalo udah login, tergantung role, nanti intent ke admin page atau user page

//                    UserNavigation()
//                    AdminNavigation()
//                    LoginNavigation()
                }
            }
        }
    }
}
