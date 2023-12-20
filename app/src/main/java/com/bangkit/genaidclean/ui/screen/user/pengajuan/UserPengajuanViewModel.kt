package com.bangkit.genaidclean.ui.screen.user.pengajuan

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.genaidclean.data.remote.response.ResultItem
import com.bangkit.genaidclean.data.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UserPengajuanViewModel ( private val repository: AppRepository): ViewModel() {

//    private val _bansosData = mutableStateOf<List<ResultItem?>?>(null)
//    val bansosData: State<List<ResultItem?>?> = _bansosData

    private val _groupedBansos = MutableStateFlow<Map<Char, List<ResultItem>>>(emptyMap())
    val groupedBansos: MutableStateFlow<Map<Char, List<ResultItem>>> get() = _groupedBansos

    private fun loadGroupedBansos() {
        viewModelScope.launch {
            try {
                val allBansos = repository.getAllProfBansos() // Memanggil di dalam coroutine
                val groupedBansos = allBansos
                    .sortedBy { it.bansosProviderId}
                    .groupBy { it.name[0] }
                _groupedBansos.value = groupedBansos
            } catch (e: Exception) {
                // Handle error, if any
                e.printStackTrace()
            }
        }
    }
    init {
        loadGroupedBansos()
    }

}