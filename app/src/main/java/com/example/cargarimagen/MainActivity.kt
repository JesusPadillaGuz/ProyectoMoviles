package com.example.cargarimagen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_second.*

class MainActivity : AppCompatActivity() {
    private lateinit var login: LoginControl
    private lateinit var lblMensaje: TextView

//variable que accede a la Bd

  //aqui
    private var applicacion: AccederApp? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        //aqui es donde guardotodo el listado de la base de datos
        applicacion = AccederApp(applicationContext)

        //aqui recupero el id de lo que se insertó en texto y lo guardo en la Bd pero falta que lo ponga en el campo de Descrip
        login = findViewById(R.id.ctlLogin)
        lblMensaje = findViewById(R.id.lblMensaje)


        login.setOnLoginListener{ usuario, password ->
            if(usuario=="usr" && password =="pass")
                lblMensaje.text="Login Correcto"
            
            else
                lblMensaje.text="Contraseña Incorrecta"

        }
         }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }







}