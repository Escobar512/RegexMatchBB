package com.example.regexbb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.regexbb.interfaces.objectTechnologies
import com.example.regexbb.interfaces.offer
import com.example.regexbb.interfaces.offerSwipe
import com.example.regexbb.models.ObjectTechnologies
import com.example.regexbb.models.Offer
import com.example.regexbb.models.OfferSwipe
import com.example.regexbb.models.ProfileImages
import com.example.regexbb.retrofit.retrofitClient
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.*
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class create_vac : AppCompatActivity() {
    private val commonNamesList: ArrayList<String> = ArrayList()
    val countries = arrayOf(
        "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
        "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia",
        "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus",
        "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina",
        "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory",
        "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia",
        "Cameroon", "Canada", "Cape Verde", "Caribbean Netherlands", "Cayman Islands",
        "Central African Republic", "Chad", "Chile", "China", "Christmas Island",
        "Cocos (Keeling) Islands", "Colombia", "Comoros", "Cook Islands", "Costa Rica",
        "Croatia", "Cuba", "Curaçao", "Cyprus", "Czechia", "DR Congo", "Denmark", "Djibouti",
        "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador",
        "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Falkland Islands",
        "Faroe Islands", "Fiji", "Finland", "France", "French Guiana", "French Polynesia",
        "French Southern and Antarctic Lands", "Gabon", "Gambia", "Georgia", "Germany",
        "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam",
        "Guatemala", "Guernsey", "Guinea", "Guinea-Bissau", "Guyana", "Haiti",
        "Heard Island and McDonald Islands", "Honduras", "Hong Kong", "Hungary", "Iceland",
        "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy",
        "Ivory Coast", "Jamaica", "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya",
        "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho",
        "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Madagascar",
        "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique",
        "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova", "Monaco",
        "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia",
        "Nauru", "Nepal", "Netherlands", "New Caledonia", "New Zealand", "Nicaragua", "Niger",
        "Nigeria", "Niue", "Norfolk Island", "North Korea", "North Macedonia",
        "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama",
        "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn Islands", "Poland",
        "Portugal", "Puerto Rico", "Qatar", "Republic of the Congo", "Romania", "Russia", "Rwanda",
        "Réunion", "Saint Barthélemy", "Saint Helena, Ascension and Tristan da Cunha",
        "Saint Kitts and Nevis", "Saint Lucia", "Saint Martin", "Saint Pierre and Miquelon",
        "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Saudi Arabia", "Senegal",
        "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Sint Maarten", "Slovakia", "Slovenia",
        "Solomon Islands", "Somalia", "South Africa", "South Georgia", "South Korea", "South Sudan",
        "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard and Jan Mayen", "Sweden", "Switzerland",
        "Syria", "São Tomé and Príncipe", "Taiwan", "Tajikistan", "Tanzania", "Thailand",
        "Timor-Leste", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey",
        "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine",
        "United Arab Emirates", "United Kingdom", "United States",
        "United States Minor Outlying Islands", "United States Virgin Islands", "Uruguay",
        "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Wallis and Futuna",
        "Western Sahara", "Yemen", "Zambia", "Zimbabwe", "Åland Islands"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_vac)
         val button_create = findViewById<Button>(R.id.button_publicar)

        val modalidad = findViewById<Spinner>(R.id.CV_modalidad)
        var itemsM = arrayOf("Presencial", "En linea", "Hibirda")
        var adapterM = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemsM)
        adapterM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        modalidad.adapter = adapterM

        val horario = findViewById<Spinner>(R.id.CV_horario)
        val itemsH = arrayOf("Tiempo Completo", "Medio Tiempo")
        val adapterH = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemsH)
        adapterH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        horario.adapter = adapterH

        val lugar = findViewById<Spinner>(R.id.CV_Lugar)
        val adapterL = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        adapterL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        lugar.adapter = adapterL


        button_create.setOnClickListener { view ->
            publicar(view, button_create)
        }
    }

    fun publicar(view: View, button: Button){
        button.isEnabled = false;
        val titulo = findViewById<EditText>(R.id.CV_titulo).text.toString()
        if (titulo.isEmpty() || titulo == ""){
            Toast.makeText(this, "Agregue un titulo", Toast.LENGTH_SHORT).show()
            return
        }
        val pay = findViewById<EditText>(R.id.CV_sueldo).text.toString()
        if (pay.isEmpty() || pay == ""){
            Toast.makeText(this, "Agregue un sueldo", Toast.LENGTH_SHORT).show()
            return
        }
        val description_t = findViewById<TextInputEditText>(R.id.CV_description_text).text.toString()
        if (description_t.isEmpty() || description_t == ""){
            Toast.makeText(this, "Agregue una descripcion", Toast.LENGTH_SHORT).show()
            return
        }
        val lugar = findViewById<Spinner>(R.id.CV_Lugar).selectedItem.toString()
        val mod = findViewById<Spinner>(R.id.CV_modalidad).selectedItem.toString()
        val horario = findViewById<Spinner>(R.id.CV_horario).selectedItem.toString()

        var offer = Offer()


        offer.offerId = UUID.randomUUID().toString()
        offer.name = titulo
        offer.pay = pay.toDouble()
        offer.country = lugar
        offer.description = description_t
        offer.mode = mod
        offer.schedule = horario
        offer.idOfferor = "2"

        var anyTech = false
        var technologies = mutableListOf<String>()
        val cpp = findViewById<CheckBox>(R.id.lang_cpp).isChecked
        if(cpp){
            technologies.add("cpp")
            anyTech = true
        }
        val cs = findViewById<CheckBox>(R.id.lang_cs).isChecked
        if(cs){
            technologies.add("cs")
            anyTech = true
        }
        val ang = findViewById<CheckBox>(R.id.lang_ang).isChecked
        if(ang){
            technologies.add("ang")
            anyTech = true
        }
        val andr = findViewById<CheckBox>(R.id.lang_and).isChecked
        if(andr){
            technologies.add("andr")
            anyTech = true
        }
        val dock = findViewById<CheckBox>(R.id.lang_dock).isChecked
        if(dock){
            technologies.add("dock")
            anyTech = true
        }
        val py = findViewById<CheckBox>(R.id.lang_py).isChecked
        if(py){
            technologies.add("py")
            anyTech = true
        }
        val java = findViewById<CheckBox>(R.id.lang_java).isChecked
        if(java){
            technologies.add("java")
            anyTech = true
        }
        val net = findViewById<CheckBox>(R.id.lang_net).isChecked
        if(net){
            technologies.add("net")
            anyTech = true
        }
        val node = findViewById<CheckBox>(R.id.lang_node).isChecked
        if(node){
            technologies.add("node")
            anyTech = true
        }
        val spring = findViewById<CheckBox>(R.id.lang_spring).isChecked
        if(spring){
            technologies.add("spring")
            anyTech = true
        }
        val sql = findViewById<CheckBox>(R.id.lang_sql).isChecked
        if(sql){
            technologies.add("sql")
            anyTech = true
        }
        val type = findViewById<CheckBox>(R.id.lang_type).isChecked
        if(type){
            technologies.add("type")
            anyTech = true
        }

        if (!anyTech){
            Toast.makeText(this, "Selecciona al menos 1 tecnologia", Toast.LENGTH_SHORT).show()
            return
        }

        publicarTechs(technologies,  offer.offerId)
        CoroutineScope(Dispatchers.Main).launch {
            postOffer(offer)
            withContext(Dispatchers.Main){
                Toast.makeText(this@create_vac, "Offerta Publicada", Toast.LENGTH_SHORT).show()
            }
        }


    }

    fun publicarTechs(techs : List<String>, offerId: String){
        CoroutineScope(Dispatchers.Main).launch {
            for(tech in techs){
                var techP = ObjectTechnologies()
                techP.dObjectId = offerId
                techP.isLooking = false
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


    suspend fun postOffer(offer : Offer): Offer? {
        var retrofit = retrofitClient.getInstance()
        var offerInterface = retrofit.create(com.example.regexbb.interfaces.offer::class.java)

        try {
            val response = offerInterface.createOffer(offer)
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

}




