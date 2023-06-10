package com.example.regexbb

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.data.database.RegexDatabase
import com.example.regexbb.interfaces.messages
import com.example.regexbb.models.Chat
import com.example.regexbb.models.Message
import com.example.regexbb.retrofit.retrofitClient
import com.example.regexbb.sockets.SocketHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*
import java.util.concurrent.TimeoutException

class MessageList : AppCompatActivity() {

    private lateinit var chatAdapter: MessageAdapter
    private lateinit var messagesList: MutableList<com.example.data.entity.Message>
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatEditText: EditText
    private lateinit var sendButton: Button

    private var isSocketInitialized = false

    private lateinit var db: RegexDatabase

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)

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

        messagesList = mutableListOf()
        chatRecyclerView = findViewById(R.id.chatRecyclerView)
        chatEditText = findViewById(R.id.chatEditText)
        sendButton = findViewById(R.id.sendButton)

        chatAdapter = MessageAdapter(this, messagesList)
        chatRecyclerView.adapter = chatAdapter
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.scrollToPosition(chatAdapter.itemCount - 1)

        CoroutineScope(Dispatchers.Main).launch {
            val messages = getMessagesChat("53c3ab49-f2ed-42b2-8672-00bab0d88a90", applicationContext)
            messagesList.addAll(messages)
            chatAdapter.notifyDataSetChanged()
            chatRecyclerView.scrollToPosition(chatAdapter.itemCount - 1)
        }

        sendButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val message = Message()
                message.sender = "2"
                message.receiver = "11"
                message.message = chatEditText.text.toString()
                message.chatId = "53c3ab49-f2ed-42b2-8672-00bab0d88a90"
                message.messageId = UUID.randomUUID().toString()

                val messageForList = com.example.data.entity.Message(UUID.randomUUID().toString(),
                    "2",
                    "11",
                    chatEditText.text.toString(),
                    "53c3ab49-f2ed-42b2-8672-00bab0d88a90")

                // Add the new message to the list and notify the adapter
                messagesList.add(messageForList)
                chatAdapter.notifyItemInserted(messagesList.size - 1)

                // Scroll to the newly added message
                chatRecyclerView.scrollToPosition(messagesList.size - 1)

                // Create the message via API
                createMessage(message)

                // Clear the chatEditText
                chatEditText.setText("")
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getMessagesChat(id: String, context: Context): List<com.example.data.entity.Message> {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isNetworkConnected = networkInfo?.isConnected ?: false

        if (isNetworkConnected) {
            var retrofit = retrofitClient.getInstance()
            var messageInterface = retrofit.create(com.example.regexbb.interfaces.messages::class.java)

            try {
                var response = messageInterface.getMessagesChat(id)
                var messages = response.body()

                messages?.let {
                    val messageEntities = it.map { message ->
                        com.example.data.entity.Message(
                            messageId = message.messageId,
                            sender = message.sender,
                            receiver = message.receiver,
                            message = message.message,
                            chatId = message.chatId
                        )
                    }
                    db.messageDAO().insertMessages(messageEntities)
                }

            } catch (e: Exception) {
                Log.d("Exception", e.toString())
                return emptyList()
            }
            finally {
                val messagesdb = db.messageDAO().getMessagesByChatId(id).map { Message ->
                    com.example.data.entity.Message(
                        messageId = Message.messageId,
                        sender = Message.sender,
                        receiver = Message.receiver,
                        message = Message.message,
                        chatId = Message.chatId
                    )
                }

                return messagesdb ?: emptyList()
            }
        } else {
            val messagesdb = db.messageDAO().getMessagesByChatId(id).map { Message ->
                com.example.data.entity.Message(
                    messageId = Message.messageId,
                    sender = Message.sender,
                    receiver = Message.receiver,
                    message = Message.message,
                    chatId = Message.chatId
                )
            }
            return messagesdb ?: emptyList()
        }
    }


    suspend fun createMessage(message : Message): Message? {
        var retrofit = retrofitClient.getInstance()
        var messageInterface = retrofit.create(messages::class.java)

        try {
            val response = messageInterface.createMessage(message)
            if (response.isSuccessful) {
                var chatR = response.body()
                return chatR
            } else {
                Log.d("error", response.errorBody().toString())
            }
        } catch (e: Exception) {
            Log.d("error", e.message.toString())

            return null
        }
        return null
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupSocketListeners() {
        val socket = SocketHandler.getSocket()
        socket.on("message_event") { args ->
            val receivedMessage = args[0].toString()
            reloadMessagesList()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun reloadMessagesList() {
        CoroutineScope(Dispatchers.Main).launch {
            val messages = getMessagesChat("53c3ab49-f2ed-42b2-8672-00bab0d88a90", applicationContext)
            messagesList.clear()
            for (message in messages) {
                messagesList.add(message)
            }

            withContext(Dispatchers.Main) {
                chatAdapter.notifyDataSetChanged()
            }
        }
    }


}