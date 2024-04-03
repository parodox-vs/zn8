package com.example.praktikakapustnikov.dto

data class Post (
    val id: Int,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int,
    val likedByMe: Boolean,
    var shares: Int,
    val sharedByMe: Boolean
)
