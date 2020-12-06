package uz.smd.fantastic.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_user.*

import uz.smd.fantastic.R

/**
 * Created by Siddikov Mukhriddin on 12/5/20
 */
class UserFragment:Fragment(R.layout.fragment_user) {
    private var navController: NavController? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        clickListener()
    }

    private fun clickListener() {
        btnSendVoice.setOnClickListener {
            navController!!.navigate(R.id.action_userFragment_to_voiceFragment)
        }
        btnCheckVoice.setOnClickListener {
            navController!!.navigate(R.id.action_userFragment_to_mainFragment)
        }

    }
}