package com.bhaveshp750.allclosedpullrequests.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhaveshp750.allclosedpullrequests.modal.PullRequest
import com.bhaveshp750.allclosedpullrequests.modal.PullRequestsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val api: PullRequestsApi
    ) : ViewModel() {

    val loading  = MutableLiveData<Boolean>()
    val pullRequestLoadError = MutableLiveData<Boolean>()
    val pullRequestList = MutableLiveData<List<PullRequest>?>()

    init {
        getPullRequests()
    }

    private fun getPullRequests() {
        loading.value = true
        viewModelScope.launch {
            try {
                pullRequestList.value = api.getPullRequests()
                pullRequestLoadError.value = false
                loading.value = false
            } catch (e: Exception) {
                Log.e("MainViewModel", "makePullRequest: ", e)
                pullRequestLoadError.value = true
                loading.value = false
            }
        }
    }
}