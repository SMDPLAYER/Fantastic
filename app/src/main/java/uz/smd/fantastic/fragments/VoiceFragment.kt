package uz.smd.fantastic.fragments


import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.fragment_voice.*
import uz.smd.fantastic.R
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Siddikov Mukhriddin on 12/5/20
 */
class VoiceFragment:Fragment(R.layout.fragment_voice) {
    private var navController: NavController? = null

    private var listBtn: Button? = null
    private var recordBtn: ImageView? = null
//    private var filenameText: TextView? = null

    private var isRecording = false

    private val recordPermission = Manifest.permission.RECORD_AUDIO
    private val PERMISSION_CODE = 21

    private var mediaRecorder: MediaRecorder? = null
    private var recordFile: String? = null

    private var timer: Chronometer? = null




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                recordPermission
            ) != PackageManager.PERMISSION_GRANTED)
        sweatAlertDialog("Sizning ovozingiz tekshirish va taqqoslash maqsadida boshqa foydalanuvchilarga jo'natilishi mumkin. Shunga rozimisiz?")
        //Intitialize Variables
        navController = Navigation.findNavController(view)
        listBtn = btnSendAudio
        recordBtn = player_play_btn
        timer =record_timer
//        filenameText = record_filename
onClick()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun onClick() {
        /*  Check, which button is pressed and do the task accordingly
         */
        btnSendAudio.setOnClickListener {
            if (isRecording) {
                val alertDialog = AlertDialog.Builder(
                    context
                )
                alertDialog.setPositiveButton(
                    "Ha"
                ) { dialog, which ->
                    navController!!.navigate(R.id.action_voiceFragment_to_congratFragment)
                    isRecording = false
                }
                alertDialog.setNegativeButton("Yo'q", null)
                alertDialog.setTitle("Audio yozib olinmoqda ")
                alertDialog.setMessage("Audio yozib olishni to'xtatishga ishonchingiz komilmi")
                alertDialog.create().show()
            } else {
                navController!!.navigate(R.id.action_voiceFragment_to_congratFragment)
            }
        }
        player_play_btn.setOnClickListener {
            if (isRecording) {
                //Stop Recording
                stopRecording()

                // Change button image and set Recording state to false
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    recordBtn!!.setImageDrawable(
                        resources.getDrawable(
                            R.drawable.record_btn_stopped,
                            null
                        )
                    )
                }
                isRecording = false
            } else {

                //Check permission to record audio
                if (checkPermissions()) {
                    //Start Recording
                    startRecording()

                    // Change button image and set Recording state to false
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        recordBtn!!.setImageDrawable(
                            resources.getDrawable(
                                R.drawable.record_btn_recording,
                                null
                            )
                        )
                    }
                    isRecording = true
                }

            }
        }
     /*   when (v.id) {
            R.id.record_list_btn ->                 *//*
                Navigation Controller
                Part of Android Jetpack, used for navigation between both fragments
                 *//*
                if (isRecording) {
                val alertDialog = AlertDialog.Builder(
                    context
                )
                alertDialog.setPositiveButton(
                    "OKAY"
                ) { dialog, which ->
                    navController!!.navigate(R.id.action_recordFragment_to_audioListFragment)
                    isRecording = false
                }
                alertDialog.setNegativeButton("CANCEL", null)
                alertDialog.setTitle("Audio Still recording")
                alertDialog.setMessage("Are you sure, you want to stop the recording?")
                alertDialog.create().show()
            } else {
                navController!!.navigate(R.id.action_recordFragment_to_audioListFragment)
            }
            R.id.record_btn -> if (isRecording) {
                //Stop Recording
                stopRecording()

                // Change button image and set Recording state to false
                recordBtn!!.setImageDrawable(
                    resources.getDrawable(
                        R.drawable.record_btn_stopped,
                        null
                    )
                )
                isRecording = false
            } else {
                //Check permission to record audio
                if (checkPermissions()) {
                    //Start Recording
                    startRecording()

                    // Change button image and set Recording state to false
                    recordBtn!!.setImageDrawable(
                        resources.getDrawable(
                            R.drawable.record_btn_recording,
                            null
                        )
                    )
                    isRecording = true
                }
            }
        }*/
    }

    private fun stopRecording() {
        //Stop Timer, very obvious
        timer!!.stop()

        //Change text on page to file saved
//        filenameText!!.text = "Recording Stopped, File Saved : $recordFile"
Toast.makeText(requireContext(),"Ovoz yozib olish to'xtatildi",Toast.LENGTH_SHORT).show()
        //Stop media recorder and set it to null for further use to record new audio
        mediaRecorder!!.stop()
        mediaRecorder!!.release()
        mediaRecorder = null
    }

    private fun startRecording() {
        //Start timer from 0
        timer!!.base = SystemClock.elapsedRealtime()
        timer!!.start()
        Toast.makeText(requireContext(),"Ovoz yozib olish boshlandi",Toast.LENGTH_SHORT).show()
        //Get app external directory path
        val recordPath = requireActivity().getExternalFilesDir("/")!!.absolutePath

        //Get current date and time
        val formatter = SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.CANADA)
        val now = Date()

        //initialize filename variable with date and time at the end to ensure the new file wont overwrite previous file
        recordFile = "Recording_" + formatter.format(now) + ".3gp"
//        filenameText!!.text = "Recording, File Name : $recordFile"

        //Setup Media Recorder for recording
        mediaRecorder = MediaRecorder()
        mediaRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder!!.setOutputFile("$recordPath/$recordFile")
        mediaRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        try {
            mediaRecorder!!.prepare()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        //Start Recording
        mediaRecorder!!.start()
    }

    private fun checkPermissions(): Boolean {
        //Check permission
        return if (ActivityCompat.checkSelfPermission(
                requireContext(),
                recordPermission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            //Permission Granted
            true
        } else {

            //Permission not granted, ask for permission
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(recordPermission),
                PERMISSION_CODE
            )
            false
        }
    }

    override fun onStop() {
        super.onStop()
        if (isRecording) {
            stopRecording()
        }
    }
//    private var navController: NavController? = null
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        navController = Navigation.findNavController(view)
//        clickListener()
//    }
//
//    private fun clickListener() {
//        player_play_btn.setOnClickListener {
//            navController!!.navigate(R.id.action_userFragment_to_voiceFragment)
//        }
//
//
//    }
}
fun Fragment.sweatAlertDialog(title:String,toast: String="") {
    SweetAlertDialog(view!!.context, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
        .setCustomImage(R.drawable.bg_btn_info)
        .setTitleText(title)
        .setConfirmText("Ha")
        .setCancelButton("Yo\'q") { obj: SweetAlertDialog -> obj.dismissWithAnimation() }
        .setConfirmClickListener { sweetAlertDialog: SweetAlertDialog ->
            sweetAlertDialog.dismissWithAnimation()
            if (toast!=""){
                Toast.makeText(
                    requireContext(),
                    toast,
                    Toast.LENGTH_SHORT
                ).show()
            }

        }.show()
}