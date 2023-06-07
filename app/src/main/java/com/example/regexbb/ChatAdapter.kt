package com.example.regexbb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val chats: List<Chat>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.chat_name)
        val messageView: TextView = itemView.findViewById(R.id.chat_message)
        val imageView: ImageView = itemView.findViewById(R.id.chat_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
        return ChatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chats[position]
        holder.nameView.text = chat.name
        holder.messageView.text = chat.message
//        holder.imageView.setImageResource(chat.imageResId)
    }

    override fun getItemCount(): Int {
        return chats.size
    }
}