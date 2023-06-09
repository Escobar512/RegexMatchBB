package com.example.regexbb

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.data.database.RegexDatabase
import com.example.regexbb.interfaces.*
import com.example.regexbb.models.*
import com.example.regexbb.retrofit.retrofitClient
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import kotlinx.coroutines.*
import java.lang.Exception

class LookerCards : Activity() {

    private lateinit var flingContainer: SwipeFlingAdapterView
    private val cardList = mutableListOf<cards>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.looker_cards)

        val adapter = cardsAdapter(this@LookerCards, cardList)
        CoroutineScope(Dispatchers.Main).launch {
            var lookers = getLookerCards()
                if (lookers.isEmpty()) {
                    lookers = getLookerReCards()
                    if (lookers.isEmpty()) {
                        return@launch
                    }
                }
            val lookerImages = mutableListOf<List<ProfileImages>>()
            val lookerTechs = mutableListOf<List<ObjectTechnologies>>()
            for (looker in lookers) {
                val images = getLookerImages(looker.profileId)
                lookerImages.add(images)
                val techs = getTechsObject(looker.profileId)
                lookerTechs.add(techs)
            }
            withContext(Dispatchers.Main) {
                var i = 0;
                for (looker in lookers) {
                    var card = cards(
                        looker.profileId,
                        lookerImages[i],
                        looker.name,
                        looker.description,
                        looker.age,
                        looker.degree,
                        looker.school,
                        looker.position,
                        lookerTechs[i]
                    )
                    i++
                    cardList.add(card)
                    adapter.notifyDataSetChanged()
                }
            }
        }


        flingContainer = findViewById<SwipeFlingAdapterView>(R.id.frame)

        flingContainer.adapter = adapter

        flingContainer.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {

            var swipe = OfferSwipe()

            override fun removeFirstObjectInAdapter() {
                Log.d("LIST", "removed object!")
                swipe.offerId = "2"
                swipe.lookerId = cardList[0].getUserId()
                cardList.removeAt(0)
                adapter.notifyDataSetChanged()
            }

            override fun onLeftCardExit(dataObject: Any) {
                Toast.makeText(this@LookerCards, "Left", Toast.LENGTH_SHORT).show()
                swipe.swiped = false
                postSwipeFillList(adapter, swipe, cardList)

            }


            override fun onRightCardExit(dataObject: Any) {
                Toast.makeText(this@LookerCards, "Right", Toast.LENGTH_SHORT).show()
                swipe.swiped = true
                CoroutineScope(Dispatchers.Main).launch {
                    postSwipeFillList(adapter, swipe, cardList)

                }
            }

            override fun onAdapterAboutToEmpty(itemsInAdapter: Int) {
            }

            override fun onScroll(scrollProgressPercent: Float) {
            }
        })


        flingContainer.setOnItemClickListener(object : SwipeFlingAdapterView.OnItemClickListener {
            override fun onItemClicked(itemPosition: Int, dataObject: Any) {
                val clickedCard = cardList[itemPosition]
                val intent = Intent(this@LookerCards, verLooker::class.java)
                intent.putExtra("cardData", clickedCard)
                startActivity(intent)
            }
        })


    }

    suspend fun getLookerCards(): List<LookingProfile> {
        var retrofit = retrofitClient.getInstance()
        var lookerInterface = retrofit.create(lookingProfiles::class.java)
        try {
            var response = lookerInterface.getLookersMatched("b168191a-49ae-4997-8175-4220462e55d1")
            var lookers = response.body()
            return lookers ?: emptyList() // Return the users if the response is not null, otherwise return an empty list
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
                val lookers = getLookerReCards()
                if (lookers.isEmpty()) {
                    return@launch
                }

                Log.d("Resultado", lookers[0].name.toString())

                val lookerImages = mutableListOf<List<ProfileImages>>()
                val lookerTechs = mutableListOf<List<ObjectTechnologies>>()
                for (looker in lookers) {
                    val images = getLookerImages(looker.profileId)
                    lookerImages.add(images)
                    val techs = getTechsObject(looker.profileId)
                    lookerTechs.add(techs)
                }

                withContext(Dispatchers.Main) {
                    var i = 0
                    for (looker in lookers) {
                        val card = cards(
                            looker.profileId,
                            lookerImages[i],
                            looker.name,
                            looker.description,
                            looker.age,
                            looker.degree,
                            looker.school,
                            looker.position,
                            lookerTechs[i]
                        )
                        i++
                        cardList.add(card)
                    }
                    adapter.notifyDataSetChanged()
                }
            }


        }
    }

    suspend fun getLookerReCards(): List<LookingProfile> {
        var retrofit = retrofitClient.getInstance()
        var lookerInterface = retrofit.create(lookingProfiles::class.java)
        try {
            var response = lookerInterface.getLookersMatched("b168191a-49ae-4997-8175-4220462e55d1")
            var lookers = response.body()
            return lookers ?: emptyList() // Return the users if the response is not null, otherwise return an empty list
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            return emptyList() // Return an empty list in case of an exception
        }
    }

    suspend fun getLookerImages(id :String): List<ProfileImages> {
        var retrofit = retrofitClient.getInstance()
        var imagesInference = retrofit.create(profileImages::class.java)
        try {
            var response = imagesInference.getProfileImagesLooker(id)
            var images = response.body()
            return images ?: emptyList() // Return the users if the response is not null, otherwise return an empty list
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            return emptyList() // Return an empty list in case of an exception
        }


    }

    suspend fun getTechsObject(id :String): List<ObjectTechnologies> {
        var retrofit = retrofitClient.getInstance()
        var otInterface = retrofit.create(objectTechnologies::class.java)
        try {
            var response = otInterface.getTechObj(id)
            var techs = response.body()
            return techs ?: emptyList() // Return the users if the response is not null, otherwise return an empty list
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
