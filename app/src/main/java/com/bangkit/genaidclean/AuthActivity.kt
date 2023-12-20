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
import com.bangkit.genaidclean.navigation.AuthNavigation
import com.bangkit.genaidclean.ui.ViewModelFactory
import com.bangkit.genaidclean.ui.theme.GenAidCleanTheme

class AuthActivity : ComponentActivity() {
    val context = this

    private val viewModel by viewModels<AuthViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getSession().observe(this){userModel ->
            if (userModel.token != "" && userModel.role == "user"){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else if (userModel.token != "" && userModel.role == "admin"){
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent)
                finish()

            }
        }

        setContent {
            GenAidCleanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AuthNavigation(context = this)
                }
            }
        }
    }
}
