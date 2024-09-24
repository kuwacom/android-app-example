package dev.kuwa.android_app_example.ui.voicevox

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.kuwa.android_app_example.data.model.VoiceVoxSpeaker
import dev.kuwa.android_app_example.data.repository.VoiceVoxRepository
import kotlinx.coroutines.launch

class VoiceVoxViewModel(private val voiceVoxRepository: VoiceVoxRepository) : ViewModel() {

    private val _speakers = MutableLiveData<List<VoiceVoxSpeaker>>()
    val speakers: LiveData<List<VoiceVoxSpeaker>> get() = _speakers

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchSpeakers() {
        viewModelScope.launch {
            // Repositoryからデータを取得
            val result = voiceVoxRepository.getSpeakers()
            // 成功・失敗に応じた処理
            result.fold(
                onSuccess = { data ->
                    // 成功した場合、データをLiveDataに格納
                    _speakers.value = data
                },
                onFailure = { throwable ->
                    // 失敗した場合、エラーメッセージをLiveDataに格納
                    _error.value = throwable.message
                }
            )
        }
    }

}