package com.example.regexbb

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView


import android.widget.Toast
import androidx.room.Room
import com.example.data.database.RegexDatabase
import com.example.regexbb.models.OfferSwipe
import com.example.regexbb.interfaces.offer
import com.example.regexbb.interfaces.offerSwipe
import com.example.regexbb.interfaces.profileImages
import com.example.regexbb.models.Offer
import com.example.regexbb.models.ProfileImages
import com.example.regexbb.retrofit.retrofitClient
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import kotlinx.coroutines.*
import java.util.*
import java.lang.Exception


class MainActivity : Activity() {

    private lateinit var flingContainer: SwipeFlingAdapterView
    private val cardList = mutableListOf<cards>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_perfil = findViewById<ImageView>(R.id.btn_perfil_dev)
        var btn_chat = findViewById<ImageView>(R.id.btn_chat_dev)
        var userId = intent.getStringExtra("userId").toString()
        var userName = intent.getStringExtra("userName").toString()
        btn_perfil.setOnClickListener(){
            set_perfil(userId, userName)
        }
        btn_chat.setOnClickListener(){
            set_chatdev(userId, userName)
        }

        val adapter = cardsAdapter(this@MainActivity, cardList)
        CoroutineScope(Dispatchers.Main).launch {
            var offers = getOfferCards()
            if (offers.isEmpty()) {
                offers = getOfferReCards()
                if (offers.isEmpty()) {
                    return@launch
                }
            }
            Log.d("Resultado", offers[0].name.toString())
            val offerorImages = mutableListOf<List<ProfileImages>>()
            for (offer in offers) {
                val images = getOfferImages(offer.idOfferor)
                offerorImages.add(images)
            }
            withContext(Dispatchers.Main) {
                var i = 0;
                for (offer in offers) {
                    var card = cards(
                        offer.offerId,
                        offerorImages[i][0].imageUrl,
                        offer.name,
                        offer.description
                    )
                    i++
                    cardList.add(card)
                    adapter.notifyDataSetChanged()
                }
            }
        }

        val db = Room.databaseBuilder(
            applicationContext,
            RegexDatabase::class.java, "RegexDB"
        ).build()

        flingContainer = findViewById<SwipeFlingAdapterView>(R.id.frame)

        flingContainer.adapter = adapter

