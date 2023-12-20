package com.bangkit.genaidclean.ui.screen.user.question

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.genaidclean.data.remote.response.ResultItem
import com.bangkit.genaidclean.data.remote.response.user.QuestionResponse
import com.bangkit.genaidclean.data.repository.AppRepository
import com.bangkit.genaidclean.utils.State as ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuestionViewModel (private val repository: AppRepository) : ViewModel(){

    private val _result: MutableStateFlow<ResultState<ResultItem>> =
        MutableStateFlow(ResultState.Loading)
    val result: StateFlow<ResultState<ResultItem>>
        get() = _result

    suspend fun getBansosById(bansosId: Int): List<ResultItem> {
        return repository.getBansosById(bansosId)
    }


    ////// halaman question
    private val _questionResponse = mutableStateOf<QuestionResponse?>(null)
    val questionResponse: State<QuestionResponse?> = _questionResponse

    fun getQuestions() {
        viewModelScope.launch {
            _questionResponse.value = repository.getQuestions()
        }
    }
}