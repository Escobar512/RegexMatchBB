package com.example.regexbb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OfferAdapter(
    private val offers: List<OfferRecycler>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(offerId: String)
    }

    inner class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.offer_name)
        val descriptionView: TextView = itemView.findViewById(R.id.offer_description)
        val offerIdView: TextView = itemView.findViewById(R.id.offer_id)
        var currentPosition: Int = -1

        init {
            itemView.setOnClickListener {
                if (currentPosition != -1) {
                    val offer = offers[currentPosition]
                    listener.onItemClick(offer.offerId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.offer_item, parent, false)
        return OfferViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = offers[position]
        holder.nameView.text = offer.name
        holder.descriptionView.text = offer.description
        holder.currentPosition = position
        holder.offerIdView.text = offer.offerId
    }

    override fun getItemCount(): Int {
        return offers.size
    }
}