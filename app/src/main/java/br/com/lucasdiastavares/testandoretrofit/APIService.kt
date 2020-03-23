package br.com.lucasdiastavares.testandoretrofit

import br.com.lucasdiastavares.testandoretrofit.Model.Post
import retrofit2.Call
import retrofit2.http.*

interface APIService {

    @GET("task-list")
    fun getPosts(): Call<List<Post>>

    @POST("task-create/")
    fun createPost(@Body newPost: Post): Call<Post>

    @PATCH("task-update/{id}/")
    fun updatePost(@Path("id") id: Int ,
                   @Field("title") title: String ,
                   @Field("price") price: Float,
                   @Field("image") image: String,
                   @Field("description") description: String)

    @DELETE("task-delete/{id}/")
    fun deletePost(@Path("id") id: Int): Call<Void>

}