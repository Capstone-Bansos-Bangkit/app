package com.bangkit.genaidclean.ui.screen.admin.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.genaidclean.data.models.DataSubmission
import com.bangkit.genaidclean.data.remote.response.admin.ResultItem
import com.bangkit.genaidclean.data.remote.response.admin.SubmissionListResponse
import com.bangkit.genaidclean.data.remote.response.admin.SubmissionSummaryResponse
import com.bangkit.genaidclean.data.repository.AppRepository
import com.bangkit.genaidclean.utils.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DashboardAdminViewModel (private val repo: AppRepository) : ViewModel() {

    private val _result =
        MutableStateFlow<State<SubmissionSummaryResponse>>(State.Loading)
    val result = _result.asStateFlow()


    private val _data =
        MutableStateFlow<State<DashboardData>>(State.Loading)
    val data = _data.asStateFlow()


    fun fetchData() {
        viewModelScope.launch {
            try {
                var dataSubmission: SubmissionListResponse = SubmissionListResponse(emptyList(), 0, "")
                var submissionSummary: SubmissionSummaryResponse? = null

                repo.getApprovedSubmissionList()
                    .catch {
                        _data.value = State.Error(it.message)
                    }
                    .collect {
                        dataSubmission = it
                    }

                repo.getSubmissionSummary()
                    .catch {
                        _data.value = State.Error(it.message)
                    }
                    .collect {
                        submissionSummary = it
                    }

                if (submissionSummary != null) {
                    _data.value = State.Success(DashboardData(submissionSummary!!, dataSubmission))
                } else {
                    _data.value = State.Error("Failed to fetch submission summary")
                }
            } catch (e: Exception) {
                _data.value = State.Error(e.message)
            }
        }
    }

}

data class DashboardData(
    val submissionSummary: SubmissionSummaryResponse,
    val dataSubmission : SubmissionListResponse
)