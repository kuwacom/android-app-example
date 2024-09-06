package dev.kuwa.test_android_navigation.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import dev.kuwa.test_android_navigation.R

/**
 * A simple [Fragment] subclass.
 * Use the [SelectLoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectLoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // レイアウトをinflateして返すだけ
        return inflater.inflate(R.layout.fragment_select_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // GoogleLoginボタン押したら飛ぶ
        view.findViewById<Button>(R.id.loginGoogle).setOnClickListener {
            findNavController().navigate(R.id.action_selectLoginFragment_to_loginGoogleFragment)
        }
    }
}