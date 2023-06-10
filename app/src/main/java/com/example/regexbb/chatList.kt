package com.example.regexbb

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.data.database.RegexDatabase
import com.example.regexbb.interfaces.*
import com.example.regexbb.models.*
import com.example.regexbb.retrofit.retrofitClient
import com.example.regexbb.sockets.SocketHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception



class chatList : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter
    private var isSocketInitialized = false

    private var chats = mutableListOf<Chat>()

    private lateinit var db: RegexDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_list)

        db = Room.databaseBuilder(
            applicationContext,
            RegexDatabase::class.java, "RegexDB"
        ).build()

        if (!isSocketInitialized) {
            SocketHandler.setSocket()
            SocketHandler.establishConnection()
            isSocketInitialized = true
        }

        setupSocketListeners()

        val user = User()
        user.userId = "11"
        user.isLooking = false

        recyclerView = findViewById(R.id.chat_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        chatAdapter = ChatAdapter(chats)
        recyclerView.adapter = chatAdapter

        CoroutineScope(Dispatchers.Main).launch {
            loadChats(user)
        }
    }

    suspend fun getLooker(id :String): LookingProfile  {
        var retrofit = retrofitClient.getInstance()
        var lookingInter = retrofit.create(lookingProfiles::class.java)
        try {
            var response = lookingInter.getLookingProfileByUser(id)
            var looker = response.body()
            return looker ?: LookingProfile()
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            return LookingProfile()
        }
    }

    suspend fun getOfferer(id :String): OfferingProfile  {
        var retrofit = retrofitClient.getInstance()
        var offerInterface = retrofit.create(offeringProfile::class.java)
        try {
            var response = offerInterface.getOfferingProfileByUserId(id)
            var offerer = response.body()
            return offerer ?: OfferingProfile()
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            return OfferingProfile()
        }
    }

    suspend fun getChatsLooker(id :String): List<com.example.regexbb.models.Chat>  {
        var retrofit = retrofitClient.getInstance()
        var chatInterface = retrofit.create(chat::class.java)
        try {
            var response = chatInterface.getChatsByLookerId(id)
            var chats = response.body()
            return chats ?: emptyList()
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            return emptyList()
        }
    }


    suspend fun getChatsByOfferor(id :String): List<com.example.regexbb.models.Chat>  {
        var retrofit = retrofitClient.getInstance()
        var chatInterface = retrofit.create(chat::class.java)
        try {
            var response = chatInterface.getChatsByOffererId(id)
            var chats = response.body()
            return chats ?: emptyList()
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            return emptyList()
        }
    }

    private suspend fun loadChats(user: User) {
        CoroutineScope(Dispatchers.Main).launch {
            chats.clear()

            if (user.isLooking) {
                val perfil = getLooker("11")
                val chatsRes = getChatsLooker(perfil.profileId)

                for (chat in chatsRes) {
                    val offerer = getOfferer(chat.offererId)
                    val images = getProfileOfferorImages(offerer.profileId)
                    val messages = getMessagesChat(chat.chatId)

                    val chatForAdapter = if (messages.isEmpty()) {
                        Chat(offerer.name, "ENVIA EL PRIMER MENSAJE", images[0].imageUrl)
                    } else {
                        Chat(offerer.name, messages[0].message, images[0].imageUrl)
                    }

                    chats.add(chatForAdapter)
                }
            } else {
                val perfil = getOfferer("11")
                val chatsRes = getChatsByOfferor(perfil.profileId)

                for (chat in chatsRes) {
                    val looker = getLooker(chat.lookerId)
                    val images = getProfileLookerImages(looker.profileId)
                    val messages = getMessagesChat(chat.chatId)

                    val chatForAdapter = if (messages.isEmpty()) {
                        Chat(looker.name, "ENVIA EL PRIMER MENSAJE", images[0].imageUrl)
                    } else {
                        Chat(looker.name, messages[0].message, images[0].imageUrl)
                    }

                    chats.add(chatForAdapter)
                }
            }

            chatAdapter.notifyDataSetChanged()
        }
    }

    private fun reloadChatList() {
        val user = User()
        user.userId = "11"
        user.isLooking = false

        CoroutineScope(Dispatchers.Main).launch {
            chats.clear()

            if (user.isLooking) {
                val perfil = getLooker("11")
                val chatsRes = getChatsLooker(perfil.profileId)

                for (chat in chatsRes) {
                    val offerer = getOfferer(chat.offererId)
                    val images = getProfileOfferorImages(offerer.profileId)
                    val messages = getMessagesChat(chat.chatId)

                    val chatForAdapter = if (messages.isEmpty()) {
                        Chat(offerer.name, "ENVIA EL PRIMER MENSAJE", images[0].imageUrl)
                    } else {
                        Chat(offerer.name, messages[0].message, images[0].imageUrl)
                    }

                    chats.add(chatForAdapter)
                }
            } else {
                val perfil = getOfferer("11")
                val chatsRes = getChatsByOfferor(perfil.profileId)

                for (chat in chatsRes) {
                    val looker = getLooker(chat.lookerId)
                    val images = getProfileLookerImages(looker.profileId)
                    val messages = getMessagesChat(chat.chatId)

                    val chatForAdapter = if (messages.isEmpty()) {
                        Chat(looker.name, "ENVIA EL PRIMER MENSAJE", images[0].imageUrl)
                    } else {
                        Chat(looker.name, messages[0].message, images[0].imageUrl)
                    }

                    chats.add(chatForAdapter)
                }
            }

            chatAdapter.notifyDataSetChanged()
        }
    }

    private fun setupSocketListeners() {
        val socket = SocketHandler.getSocket()
        socket.on("message_event") { args ->
            val receivedMessage = args[0].toString()
            runOnUiThread {
                reloadChatList()
            }
        }
    }


    suspend fun getMessagesChat(id :String): List<Message>  {
        var retrofit = retrofitClient.getInstance()
        var messageInterface = retrofit.create(messages::class.java)
        try {
            var response = messageInterface.getMessagesChat(id)
            var messages = response.body()
            return messages ?: emptyList()
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            return emptyList()
        }
    }


    suspend fun getProfileLookerImages(id :String): List<ProfileImages> {
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

    suspend fun getProfileOfferorImages(id :String): List<ProfileImages> {
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

}