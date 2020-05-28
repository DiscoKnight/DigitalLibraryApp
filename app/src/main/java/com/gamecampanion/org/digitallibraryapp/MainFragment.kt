package com.gamecampanion.org.digitallibraryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gamecampanion.org.digitallibraryapp.R.id.action_ThirdFrgment_to_SecondFragment
import com.gamecampanion.org.digitallibraryapp.R.id.action_fourthFragment_to_ThirdFragment

class MainFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragmentmain, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.addButton).setOnClickListener {
            findNavController().navigate(action_ThirdFrgment_to_SecondFragment)
        }

        view.findViewById<Button>(R.id.viewButton).setOnClickListener {
            findNavController().navigate(action_fourthFragment_to_ThirdFragment)
        }

    }

}