        flingContainer.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {

            var swipe = OfferSwipe()

            override fun removeFirstObjectInAdapter() {
                Log.d("LIST", "removed object!")
                swipe.lookerId = "2"
                swipe.offerId = cardList[0].getUserId()
                cardList.removeAt(0)
                adapter.notifyDataSetChanged()
            }

            override fun onLeftCardExit(dataObject: Any) {
                Toast.makeText(this@MainActivity, "Left", Toast.LENGTH_SHORT).show()
                swipe.swiped = false
                postSwipeFillList(adapter, swipe, cardList)

            }


            override fun onRightCardExit(dataObject: Any) {
                Toast.makeText(this@MainActivity, "Right", Toast.LENGTH_SHORT).show()
                swipe.swiped = true
                CoroutineScope(Dispatchers.Main).launch {
                    postSwipeFillList(adapter, swipe, cardList)

                }
            }

            override fun onAdapterAboutToEmpty(itemsInAdapter: Int) {
//                CoroutineScope(Dispatchers.Main).launch {
//                    var offers = getOfferReCards()
//                    if (offers.isEmpty()) {
//                        return@launch
//                    }
//                    val offerorImages = mutableListOf<List<ProfileImages>>()
//                    for (offer in offers) {
//                        val images = getOfferImages(offer.idOfferor)
//                        offerorImages.add(images)
//                    }
//                    withContext(Dispatchers.Main) {
//                        var i = 0;
//                        for (offer in offers) {
//                            var card = cards(
//                                offer.offerId,
//                                offerorImages[i][0].imageUrl,
//                                offer.name
//                            )
//                            i++
//                            cardList.add(card)
//                            adapter.notifyDataSetChanged()
//                        }
//                    }
//                }
            }

            override fun onScroll(scrollProgressPercent: Float) {
//                val view: View = flingContainer.getSelectedView()
//                view.findViewById<View>(R.id.item_swipe_right_indicator).alpha =
//                    if (scrollProgressPercent < 0) -scrollProgressPercent else 0f
//                view.findViewById<View>(R.id.item_swipe_left_indicator).alpha =
//                    if (scrollProgressPercent > 0) scrollProgressPercent else 0f
            }
        })


        flingContainer.setOnItemClickListener(object : SwipeFlingAdapterView.OnItemClickListener {
            override fun onItemClicked(itemPosition: Int, dataObject: Any) {
                Toast.makeText(this@MainActivity, "Clicked", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun set_perfil(userId: String, userName: String){
        val res = Intent(this, create_perfil::class.java)
        res.putExtra("userId", userId)
        startActivity(res)
    }
    fun set_chatdev(userId: String, userName: String){
        val res = Intent(this, chatList::class.java)
        res.putExtra("userId", userId)
        res.putExtra("userName", userName)
        startActivity(res)
    }


    suspend fun getOfferCards(): List<Offer> {
        var retrofit = retrofitClient.getInstance()
        var userInterface = retrofit.create(offer::class.java)
        try {
            var response = userInterface.getOffersMatched("2")
            var offers = response.body()
            return offers ?: emptyList() // Return the users if the response is not null, otherwise return an empty list
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            return emptyList() // Return an empty list in case of an exception
        }
    }

    fun postSwipeFillList(adapter: cardsAdapter, swipe: OfferSwipe, list: List<cards>) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = CompletableDeferred<Unit>()

            launch(Dispatchers.IO) {
                val success = performOfferSwipe(swipe)
                if (success != null) {
                    deferred.complete(Unit)
                } else {
                    deferred.cancel()
                }
            }

            deferred.await()
            if(list.isEmpty()){
                val offers = getOfferReCards()
                if (offers.isEmpty()) {
                    return@launch
                }

                Log.d("Resultado", offers[0].name.toString())

                val offerorImages = mutableListOf<List<ProfileImages>>()
                for (offer in offers) {
                    val images = getOfferImages(offer.idOfferor)
                    offerorImages.add(images)
                }

                withContext(Dispatchers.Main) {
                    var i = 0
                    for (offer in offers) {
                        val card = cards(
                            offer.offerId,
                            offerorImages[i][0].imageUrl,
                            offer.name,
                            offer.description
                        )
                        i++
                        cardList.add(card)
                    }
                    adapter.notifyDataSetChanged()
                }
            }


        }
    }

    suspend fun getOfferReCards(): List<Offer> {
        var retrofit = retrofitClient.getInstance()
        var userInterface = retrofit.create(offer::class.java)
        try {
            var response = userInterface.getOffersReMatched("1")
            var offers = response.body()
            return offers ?: emptyList() // Return the users if the response is not null, otherwise return an empty list
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            return emptyList() // Return an empty list in case of an exception
        }
    }

    suspend fun getOfferImages(id :String): List<ProfileImages> {
        var retrofit = retrofitClient.getInstance()
        var imagesInference = retrofit.create(profileImages::class.java)
        try {
            var response = imagesInference.getProfileImagesOfferor(id)
            var images = response.body()
            return images ?: emptyList() // Return the users if the response is not null, otherwise return an empty list
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            return emptyList() // Return an empty list in case of an exception
        }


    }

    suspend fun performOfferSwipe(swipe : OfferSwipe): OfferSwipe? {
        var retrofit = retrofitClient.getInstance()
        var offerSwipeInterface = retrofit.create(offerSwipe::class.java)

        try {
            val response = offerSwipeInterface.createOfferSwipe(swipe)
            if (response.isSuccessful) {
                var swipeR = response.body()
                return swipeR
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