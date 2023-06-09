package com.example.regexbb

import com.example.regexbb.models.ObjectTechnologies
import com.example.regexbb.models.ProfileImages
import java.io.Serializable
import java.text.FieldPosition

class cards : Serializable {
    private var userId: String
    private var imageUrls: List<ProfileImages>
    private var name: String
    private var description: String
    private var clicked: Boolean
    private var age: Int
    private var degree: String
    private var school: String
    private var position: String
    private var techs: List<ObjectTechnologies>

    constructor(
        userId: String,
        imageUrls: List<ProfileImages>,
        name: String,
        description: String,
        age: Int,
        degree: String,
        school: String,
        position: String,
        techs : List<ObjectTechnologies>
    ) {
        this.userId = userId
        this.name = name
        this.imageUrls = imageUrls
        this.description = description
        this.age = age
        this.degree = degree
        this.school = school
        this.position = position
        this.clicked = false
        this.techs = techs
    }

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

    fun getImageUrl(): List<ProfileImages> {
        return imageUrls
    }

    fun setImageUrl(imageUrls: List<ProfileImages>) {
        this.imageUrls = imageUrls
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

    fun getAge(): Int {
        return age
    }

    fun setAge(age: Int) {
        this.age = age
    }

    fun getDegree(): String {
        return degree
    }

    fun setDegree(degree: String) {
        this.degree = degree
    }

    fun getSchool(): String {
        return school
    }

    fun setSchool(school: String) {
        this.school = school
    }

    fun getPosition(): String {
        return position
    }

    fun setPosition(position: String) {
        this.position = position
    }

    fun getTech(): List<ObjectTechnologies> {
        return techs
    }

    fun setTech(techs: List<ObjectTechnologies>) {
        this.techs = techs
    }
}