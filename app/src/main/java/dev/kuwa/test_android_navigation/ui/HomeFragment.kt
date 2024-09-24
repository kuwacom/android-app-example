package dev.kuwa.test_android_navigation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dev.kuwa.test_android_navigation.databinding.FragmentHomeBinding
import dev.kuwa.test_android_navigation.notifications.GeneralNotification
import dev.kuwa.test_android_navigation.utils.openNotificationSettings

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

//    private val args: HomeFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 必要な処理はここで

        // OpenNotificationSettingsButton
        binding.openNotificationSettingsButton.setOnClickListener {
            openNotificationSettings(requireContext())
        }

        // NotificationCheckButton
        binding.notificationCheckButton.setOnClickListener {
            GeneralNotification(requireContext()).sendNotification(
                1,
                "通知が有効です！",
                "アプリからの通知が許可されています",
//                R.drawable.notification // 通知アイコン(白黒)
            )
        }

        // VoiceVoxButton
        binding.voiceVoxButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToVoiceVoxFragment()
            findNavController().navigate(action)
        }

        // TimeInfo
        binding.timeInfoButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToTimeInfoFragment()
            findNavController().navigate(action)
        }

    }
}