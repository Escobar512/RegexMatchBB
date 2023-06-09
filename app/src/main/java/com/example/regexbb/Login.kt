package com.example.regexbb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.regexbb.interfaces.user
import com.example.regexbb.models.User
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.regexbb.retrofit.retrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

        CoroutineScope(Dispatchers.Main).launch{
            val user = login(username = username.text.toString(), password = password.text.toString())
        }

    }

    fun Registrar(view: View){

        val intent = Intent(this, register::class.java)
        startActivity(intent);
    }

    suspend fun login(username: String, password: String){
        var retrofit = retrofitClient.getInstance()
        var userinterface = retrofit.create(com.example.regexbb.interfaces.user::class.java)

        try {
            val response = userinterface.getLogin(username, password)
            if (response.isSuccessful) {
                var userR = response.body()
                if (userR != null)
                {
                    if (userR.isLooking){
                        val res = Intent(this, MainActivity::class.java)
                        startActivity(res)
                    }
                    else{
                        val res = Intent(this, offerList::class.java)
                        startActivity(res)
                    }
                }
                else{
                    Toast.makeText(this, "Usuario No encontrado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Log.d("error", response.errorBody().toString())
            }
        }catch (e: Exception){
            Log.d("error", e.message.toString())
        }
    }

}
