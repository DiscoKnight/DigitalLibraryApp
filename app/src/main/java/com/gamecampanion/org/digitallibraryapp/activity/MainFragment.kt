package com.gamecampanion.org.digitallibraryapp.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gamecampanion.org.digitallibraryapp.R
import com.gamecampanion.org.digitallibraryapp.R.id.*

class MainFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layoutmobilemain, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(addButton).setOnClickListener {
            findNavController().navigate(action_ThirdFrgment_to_SecondFragment)
        }

        view.findViewById<Button>(viewButton).setOnClickListener {
            findNavController().navigate(action_fourthFragment_to_ThirdFragment)
        }

        view.findViewById<Button>(deleteButton).setOnClickListener {
            findNavController().navigate(action_mainFragment_to_DeleteFragment)
        }

        view.findViewById<Button>(editButton).setOnClickListener {
            findNavController().navigate(action_mainfragment_to_editfragment)
        }

    }

}