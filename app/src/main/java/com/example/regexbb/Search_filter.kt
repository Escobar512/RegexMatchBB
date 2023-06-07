package com.example.regexbb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class Search_filter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_filter)

        val spinner = findViewById<Spinner>(R.id.search_mod)
        val items = arrayOf("Presencial", "En linea", "Hibirda")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val spinner2 = findViewById<Spinner>(R.id.search_horario)
        val items2 = arrayOf("Tiempo Completo", "Medio Tiempo")
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items2)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = adapter2

        val button_search = findViewById<Button>(R.id.btn_apply_filters)
        button_search.setOnClickListener { view ->
            search(view)
        }
    }

    fun search(view: View){

        val pay = findViewById<SeekBar>(R.id.search_sueldo)
        val mod = findViewById<Spinner>(R.id.search_mod)
        val horario = findViewById<Spinner>(R.id.search_horario)

        val cpp = findViewById<CheckBox>(R.id.search_cpp)
        val cs = findViewById<CheckBox>(R.id.search_cs)
        val ang = findViewById<CheckBox>(R.id.search_angular)
        val andr = findViewById<CheckBox>(R.id.search_and)
        val dock = findViewById<CheckBox>(R.id.search_docker)
        val py = findViewById<CheckBox>(R.id.seacrh_py)
        val java = findViewById<CheckBox>(R.id.search_java)
        val net = findViewById<CheckBox>(R.id.search_net)
        val node = findViewById<CheckBox>(R.id.search_node)
        val spring = findViewById<CheckBox>(R.id.search_spring)
        val sql = findViewById<CheckBox>(R.id.search_sql)
        val type = findViewById<CheckBox>(R.id.search_type)

        //Funcion buscar
    }
}