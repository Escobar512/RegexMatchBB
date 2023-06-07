package com.example.regexbb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val button_reg = findViewById<Button>(R.id.button_registrar)
        button_reg.setOnClickListener { view ->
            registrar(view)
        }
    }

    fun registrar(view: View){
        val nombre = findViewById<EditText>(R.id.user_register_name)
        val email = findViewById<EditText>(R.id.user_register_email)
        val pass = findViewById<EditText>(R.id.user_register_pass)

        val radioGroup_register = findViewById<RadioGroup>(R.id.radio_type_register)
        val selected = findViewById<RadioButton>(radioGroup_register.checkedRadioButtonId)

        //Funcion de Registrar
    }
}