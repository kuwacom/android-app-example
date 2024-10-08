package dev.kuwa.android_app_example.ui.voicevox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.kuwa.android_app_example.data.repository.VoiceVoxRepository

class VoiceVoxViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VoiceVoxViewModel::class.java)) {
            // RepositoryをFactory内で直接生成してViewModelに渡す
            return VoiceVoxViewModel(voiceVoxRepository = VoiceVoxRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}