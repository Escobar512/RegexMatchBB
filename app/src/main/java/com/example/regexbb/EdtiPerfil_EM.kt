package com.example.regexbb

import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.regexbb.interfaces.offeringProfile
import com.example.regexbb.models.OfferingProfile
import com.example.regexbb.interfaces.profileImages
import com.example.regexbb.models.ProfileImages
import android.widget.ImageView
import android.net.Uri
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.regexbb.retrofit.retrofitClient
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URI
import java.util.*

class EdtiPerfil_EM : AppCompatActivity() {
    private val PICK_IMAGES_REQUEST_CODE = 1

    var userId = ""
    val lista = mutableListOf<String>()
    var image = String()

    var multiple = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edti_perfil_em)

        val addimg = findViewById<Button>(R.id.btn_addimg)
        val create = findViewById<Button>(R.id.btn_crearperfil)
        val textviewdescription = findViewById<EditText>(R.id.off_description)
        val description = textviewdescription.text.toString()

        userId = intent.getStringExtra("userId").toString()

        addimg.setOnClickListener(){
            abir_galeria()
        }

        create.setOnClickListener(){ view ->
            CoroutineScope(Dispatchers.Main).launch{
                create(view, description)
            }
        }
    }

    suspend fun create(view: View, description: String){
        var retrofit = retrofitClient.getInstance()
        var offeringProfileinterface = retrofit.create(offeringProfile::class.java)
        var profileImagesinterface = retrofit.create(profileImages::class.java)

        try {
            var offer = OfferingProfile()
            offer.description = description
            offer.userId = userId
            offer.profileId = "15"//UUID.randomUUID().toString()
            val response = offeringProfileinterface.createOfferingProfile(offer)
            if (response.isSuccessful){
                var profile = response.body()
                if (multiple)
                {
                    for (i in 0 until lista.size) {
                        var profileImages = ProfileImages()
                        profileImages.imageUrl = lista[i]
                        profileImages.isLooker = false
                        profileImages.profileId = profile!!.profileId
                        profileImagesinterface.createProfileImage(profileImages)
                    }
                }
                else{
                    var profileImages = ProfileImages()
                    profileImages.imageUrl = image
                    profileImages.isLooker = false
                    profileImages.profileId = profile!!.profileId
                    profileImagesinterface.createProfileImage(profileImages)
                }
            }
        }catch (e: Exception){

        }
    }

    fun abir_galeria(){
        val intent = Intent()
        intent.setType("image/*")
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent,"SeleccionaImagenes"),1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            val clip = data?.clipData
        if (requestCode == PICK_IMAGES_REQUEST_CODE && resultCode == RESULT_OK){
            if (data?.clipData != null){
                for (i in 0 until  data.clipData!!.itemCount){
                    lista.add(clip?.getItemAt(i).toString())
                }
                multiple = true
            }
            else if (data?.data != null){
                image = data?.data.toString()
                multiple = false
            }
        }
    }
}