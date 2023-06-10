package com.example.regexbb.sockets

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import java.net.URISyntaxException

class SocketIOService : Service() {
    private lateinit var socket: Socket
    private var isConnected = false

    override fun onCreate() {
        super.onCreate()


        try {
            socket = IO.socket("http://192.168.100.5:8080")
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!isConnected) {
            connectSocket()
        }


        socket.on("server_response") { args ->
            val response = args[0].toString()
            Log.d("ASS", response)
        }

        val data = JSONObject()
        data.put("message", "Hello from client")
        socket.emit("client_event", data)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        // Close the socket connection
        disconnectSocket()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun connectSocket() {
        socket.connect()
        isConnected = true

        // Reconnection logic
        socket.on(Socket.EVENT_DISCONNECT) {
            isConnected = false
            // Implement reconnection attempt here, e.g., using a Handler and retry mechanism
        }
    }

    private fun disconnectSocket() {
        socket.disconnect()
        isConnected = false
    }
}
