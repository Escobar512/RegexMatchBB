package com.example.regexbb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.regexbb.interfaces.objectTechnologies
import com.example.regexbb.models.ObjectTechnologies
import com.example.regexbb.models.LookingProfile
import android.view.View
import android.widget.*
import com.example.regexbb.retrofit.retrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class create_perfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_perfil)

        var userId = intent.getStringExtra("userId").toString()
        var userName = intent.getStringExtra("userName").toString()
        val button_create = findViewById<Button>(R.id.btn_publicar)

        val modalidad = findViewById<Spinner>(R.id.CP_modalidad)
        var itemsM = arrayOf("Presencial", "En linea", "Hibirda")
        var adapterM = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemsM)
        adapterM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        modalidad.adapter = adapterM

        val horario = findViewById<Spinner>(R.id.CP_horario)
        val itemsH = arrayOf("Tiempo Completo", "Medio Tiempo")
        val adapterH = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemsH)
        adapterH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        horario.adapter = adapterH

        button_create.setOnClickListener { view ->
            publicar(view, userId, userName)
        }
    }

    fun publicar(view: View, userId: String, userName: String){
        val puesto = findViewById<EditText>(R.id.CP_puesto_d).text.toString()
        val edad = findViewById<EditText>(R.id.CP_edad).text.toString()
        val resumen = findViewById<EditText>(R.id.CP_resume).text.toString()
        //val resumen_t = findViewById<EditText>(R.id.CP_text_resume).text.toString()
        val escolaridad = findViewById<EditText>(R.id.CP_escolaridad).text.toString()
        val pay = findViewById<EditText>(R.id.CP_pay).text.toString()
        val escuela = findViewById<EditText>(R.id.CP_escuela).text.toString()
        val horario = findViewById<Spinner>(R.id.CP_horario).selectedItem.toString()
        val mod = findViewById<Spinner>(R.id.CP_modalidad).selectedItem.toString()

        //Funcion para publicar
        val looker = LookingProfile()

        looker.profileId = UUID.randomUUID().toString()
        looker.age = edad.toInt()
        looker.position = puesto
        looker.degree = escolaridad
        looker.school = escuela
        looker.description = resumen
        looker.userId = userId
        looker.name = userName

        var anyTech = false
        var technologies = mutableListOf<String>()
        val cpp = findViewById<CheckBox>(R.id.cb_CPP).isChecked
        if(cpp){
            technologies.add("cpp")
            anyTech = true
        }
        val cs = findViewById<CheckBox>(R.id.cb_CS).isChecked
        if(cs){
            technologies.add("cs")
            anyTech = true
        }
        val ang = findViewById<CheckBox>(R.id.cb_ANG).isChecked
        if(ang){
            technologies.add("ang")
            anyTech = true
        }
        val andr = findViewById<CheckBox>(R.id.cb_ANDR).isChecked
        if(andr){
            technologies.add("andr")
            anyTech = true
        }
        val dock = findViewById<CheckBox>(R.id.cb_DOCKER).isChecked
        if(dock){
            technologies.add("dock")
            anyTech = true
        }
        val py = findViewById<CheckBox>(R.id.cb_PY).isChecked
        if(py){
            technologies.add("py")
            anyTech = true
        }
        val java = findViewById<CheckBox>(R.id.cb_JAVA).isChecked
        if(java){
            technologies.add("java")
            anyTech = true
        }
        val net = findViewById<CheckBox>(R.id.cb_NET).isChecked
        if(net){
            technologies.add("net")
            anyTech = true
        }
        val node = findViewById<CheckBox>(R.id.cb_NODE).isChecked
        if(node){
            technologies.add("node")
            anyTech = true
        }
        val spring = findViewById<CheckBox>(R.id.cb_SPRING).isChecked
        if(spring){
            technologies.add("spring")
            anyTech = true
        }
        val sql = findViewById<CheckBox>(R.id.cb_SQL).isChecked
        if(sql){
            technologies.add("sql")
            anyTech = true
        }
        val type = findViewById<CheckBox>(R.id.cb_TYPE).isChecked
        if(type){
            technologies.add("type")
            anyTech = true
        }
        if (!anyTech){
            Toast.makeText(this, "Selecciona al menos 1 tecnologia", Toast.LENGTH_SHORT).show()
            return
        }
        publicarTechs(technologies,  looker.profileId)
        CoroutineScope(Dispatchers.Main).launch {
            postPerfil(looker)
            withContext(Dispatchers.Main){
                Toast.makeText(this@create_perfil, "Perfil Publicado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    suspend fun postPerfil(looker: LookingProfile): LookingProfile?{
        var retrofit = retrofitClient.getInstance()
        var lookerInterface = retrofit.create(com.example.regexbb.interfaces.lookingProfiles::class.java)

        try {
            val response = lookerInterface.createLookingProfile(looker)
            if (response.isSuccessful) {
                var offerR = response.body()
                return offerR
            } else {
                Log.d("error", response.errorBody().toString())
            }
        } catch (e: Exception) {
            Log.d("error", e.message.toString())

            return null
        }
        return null
    }

    fun publicarTechs(techs : List<String>, profileId: String){
        CoroutineScope(Dispatchers.Main).launch {
            for(tech in techs){
                var techP = ObjectTechnologies()
                techP.dObjectId = profileId
                techP.isLooking = true
                techP.technologyId = tech
                postTechnologyffer(techP)
            }
        }
    }

    suspend fun postTechnologyffer(objTech : ObjectTechnologies): ObjectTechnologies? {
        var retrofit = retrofitClient.getInstance()
        var objectTechnologiesInterface = retrofit.create(objectTechnologies::class.java)

        try {
            val response = objectTechnologiesInterface.createObjectTechnologies(objTech)
            if (response.isSuccessful) {
                var objTechR = response.body()
                return objTechR
            } else {
                Log.d("error", response.errorBody().toString())
            }
        } catch (e: Exception) {
            Log.d("error", e.message.toString())

            return null
        }
        return null
    }


}