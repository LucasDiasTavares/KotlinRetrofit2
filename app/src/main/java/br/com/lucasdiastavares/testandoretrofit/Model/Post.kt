package br.com.lucasdiastavares.testandoretrofit.Model


class Post(
        val id: Int?=null,
        val title: String?=null,
        val price: Float?=null,
        val image: String?=null,
        val description: String?=null
)
{
     override fun equals(other: Any?): Boolean {

         if(javaClass != other?.javaClass){
             return false
         }

         other as Post

         if(id != other.id){
             return false
         }
         if(title != other.title){
             return false
         }
         if(price != other.price){
             return false
         }
         if(image != other.image){
             return false
         }
         if(description != other.description){
             return false
         }

         return true
    }
}