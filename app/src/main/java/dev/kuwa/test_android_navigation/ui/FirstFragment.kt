package dev.kuwa.test_android_navigation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.kuwa.test_android_navigation.R

/**
 * 最初に表示される画面(ロード表記にする)
 * This Fragment is view first.
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.postDelayed({
            // ログイン状態のチェック
            if (isUserLoggedIn()) {
                // ログイン済みならHomeFragmentに遷移
                findNavController().navigate(R.id.action_firstFragment_to_homeFragment)
            } else {
                // 未ログインならLoginFragmentに遷移
                findNavController().navigate(R.id.action_firstFragment_to_selectLoginFragment)
            }
        }, 2000)
    }

    private fun isUserLoggedIn(): Boolean {
        return false
    }
}