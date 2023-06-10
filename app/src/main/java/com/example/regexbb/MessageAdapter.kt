package com.example.regexbb

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.regexbb.interfaces.lookingProfiles
import com.example.regexbb.interfaces.offeringProfile
import com.example.regexbb.models.LookingProfile
import com.example.regexbb.models.Message
import com.example.regexbb.models.OfferingProfile
import com.example.regexbb.retrofit.retrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MessageAdapter(private val context: Context, private val messages: MutableList<com.example.data.entity.Message>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageTextView: TextView = itemView.findViewById(R.id.messageTextView)
        val senderTextView: TextView = itemView.findViewById(R.id.senderTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.message_bubble, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.messageTextView.text = message.message

        CoroutineScope(Dispatchers.Main).launch {
            val looker = getLooker(message.sender)
            val offer = getOfferer(message.sender)

            val senderName = if (looker.name.isNotBlank()) {
                looker.name
            } else {
                offer.name
            }
            holder.senderTextView.text = senderName
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    suspend fun getLooker(id: String): LookingProfile {
        val retrofit = retrofitClient.getInstance()
        val lookingInter = retrofit.create(lookingProfiles::class.java)
        try {
            val response = lookingInter.getLookingProfileByUser(id)
            val looker = response.body()
            return looker ?: LookingProfile()
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            return LookingProfile()
        }
    }

    suspend fun getOfferer(id: String): OfferingProfile {
        val retrofit = retrofitClient.getInstance()
        val offerInterface = retrofit.create(offeringProfile::class.java)
        try {
            val response = offerInterface.getOfferingProfileByUserId(id)
            val offerer = response.body()
            return offerer ?: OfferingProfile()
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            return OfferingProfile()
        }
    }
}