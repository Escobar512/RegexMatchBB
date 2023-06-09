package com.example.regexbb

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class verLooker : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_perfil)

        val clickedCard = intent.getSerializableExtra("cardData") as cards
        val nameTextView = findViewById<TextView>(R.id.textName)
        val descriptionTextView = findViewById<TextInputEditText>(R.id.CP_desc)
        val ageTextView = findViewById<TextView>(R.id.CP_edad)
        val degreeTextView = findViewById<TextView>(R.id.CP_degree)
        val schoolTextView = findViewById<TextView>(R.id.CP_School)
        val positionTextView = findViewById<TextView>(R.id.CP_Position)
        val techTextView = findViewById<TextView>(R.id.CP_tech)

        var viewPager = findViewById<ViewPager>(R.id.imageViewPager)

        val images = mutableListOf<String>()

        val cardImages = clickedCard.getImageUrl()

        for (image in cardImages){
            images.add(image.imageUrl)
        }

        var imageAdapter = ImageAdapter(images)
        viewPager.adapter = imageAdapter

        nameTextView.text = clickedCard.getName()
        descriptionTextView.setText(clickedCard.getDescription())
        techTextView.setText("")
        val techs = clickedCard.getTech()
        for (tech in techs){
            techTextView.setText(techTextView.text.toString() + tech.technologyId + ", ")
        }


        ageTextView.text = clickedCard.getAge().toString()
        degreeTextView.text = clickedCard.getDegree()
        schoolTextView.text = clickedCard.getSchool()
        positionTextView.text = clickedCard.getPosition()

    }

}