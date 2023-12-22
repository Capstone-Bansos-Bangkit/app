package com.bangkit.genaidclean

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bangkit.genaidclean.navigation.AdminNavigation
import com.bangkit.genaidclean.navigation.UserNavigation
import com.bangkit.genaidclean.ui.ViewModelFactory
import com.bangkit.genaidclean.ui.theme.GenAidCleanTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        viewModel.getSession().observe(this){userModel ->
            if (userModel.token == ""){
                val intentAuth = Intent(this, AuthActivity::class.java)
                startActivity(intentAuth)
                finish()
            } else if (userModel.role == "admin"){
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                setContent {
                    GenAidCleanTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            UserNavigation(context = this)
                        }
                    }
                }
            }
        }

    }
}
