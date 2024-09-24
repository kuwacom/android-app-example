package dev.kuwa.android_app_example.ui.timeInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.moshi.Moshi
import dev.kuwa.android_app_example.data.model.TimeInfo
import dev.kuwa.android_app_example.databinding.FragmentTimeInfoBinding

class TimeInfoFragment : Fragment() {

    companion object {
        fun newInstance() = TimeInfoFragment()
    }

    private lateinit var viewModel: TimeInfoViewModel

    private var _binding: FragmentTimeInfoBinding? = null
    private val binding get() = _binding!!

//    private val args: TimeInfoFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTimeInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModelのセットアップ
        val viewModelFactory = TimeInfoViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(TimeInfoViewModel::class.java)

        // データの監視
        viewModel.timeData.observe(viewLifecycleOwner, Observer { timeData ->

            // UI更新: speakersデータを利用してリスト表示や他の処理を行う
            val moshi = Moshi.Builder().build()
            // List<SampleResponse> 用のアダプタを取得
            val adapter = moshi.adapter(TimeInfo::class.java)

            binding.textViewUtcOffset.text = timeData.utcOffset
            binding.textViewTimezone.text = timeData.timezone
            binding.textViewDayOfWeek.text = timeData.dayOfWeek.toString()
            binding.textViewDayOfYear.text = timeData.dayOfYear.toString()
            binding.textViewDatetime.text = timeData.datetime
            binding.textViewUtcDatetime.text = timeData.utcDatetime
            binding.textViewUnixtime.text = timeData.unixtime.toString()
            binding.textViewRawOffset.text = timeData.rawOffset.toString()
            binding.textViewWeekNumber.text = timeData.weekNumber.toString()
            binding.textViewDst.text = timeData.dst.toString()
            binding.textViewAbbreviation.text = timeData.abbreviation
            binding.textViewDstOffset.text = timeData.dstOffset.toString()
            binding.textViewDstFrom.text = timeData.dstFrom ?: "N/A"
            binding.textViewDstUntil.text = timeData.dstUntil ?: "N/A"
            binding.textViewClientIp.text = timeData.clientIp

            binding.jsonDisplay.text = adapter.toJson(timeData)
        })

        // エラーメッセージの監視
        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            // エラーメッセージの表示などの処理を行う
        })

        viewModel.fetchTimeInfo()

    }
}