package br.com.lucasdiastavares.testandoretrofit

import br.com.lucasdiastavares.testandoretrofit.Model.Post
import retrofit2.Call
import retrofit2.http.*

interface APIService {

    @GET("task-list")
    fun getPosts(): Call<List<Post>>

    @POST("task-create/")
    fun createPost(@Body newPost: Post): Call<Post>

    @PUT("posts/{id}/")
    fun updatePost(@Path("id") id: Long ,
                   @Field("title") title: String ,
                   @Field("price") price: Float,
                   @Field("image") image: String,
                   @Field("description") description: String)

    @DELETE("task-delete/{id}/")
    fun deletePost(@Path("id") id: Long): Call<Void>

}