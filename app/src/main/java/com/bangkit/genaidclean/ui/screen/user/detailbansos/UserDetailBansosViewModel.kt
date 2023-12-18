package com.bangkit.genaidclean.ui.screen.user.detailbansos

import androidx.lifecycle.ViewModel
import com.bangkit.genaidclean.data.remote.response.ResultItem
import com.bangkit.genaidclean.data.repository.AppRepository
import com.bangkit.genaidclean.utils.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserDetailBansosViewModel ( private val repo: AppRepository): ViewModel() {


    private val _result: MutableStateFlow<State<ResultItem>> =
        MutableStateFlow(State.Loading)

    val result: StateFlow<State<ResultItem>>
        get() = _result

    suspend fun getBansosById(bansosName: String): List<ResultItem> {
        return repo.getPrfileBansosName(bansosName)
    }

}