package com.example.regexbb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        val btn_perfil = findViewById<Button>(R.id.btn_perfil_dev)
        val btn_chat = findViewById<Button>(R.id.btn_cardsmain)
        val btn_add = findViewById<Button>(R.id.btn_add)

        val userId = intent.getStringExtra("userId").toString()
        val userName = intent.getStringExtra("userName").toString()

        btn_perfil.setOnClickListener() {
            view_perfil(userId, userName)
        }
        btn_add.setOnClickListener() { view ->
            view_add(userId, userName)
        }
        btn_chat.setOnClickListener() { view ->
            view_chat(userId, userName)
        }
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

    fun view_perfil(userId: String, userName: String){
        val intent = Intent(this, EdtiPerfil_EM::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }
    fun view_chat(userId: String, userName: String){
        val intent = Intent(this, chatList::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }
    fun view_add(userId: String, userName: String){
        val intent = Intent(this, create_vac::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
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