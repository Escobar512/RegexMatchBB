package com.example.regexbb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button_log = findViewById<Button>(R.id.Loginbtn)
        button_log.setOnClickListener { view ->
            login_click(view)
        }
        val button_reg = findViewById<Button>(R.id.registerbtn)
        button_reg.setOnClickListener { view ->
            Registrar(view)
        }
    }

    fun login_click(view: View){
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.user_password)

        //Funcion para logear
    }

    fun Registrar(view: View){

        val intent = Intent(this, register::class.java)
        startActivity(intent);
    }

}