package com.bangkit.genaidclean

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bangkit.genaidclean.navigation.AdminNavigation
import com.bangkit.genaidclean.ui.ViewModelFactory
import com.bangkit.genaidclean.ui.theme.GenAidCleanTheme

class AdminActivity : ComponentActivity() {

    private val viewModel by viewModels<AdminActivityViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        //check session
        viewModel.getSession().observe(this){
            if (it.token == ""){
                val intentAuth = Intent(this, AuthActivity::class.java)
                startActivity(intentAuth)
                finish()
            }
        }

        super.onCreate(savedInstanceState)
        setContent {
            GenAidCleanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AdminNavigation(
                        actionLogOut = ::logout
                    )
                }
            }
        }
    }

    private fun logout(){
        viewModel.logOut()
    }
}

