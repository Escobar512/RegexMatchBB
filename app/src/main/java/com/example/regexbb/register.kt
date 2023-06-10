package com.example.regexbb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.regexbb.models.User
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.regexbb.retrofit.retrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val button_reg = findViewById<Button>(R.id.btn_crearperfil)
        button_reg.setOnClickListener { view ->
            registrar(view)
        }
    }

    fun registrar(view: View){
        val nombre = findViewById<EditText>(R.id.empresa_name)
        val email = findViewById<EditText>(R.id.user_register_email)
        val pass = findViewById<EditText>(R.id.user_register_pass)
        var isLooking = false
        val radioGroup_register = findViewById<RadioGroup>(R.id.radio_type_register)
        val selected = findViewById<RadioButton>(radioGroup_register.checkedRadioButtonId)

        val op = selected.text.toString()
        if (op == "Desarrollador")
        {
            isLooking = true
        }
        else{
            isLooking = false
        }

        var User = User()
        User.email = email.text.toString()
        User.userName = nombre.text.toString()
        User.password = pass.text.toString()
        User.isLooking = isLooking
        //Funcion de Registrar
        CoroutineScope(Dispatchers.Main).launch{
            postregistrar(User)
        }
    }

    suspend fun postregistrar(Nuser: User)
    {
        var retrofit = retrofitClient.getInstance()
        var userinterface = retrofit.create(com.example.regexbb.interfaces.user::class.java)

        try{
            val res = userinterface.createUser(Nuser)
            if (res.isSuccessful)
            {
                val res = Intent(this, Login::class.java)
                startActivity(res)
            }
        }catch (e: Exception){

        }

    }
}