package com.gamecampanion.org.digitallibraryapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add, container, false)

        var typeSpinner: Spinner = view.findViewById(R.id.typeSpinner)

        typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                print("smurf")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (view != null) {
                    configLayout(position, view, view.context )
                }
                print("smurf1")
            }

        }

        var addButton: Button = view.findViewById(R.id.button_second);

        addButton.setOnClickListener {
            print("smurf")
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun configLayout(index: Int, view: View, context: Context){
        when(index){
            0 -> configLayoutGame(view, context)
            else -> {}
        }
    }

    private fun configLayoutGame(view: View, context: Context){
        print("smurf")
    }

    fun addToCollection(view: View){
        print("smurf1");
    }


}


