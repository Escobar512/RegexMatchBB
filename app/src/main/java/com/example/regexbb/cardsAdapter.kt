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

    private var clicked = 0

    private val boundViews = mutableMapOf<Int, View>() // Store the bound views and their positions

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item, parent, false)

        // Set up the card view with data from the Card object at the given position
        val card = getItem(position)
        view.findViewById<TextView>(R.id.name).text = card.getName()

        val descriptionTextView = view.findViewById<TextView>(R.id.description)

        if (clicked == 1) {
            descriptionTextView.visibility = View.VISIBLE
            descriptionTextView.text = card.getDescription()
        } else {
            descriptionTextView.visibility = View.GONE
        }




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

    fun updateCardLayout(position: Int, newProperty: Boolean) {
        val view = boundViews[position]
        val card = getItem(position)
        val descriptionTextView = view?.findViewById<TextView>(R.id.description)
        if (clicked == 1) {
            descriptionTextView?.visibility = View.VISIBLE
            descriptionTextView?.text = card.getDescription()
        } else {
            descriptionTextView?.visibility = View.GONE
        }
        val clickedCard = cardList[position]
        clickedCard.setClickedCard(true)
        notifyDataSetChanged()
    }

    fun getClicked(): Int {
        return clicked
    }

    fun setClicked(clicked: Int) {
        this.clicked = clicked
    }


    override fun getCount(): Int {
        return cardList.size
    }
}