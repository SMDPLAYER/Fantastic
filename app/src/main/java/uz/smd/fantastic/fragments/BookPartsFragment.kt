package uz.smd.fantastic.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_book_parts.*
import uz.smd.fantastic.R

/**
 * Created by Siddikov Mukhriddin on 12/5/20
 */
class BookPartsFragment:Fragment(R.layout.fragment_book_parts) {
    private var navController: NavController? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        clickListener()
    }

    fun onClick(){
        navController!!.navigate(R.id.action_bookPartsFragment_to_textFragment)
    }
    private fun clickListener() {
        btnRandom.setOnClickListener {
            onClick()
        }
        btnRandom1.setOnClickListener {
            onClick()
        }
        btnRandom2.setOnClickListener {
            onClick()
        }
        btnRandom3.setOnClickListener {
            onClick()
        }
        btnRandom4.setOnClickListener {
            onClick()
        }
        btnRandom5.setOnClickListener {
            onClick()
        }
        btnRandom6.setOnClickListener {
            onClick()
        }



    }
}