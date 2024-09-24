package dev.kuwa.android_app_example.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.kuwa.android_app_example.databinding.FragmentSelectLoginBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SelectLoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectLoginFragment : Fragment() {

    private var _binding: FragmentSelectLoginBinding? = null
    private val binding get() = _binding!!

//    private val args: SelectLoginFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSelectLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // GoogleLoginボタン押したら飛ぶ
        binding.loginGoogle.setOnClickListener {
            val action = SelectLoginFragmentDirections.actionSelectLoginFragmentToLoginGoogleFragment()
            findNavController().navigate(action)
        }

        // LoginCancelボタン押したら飛ぶ
        binding.loginCancel.setOnClickListener {
            val action = SelectLoginFragmentDirections.actionSelectLoginFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }
}