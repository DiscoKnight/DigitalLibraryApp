package com.gamecampanion.org.digitallibraryapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gamecampanion.org.digitallibraryapp.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstScreenFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layoutmobilefirstscreen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val navController = parentFragmentManager.findFragmentById(R.id.my_nav_host_fragment)?.findNavController()

        //val navController = childFragmentManager.findFragmentById(R.id.my_nav_host_fragment)?.findNavController()

        System.out.println("smurf")
//
//        view.findViewById<Button>(R.id.signUpButton).setOnClickListener {
//            navController?.navigate(R.id.action_signUpActivity_to_signUpFragmentNav)
//        }
//
//        view.findViewById<Button>(R.id.signInButton).setOnClickListener {
//            navController?.navigate(R.id.action_startFragment_to_providerSelect)
//        }
    }
}
