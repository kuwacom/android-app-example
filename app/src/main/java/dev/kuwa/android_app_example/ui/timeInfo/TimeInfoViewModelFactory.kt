package dev.kuwa.android_app_example.ui.timeInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.kuwa.android_app_example.data.repository.TimeInfoRepository

class TimeInfoViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimeInfoViewModel::class.java)) {
            // RepositoryをFactory内で直接生成してViewModelに渡す
            return TimeInfoViewModel(timeInfoRepository = TimeInfoRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}