package com.example.praktikakapustnikov.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.praktikakapustnikov.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {
    private var nextId = 1
    private var posts = listOf(
        Post(
        id = nextId++,
        author = "Бтпит. Учебное заведение",
        content = "Всех студентов поздравляем с 1 сентября!",
        published = "1 сентября в 8:00",
        likedByMe = false,
        likes = 499,
        shares = 12,
        sharedByMe = false,
        ),
        Post(
            id = nextId++,
            author = "Бтпит. Учебное заведение",
            content = "Всех студентов поздравляем с 2 сентября!",
            published = "2 сентября в 8:00",
            likedByMe = false,
            likes = 999999,
            shares = 999,
            sharedByMe = false
        )
    ).reversed()
    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun save(post: Post) {
        if (post.id == 0) {
            posts = listOf(post.copy(
                    id = nextId++,
                    author = "Me",
                    likedByMe = false,
                    sharedByMe = false,
                    published = "now"
                )
            ) + posts
            data.value = posts
            return
        }

        posts = posts.map {
            if (it.id != post.id) it else it.copy (content = post.content, likes = post.likes, shares = post.shares)
        }
        data.value = posts
    }

    override fun likeById(id: Int) {
        posts = posts.map{
            if (it.id != id) it else
                it.copy(likedByMe = !it.likedByMe, likes = if (!it.likedByMe) it.likes+1 else it.likes-1) }

        data.value = posts
    }
    override fun shareById(id:Int) {
        posts = posts.map {
            if (it.id != id) it else
                it.copy(sharedByMe = !it.sharedByMe, shares = it.shares+1)
        }
        data.value = posts
    }

    override fun removeById(id: Int) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }
}