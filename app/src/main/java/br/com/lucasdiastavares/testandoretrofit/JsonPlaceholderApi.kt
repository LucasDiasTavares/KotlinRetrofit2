package br.com.lucasdiastavares.testandoretrofit

import br.com.lucasdiastavares.testandoretrofit.Model.Post
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceholderApi {

    @GET("?key=$KEY")
    fun getPosts(): Call<Post>

}