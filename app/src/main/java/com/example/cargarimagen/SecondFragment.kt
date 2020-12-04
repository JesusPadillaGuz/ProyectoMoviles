package com.example.cargarimagen

import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    var applicacion: AccederApp? = null
    val SELECT_FOTO = 1
    val mImages: Uri? = null
    var path: String? = null
    var filePath = ""
    private var carta: Descripcion? = null
    private var position: Int? = null


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

        when {
            ContextCompat.checkSelfPermission(
                context!!,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                view.findViewById<Button>(R.id.button_cargarimagen).setOnClickListener{
                    val intent: Intent = Intent(
                        Intent.ACTION_GET_CONTENT,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,mImages)
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                    intent.setType("image/*")
                    startActivityForResult(Intent.createChooser(intent, "Elige aplicacion"), SELECT_FOTO)

                }
            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                val permisos = android.Manifest.permission.READ_EXTERNAL_STORAGE
                activity?.let { ActivityCompat.requestPermissions(it, arrayOf(permisos), 50) }
            }
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

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== AppCompatActivity.RESULT_OK) {
            val selectedImage: Uri? = data?.data
            val wholeID = DocumentsContract.getDocumentId(selectedImage)
            val id = wholeID.split(":".toRegex()).toTypedArray()[1]

            val column = arrayOf(MediaStore.Images.Media.DATA)
            val sel = MediaStore.Images.Media._ID + "=?"

            val cursor: Cursor? = activity?.contentResolver?.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,column, sel, arrayOf(id), null)
            //  var filePath = ""
            val columnIndex = cursor?.getColumnIndex(column[0])
            if (cursor != null) {
                if(cursor.moveToFirst())
                    filePath = cursor.getString(columnIndex!!)
            }
            path = filePath
            println(filePath)
            val bitmap = BitmapFactory.decodeFile(path); // Crea un mapa de bits y lo asigna a un imagenView
            ponerimagen.setImageBitmap(bitmap);

        }
    }
}
