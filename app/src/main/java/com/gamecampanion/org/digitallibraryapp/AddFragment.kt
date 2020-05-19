package com.gamecampanion.org.digitallibraryapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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

        var typeSpinner: Spinner = view.findViewById(R.id.typeCollection)

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
            1 -> configLayoutMusic()
            2 -> configLayoutMovie()
            else -> {}
        }
    }

    private fun configLayoutGame(view: View, context: Context){

        print("smurf")
       //var pSpiner: Spinner = view.findViewById(R.id.platformSpinner)
//
//        val s = resources.getStringArray(R.array.gamePlatform)
//
//        val adapter = ArrayAdapter(context,
//            android.R.layout.simple_spinner_item, s)
    }

//    private fun ArrayAdapter(view: View, fragmentMain: Int, s: Array<String>): Any {
//
//    }

    private fun configLayoutMovie(){}

    private fun configLayoutMusic(){}
}


