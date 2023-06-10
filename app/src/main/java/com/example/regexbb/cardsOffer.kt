package com.example.regexbb

import com.example.regexbb.models.ObjectTechnologies
import com.example.regexbb.models.ProfileImages
import java.io.Serializable
import java.text.FieldPosition

class cardsOffer : Serializable {
    private var userId: String
    private var imagesUrl: List<ProfileImages>
    private var name: String
    private var description: String
    private var pay: Double
    private var schedule: String
    private var mode: String
    private var techs: List<ObjectTechnologies>

    constructor(
        userId: String,
        imagesUrl: List<ProfileImages>,
        name: String,
        description: String,
        pay: Double,
        schedule: String,
        mode: String,
        techs : List<ObjectTechnologies>
    ) {
        this.userId = userId
        this.name = name
        this.imagesUrl = imagesUrl
        this.description = description
        this.pay = pay
        this.schedule = schedule
        this.mode = mode
        this.techs = techs
    }

    fun getUserId(): String {
        return userId
    }

    fun setUserId(userId: String) {
        this.userId = userId
    }

    fun getImageUrl(): List<ProfileImages> {
        return imagesUrl
    }

    fun setImageUrl(imageUrl: List<ProfileImages>) {
        this.imagesUrl = imageUrl
    }

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

    fun getPay(): Double {
        return pay
    }

    fun setPay(pay: Double) {
        this.pay = pay
    }

    fun getSchedule(): String {
        return schedule
    }

    fun setSchedule(schedule: String) {
        this.schedule = schedule
    }

    fun getMode(): String {
        return mode
    }

    fun setMode(mode: String) {
        this.mode = mode
    }

    fun getTech(): List<ObjectTechnologies> {
        return techs
    }

    fun setTech(techs: List<ObjectTechnologies>) {
        this.techs = techs
    }
}