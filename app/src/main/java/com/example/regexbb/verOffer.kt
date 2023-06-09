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

class verOffer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_offer)

        val clickedCard = intent.getSerializableExtra("cardData") as cardsOffer
        val nameTextView = findViewById<TextView>(R.id.textName)
        val descriptionTextView = findViewById<TextInputEditText>(R.id.CP_desc)
        val payTextView = findViewById<TextView>(R.id.CP_paga)
        val hoursTextView = findViewById<TextView>(R.id.CP_horas)
        val modeTextView = findViewById<TextView>(R.id.CP_modalidad)
        val techTextView = findViewById<TextView>(R.id.CP_tech)

        val viewPager = findViewById<ViewPager>(R.id.imageViewPager)

        val images = mutableListOf<String>()

        val cardImages = clickedCard.getImageUrl()

        for (image in cardImages) {
            images.add(image.imageUrl)
        }

        val imageAdapter = ImageAdapter(images)
        viewPager.adapter = imageAdapter

        nameTextView.text = clickedCard.getName()
        descriptionTextView.setText(clickedCard.getDescription())
        payTextView.text = clickedCard.getPay().toString()
        hoursTextView.text = clickedCard.getSchedule()
        modeTextView.text = clickedCard.getMode()
        techTextView.text = ""

        val techs = clickedCard.getTech()
        for (tech in techs) {
            techTextView.text = techTextView.text.toString() + tech.technologyId + ", "
        }
    }
}