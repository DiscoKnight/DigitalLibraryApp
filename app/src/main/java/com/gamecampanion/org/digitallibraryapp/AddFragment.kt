package com.gamecampanion.org.digitallibraryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.lang.System.out

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddFragment : Fragment() {


    lateinit var typeSpinner: Spinner

    lateinit var genreSpinner: Spinner

    lateinit var platformSpinner: Spinner

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add, container, false)

        typeSpinner = view.findViewById(R.id.typeSpinner)

        genreSpinner = view.findViewById(R.id.genreSpinner)

        platformSpinner = view.findViewById(R.id.platformSpinner)

        ///////////////////////////////////////////
        typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                print("smurf")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (view != null) {
                    configLayout(position)
                }
            }

        }

        /////////////////////////////////////////

        var addButton: Button = view.findViewById(R.id.button_second);

        addButton.setOnClickListener {
            print("smurf")
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun configLayout(position : Int){
        when(position){
            0 -> configLayout(resources.getStringArray(R.array.gamePlatform), true)
            1 -> configLayout(resources.getStringArray(R.array.musicGenre), false)
            2 -> configLayout(resources.getStringArray(R.array.movieGenre), false)
        }
    }

    private fun configLayout(genreSpinnerArray: Array<String>, isEnabled: Boolean){
            genreSpinner.isEnabled = isEnabled

            platformSpinner.adapter = ArrayAdapter(this.requireContext(),
                android.R.layout.simple_spinner_item,
                genreSpinnerArray)
    }

}


