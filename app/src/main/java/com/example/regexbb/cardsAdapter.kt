package com.example.regexbb

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.regexbb.R

class cardsAdapter(private val context: Context, private val cardList: List<cards>) : BaseAdapter() {

    private val boundViews = mutableMapOf<Int, View>() // Store the bound views and their positions

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item, parent, false)

        // Set up the card view with data from the Card object at the given position
        val card = getItem(position)
        view.findViewById<TextView>(R.id.name).text = card.getName()

        val descriptionTextView = view.findViewById<TextView>(R.id.description)

        descriptionTextView.text = card.getDescription()



        Glide.with(context)
            .load(card.getImageUrl())
            .into(view.findViewById<ImageView>(R.id.image))

        boundViews[position] = view
        return view
    }

    override fun getItem(position: Int): cards {
        return cardList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return cardList.size
    }
}