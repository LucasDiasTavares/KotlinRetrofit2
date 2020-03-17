package br.com.lucasdiastavares.testandoretrofit

import br.com.lucasdiastavares.testandoretrofit.Model.Post
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceholderApi {

    @GET("task-list")
    fun getPosts(): Call<List<Post>>

}