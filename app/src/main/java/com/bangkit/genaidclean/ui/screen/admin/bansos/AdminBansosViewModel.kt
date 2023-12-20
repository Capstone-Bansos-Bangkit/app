package com.bangkit.genaidclean.ui.screen.admin.bansos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.genaidclean.data.remote.response.Bansos
import com.bangkit.genaidclean.data.remote.response.ResultItem as BansosResult
import com.bangkit.genaidclean.data.repository.AppRepository
import com.bangkit.genaidclean.utils.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AdminBansosViewModel(private val repo: AppRepository) : ViewModel() {

    private val _state = MutableStateFlow<State<Bansos>>(State.Loading)
    val state = _state.asStateFlow()

    private val _bansosDetail = MutableStateFlow<State<BansosResult>>(State.Loading)
    val bansosDetail = _bansosDetail.asStateFlow()

    fun getBansos() {
        viewModelScope.launch {
            try {
                repo.getDatabansos()
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

    fun getBansosById(id: Int) {
        viewModelScope.launch {
            try {
                repo.getBansosById(id)
                _bansosDetail.value = State.Success(repo.getBansosDetail(id))

            } catch (e: Exception) {
                _bansosDetail.value = State.Error(e.message)
            }
        }
    }

}