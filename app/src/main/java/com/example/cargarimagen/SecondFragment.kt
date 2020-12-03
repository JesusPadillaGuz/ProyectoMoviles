package com.example.cargarimagen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    var applicacion: AccederApp? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applicacion = AccederApp(requireActivity().applicationContext)
        view.findViewById<Button>(R.id.button_cargarimagen).setOnClickListener {
            /* findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)*/
      //      application.room.DescripcionDao().insert()
        }

        /*view.findViewById<Button>(R.id.button_second).setOnClickListener {
             findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }*/
        buttonChido.setOnClickListener {

            val inputDescripcion : Descripcion?= Descripcion(0,"","")

            //OJO AQUI
            val variable: EditText = view.findViewById(R.id.inputdescripcion)
            //   inputDescripcion?.descrip = findViewById(R.id.Texto)

            inputDescripcion?.descrip = inputdescripcion.text.toString()
            // inputDescripcion?.ruta =""


            //se supone que aqui va la corrutina
            if (inputDescripcion != null) {
                lifecycleScope.launch{
                    val d = Descripcion(null, variable.text.toString(),"")
                    applicacion?.room?.descripcionDao()?.insert(d)
                    val basecompleta = applicacion?.room?.descripcionDao()?.getAll()
                    println("${basecompleta}")
                }

            }

        }
    }
}
