@file:Suppress("DEPRECATION", "DEPRECATION")

package com.eugeniogriegues.utn.climaciudades

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter
import org.json.JSONObject
import java.net.URL


@Suppress("DEPRECATION", "OverridingDeprecatedMember", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {

    var CIUDAD: String = ""
    val API: String = "d786f3ff3e90c8291eb0ee7743657460"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val flag: Int = 0

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)

        val nuevaCiudad = intent.getStringExtra("nueva_ciudad")


        val myPref = prefs.all.keys.toTypedArray()      // RECUPERO LOS DATOS ALMACENADOS Y LOS TRANSFORMO PARA EL ADAPTER



        val adapter = ArrayAdapter(
            this,
            R.layout.row,
            R.id.textViewLabel, myPref
        )

        myListVIew.adapter = adapter



        if (nuevaCiudad != null) {

            val editor = prefs.edit()

            editor.putInt(nuevaCiudad, flag)        // ACA ES DONDE GUARDO LA NUEVO CIUDAD


            editor.apply()
            editor.commit()


            adapter.notifyDataSetChanged()


            Toast.makeText(
                applicationContext,
                """CIUDAD GUARDADA : """ + nuevaCiudad,
                Toast.LENGTH_LONG
            )
                .show()


            val a = Intent(this, MainActivity::class.java)       // LA UNICA MANERA QUE ENCONTRE DE ACTUALIZAR LA LISTA - ADAPTER EN "TIEMPO REAL"

            startActivity(a)


        }



        myListVIew.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->


            CIUDAD = parent.getItemAtPosition(position) as String

                                                        // CONSULTA DEL CLIMA
            weatherTask().execute()

        }



        buttonQuitar.setOnClickListener {

            if (myPref.isNotEmpty()) {    // VERIFICO QUE LA LISTA NO ESTE VACIA ANTES DE QUERERLA RESETAR


                val editor = prefs.edit()


                editor.clear()   // ELIMINA TODOS LOS ELEMENTOS ALMACENADOS

                editor.commit()
                editor.apply()

                adapter.notifyDataSetChanged()   // NO HUBO MANERA DE HACERLO ANDAR


                val a = Intent(this, MainActivity::class.java)   // LA UNICA MANERA QUE ENCONTRE DE ACTUALIZAR LA LISTA - ADAPTER EN "TIEMPO REAL"

                startActivity(a)


                Toast.makeText(
                    applicationContext,
                    """DATOS ELIMINADOS""", Toast.LENGTH_SHORT
                )
                    .show()


            } else {
                Toast.makeText(
                    applicationContext,        // SI NO HAY DATOS GUARDADOS AL QUERER BORRAR LA LISTA , MUESTRA ESTE MENSAJE
                    """LISTADO VACIO""", Toast.LENGTH_SHORT
                )
                    .show()
            }

        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        when (id) {

            R.id.opcionAgregar -> abrirActividad()

        }

        return super.onOptionsItemSelected(item)
    }


    private fun abrirActividad() {     // ABRE LA ACTIVIDAD CREADA PARA INGRESAR UNA NUEVA CIUDAD

        val i = Intent(this, NuevaCiudad::class.java)

        startActivity(i)


    }

    inner class weatherTask() : AsyncTask<String, Void, String>() {


        override fun onPreExecute() {


            super.onPreExecute()



        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$CIUDAD&units=metric&appid=$API").readText(
                        Charsets.UTF_8
                    )
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {


                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")


                val temp = main.getString("temp") + "°C"

                Toast.makeText(
                    applicationContext,
                    """TEMPERATURA:  $temp """, Toast.LENGTH_LONG
                )
                    .show()



            } catch (e: Exception) {

            }


        }

    }

}
