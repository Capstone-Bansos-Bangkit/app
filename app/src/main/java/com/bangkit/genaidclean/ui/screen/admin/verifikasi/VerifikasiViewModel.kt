package com.bangkit.genaidclean.ui.screen.admin.verifikasi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.genaidclean.data.remote.response.admin.SubmissionListResponse
import com.bangkit.genaidclean.data.repository.AppRepository
import com.bangkit.genaidclean.utils.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class VerifikasiViewModel (private val repo: AppRepository): ViewModel() {

    private val _state = MutableStateFlow<State<SubmissionListResponse>>(State.Loading)
    val state = _state.asStateFlow()


    fun fetchData() {
        viewModelScope.launch {
            try {
                repo.getPendingSubmissionList()
                    .catch {
                        _state.value = State.Error(it.message)
                    }
                    .collect {
                        _state.value = State.Success(it)
                    }
            } catch (e: Exception) {
                _state.value = State.Error(e.message)
            }
        }
    }


}