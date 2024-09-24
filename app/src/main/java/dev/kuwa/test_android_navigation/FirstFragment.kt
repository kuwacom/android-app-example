package dev.kuwa.test_android_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.kuwa.test_android_navigation.databinding.FragmentFirstBinding

/**
 * 最初に表示される画面(ロード表記にする)
 * This Fragment is view first.
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {

    companion object {
        fun newInstance() = FirstFragment()
    }

    private lateinit var viewModel: FirstViewModel

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val app = requireActivity().application as MainApplication

        view.postDelayed({
            // ログイン状態のチェック
            if (app.isLoggedIn()) {
                // ログイン済みならHomeFragmentに遷移
                findNavController().navigate(R.id.action_firstFragment_to_homeFragment)
            } else {
                // 未ログインならLoginFragmentに遷移
                findNavController().navigate(R.id.action_firstFragment_to_selectLoginFragment)
            }
        }, 10000) // 疑似ロードで1秒待つ
    }

}