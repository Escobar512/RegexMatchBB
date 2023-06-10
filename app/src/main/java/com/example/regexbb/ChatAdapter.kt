package com.example.regexbb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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
        holder.messageView.text = chat.lastMessage
        Glide.with(holder.imageView)
            .load(chat.imageUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.imageView)


    }

    override fun getItemCount(): Int {
        return chats.size
    }
}