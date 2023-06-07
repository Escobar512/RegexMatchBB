package com.example.regexbb

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.regexbb.interfaces.chat

class chatList : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_list)
        val chats = mutableListOf<Chat>()
        var chatDummy = Chat("Javier", "hola", 1)
        var chatDumber = Chat("Rodolfo", "Hola, buenos dias", 1)
        chats.add(chatDummy)
        chats.add(chatDumber)

        recyclerView = findViewById(R.id.chat_list) // Replace `recyclerView` with the ID of your RecyclerView in the activity layout
        recyclerView.layoutManager = LinearLayoutManager(this) // LinearLayoutManager displays items in a vertical list
        chatAdapter = ChatAdapter(chats) // Replace `chats` with your list of Chat objects
        recyclerView.adapter = chatAdapter

    }
}