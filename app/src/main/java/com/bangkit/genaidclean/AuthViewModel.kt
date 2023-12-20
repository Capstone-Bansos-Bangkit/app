package com.bangkit.genaidclean

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.genaidclean.data.preferences.UserModel
import com.bangkit.genaidclean.data.repository.AppRepository
import com.bangkit.genaidclean.utils.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repo: AppRepository): ViewModel() {

    private val _loginResult = MutableStateFlow<State<UserModel>>(State.Loading)
    val loginResult: StateFlow<State<UserModel>> = _loginResult.asStateFlow()

    fun loginUser(
        nik: String,
        motherName: String,
        birthDate: String
    ) {
        viewModelScope.launch {
            repo.loginUser(nik, motherName, birthDate)
                .collect{result ->
                    when(result) {
                        is State.Loading -> {
                            _loginResult.value = State.Loading
                        }
                        is State.Success -> {
                            _loginResult.value = State.Success(result.data)
                            saveSession(result.data)
                        }
                        is State.Error -> {
                            _loginResult.value = State.Error(result.error)
                        }
                    }
                }
        }
    }

    fun loginAdmin(
        username: String,
        password: String,
    ) {
        viewModelScope.launch {
            repo.loginAdmin(username, password)
                .collect {result ->
                    when (result) {
                        is State.Loading -> {
                            _loginResult.value = State.Loading
                        }
                        is State.Success -> {
                            _loginResult.value = State.Success(result.data)
                            saveSession(result.data)
                        }
                        is State.Error -> {
                            _loginResult.value = State.Error(result.error)
                        }
                    }
                }
        }
    }

    private fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repo.saveSession(user)
        }
    }
}