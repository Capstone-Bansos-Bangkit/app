package com.bangkit.genaidclean.ui.screen.admin.verifikasi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.genaidclean.data.remote.response.admin.SubmissionDetailResponse
import com.bangkit.genaidclean.data.remote.response.admin.SubmissionListResponse
import com.bangkit.genaidclean.data.remote.response.admin.VerifySubmissionResponse
import com.bangkit.genaidclean.data.repository.AppRepository
import com.bangkit.genaidclean.utils.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class VerifikasiViewModel (private val repo: AppRepository): ViewModel() {

    private val _state = MutableStateFlow<State<SubmissionListResponse>>(State.Loading)
    val state = _state.asStateFlow()

    private val _detailSubmis = MutableStateFlow<State<SubmissionDetailResponse>>(State.Loading)
    val detailSubmis = _detailSubmis.asStateFlow()

    private val _verifSubmis = MutableStateFlow<State<VerifySubmissionResponse>>(State.Loading)
    val verifSubmis = _verifSubmis.asStateFlow()


    fun fetchData(bansosId: Int? = null) {
        viewModelScope.launch {
            try {
                repo.getPendingSubmissionList(bansosId = bansosId)
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

    fun getDetailSubmission(id: Int) {
        viewModelScope.launch {
            try {
                repo.getDetailSubmission(id)
                    .catch {
                        _detailSubmis.value = State.Error(it.message)
                    }
                    .collect {
                        _detailSubmis.value = State.Success(it)
                    }
            } catch (e: Exception) {
                _detailSubmis.value = State.Error(e.message)
            }
        }
    }

    fun verifSubmission(id: Int, status: String) {
        viewModelScope.launch {
            try {
                repo.verifySubmission(userSubmissionId = id, status = status)
                    .catch {
                        _verifSubmis.value = State.Error(it.message)
                    }
                    .collect {
                        _verifSubmis.value = State.Success(it)
                    }
            } catch (e: Exception) {
                _state.value = State.Error(e.message)
            }
        }
    }


}