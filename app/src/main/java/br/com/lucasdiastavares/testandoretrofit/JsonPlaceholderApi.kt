package br.com.lucasdiastavares.testandoretrofit

import br.com.lucasdiastavares.testandoretrofit.Model.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface JsonPlaceholderApi {

    @GET("task-list")
    fun getPosts(): Call<List<Post>>

    @POST("task-create/")
    fun createPost(@Body newPost: Post): Call<Post>

}