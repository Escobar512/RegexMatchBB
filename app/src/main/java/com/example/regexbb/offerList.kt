package com.example.regexbb

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.regexbb.interfaces.chat
import com.example.regexbb.interfaces.offer
import com.example.regexbb.models.Offer
import com.example.regexbb.retrofit.retrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class offerList : AppCompatActivity(), OfferAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var offerAdapter: OfferAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.offer_list)
        val offers = mutableListOf<OfferRecycler>()
        CoroutineScope(Dispatchers.Main).launch {
           val offersFull = getOffers()
            withContext(Dispatchers.Main) {
                for (offer in offersFull){
                    val offerDummy = OfferRecycler(offer.name, offer.description, offer.offerId)
                    offers.add(offerDummy)
                    offerAdapter.notifyDataSetChanged()
                }
            }
        }

        recyclerView = findViewById(R.id.offer_list) // Replace `recyclerView` with the ID of your RecyclerView in the activity layout
        recyclerView.layoutManager = LinearLayoutManager(this) // LinearLayoutManager displays items in a vertical list
        offerAdapter = OfferAdapter(offers, this) // Replace `chats` with your list of Chat objects
        recyclerView.adapter = offerAdapter

    }

    suspend fun getOffers(): List<Offer> {
        var retrofit = retrofitClient.getInstance()
        var offerInstance = retrofit.create(offer::class.java)
        try {
            var response = offerInstance.getOffersByOfferor("2")
            var offers = response.body()
            return offers ?: emptyList() // Return the users if the response is not null, otherwise return an empty list
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            return emptyList() // Return an empty list in case of an exception
        }
    }

    override fun onItemClick(offerId: String) {
//        val intent = Intent(this, DestinationActivity::class.java)
//        intent.putExtra("offerName", offerName)
//        startActivity(intent)
        Toast.makeText(this, offerId, LENGTH_SHORT).show()
    }


}