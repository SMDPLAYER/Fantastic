package uz.smd.fantastic.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*
import uz.smd.fantastic.R
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Siddikov Mukhriddin on 12/5/20
 */
class MainFragment : Fragment(R.layout.fragment_main) {
    private var navController: NavController? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        clickListener()
    }

    private fun clickListener() {
        btnAudio.setOnClickListener {
            navController!!.navigate(R.id.action_mainFragment_to_booksFragment)
        }
        btnUser.setOnClickListener {
            navController!!.navigate(R.id.action_mainFragment_to_voiceFragment)
        }

    }


}