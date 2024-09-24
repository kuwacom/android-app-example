package dev.kuwa.test_android_navigation.ui.timeInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.kuwa.test_android_navigation.data.model.TimeInfo
import dev.kuwa.test_android_navigation.data.repository.TimeInfoRepository
import kotlinx.coroutines.launch

class TimeInfoViewModel(private val timeInfoRepository: TimeInfoRepository) : ViewModel() {

    private val _timeData = MutableLiveData<TimeInfo>()
    val timeData: LiveData<TimeInfo> get() = _timeData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchTimeInfo() {
        viewModelScope.launch {
            // Repositoryからデータを取得
            val result = timeInfoRepository.getTimeInfo()
            println(result)
            // 成功・失敗に応じた処理
            result.fold(
                onSuccess = { data ->
                    // 成功した場合、データをLiveDataに格納
                    println(data)
                    _timeData.value = data
                },
                onFailure = { throwable ->
                    // 失敗した場合、エラーメッセージをLiveDataに格納
                    _error.value = throwable.message
                }
            )
        }
    }

}