package dev.kuwa.test_android_navigation.ui.voicevox

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dev.kuwa.test_android_navigation.R
import dev.kuwa.test_android_navigation.data.model.VoiceVoxSpeaker
import dev.kuwa.test_android_navigation.databinding.FragmentVoiceVoxBinding
import kotlin.random.Random

class VoiceVoxFragment() : Fragment() {

    companion object {
        fun newInstance() = VoiceVoxFragment()
    }

    private lateinit var viewModel: VoiceVoxViewModel

    private var _binding: FragmentVoiceVoxBinding? = null
    private val binding get() = _binding!!

//    private val args: VoiceVoxFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentVoiceVoxBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModelのセットアップ
        val viewModelFactory = VoiceVoxViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(VoiceVoxViewModel::class.java)

        // データの監視
        viewModel.speakers.observe(viewLifecycleOwner, Observer { speakers ->

            // UI更新: speakersデータを利用してリスト表示や他の処理を行う
            val moshi = Moshi.Builder().build()
            // List<SampleResponse> のタイプを定義
            val type = Types.newParameterizedType(List::class.java, VoiceVoxSpeaker::class.java)
            // List<SampleResponse> 用のアダプタを取得
            val adapter = moshi.adapter<List<VoiceVoxSpeaker>>(type)

            binding.speakersJson.text = adapter.toJson(speakers)

            val index = Random.nextInt(0, speakers.size - 1)
            binding.speakerNameDisplay.text = speakers[index].name
            binding.speakerUUIDDisplay.text = speakers[index].version
        })

        // エラーメッセージの監視
        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            // エラーメッセージの表示などの処理を行う
            println(error)
            binding.errorDisplay.text = error
        })

        viewModel.fetchSpeakers()

    }

}