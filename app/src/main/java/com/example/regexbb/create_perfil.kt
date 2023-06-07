package com.example.regexbb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class create_perfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_perfil)
        val button_create = findViewById<Button>(R.id.button_publicar)
        button_create.setOnClickListener { view ->
            publicar(view)
        }
    }

    fun publicar(view: View){
        val puesto = findViewById<EditText>(R.id.CP_puesto_d)
        val edad = findViewById<EditText>(R.id.CP_edad)
        val resumen = findViewById<EditText>(R.id.CP_resume)
        val resumen_t = findViewById<EditText>(R.id.CP_text_resume)

        val cpp = findViewById<CheckBox>(R.id.cb_CPP)
        val cs = findViewById<CheckBox>(R.id.cb_CS)
        val ang = findViewById<CheckBox>(R.id.cb_ANG)
        val andr = findViewById<CheckBox>(R.id.cb_ANDR)
        val dock = findViewById<CheckBox>(R.id.cb_DOCKER)
        val py = findViewById<CheckBox>(R.id.cb_PY)
        val java = findViewById<CheckBox>(R.id.cb_JAVA)
        val net = findViewById<CheckBox>(R.id.cb_NET)
        val node = findViewById<CheckBox>(R.id.cb_NODE)
        val spring = findViewById<CheckBox>(R.id.cb_SPRING)
        val sql = findViewById<CheckBox>(R.id.cb_SQL)
        val type = findViewById<CheckBox>(R.id.cb_TYPE)

        //Funcion para publicar
    }


}