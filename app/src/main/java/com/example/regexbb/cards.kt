package com.example.regexbb

class cards {
    // property (data member)
    private var userId: String
    private var imageUrl: String
    private var name: String
    private var description: String
    private var clicked : Boolean


    constructor(userId: String, imageUrl: String ,name: String, description: String) {
        this.userId = userId
        this.name = name
        this.imageUrl = imageUrl
        this.description = description
        this.clicked = false
    }

    // getter and setter for userId
    fun getUserId(): String {
        return userId
    }

    fun setUserId(userId: String) {
        this.userId = userId
    }

    fun getClicked(): Boolean {
        return clicked
    }

    fun setClickedCard(clicked: Boolean) {
        this.clicked = clicked
    }

    // getter and setter for imageUrl
    fun getImageUrl(): String {
        return imageUrl
    }

    fun setImageUrl(imageUrl: String) {
        this.imageUrl = imageUrl
    }

    // getter and setter for name
    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getDescription(): String {
        return description
    }

    fun setDescription(description: String) {
        this.description = description
    }

}