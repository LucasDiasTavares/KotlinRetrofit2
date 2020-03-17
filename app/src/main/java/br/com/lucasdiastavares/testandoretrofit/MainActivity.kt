package br.com.lucasdiastavares.testandoretrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import br.com.lucasdiastavares.testandoretrofit.Model.Hits
import br.com.lucasdiastavares.testandoretrofit.Model.Post
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var mainAdapter: MainAdapter?=null
    private var myList = ArrayList<Hits>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Retrofit Builder
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://pixabay.com/api/")
                .build()

        val jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi::class.java)

        jsonPlaceholderApi.getPosts().enqueue(object :Callback<Post>{

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                myList.addAll(response.body()?.hits?:ArrayList())
                mainAdapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Post>, t: Throwable?) {
                Log.e("ERROR", t?.message.toString())
            }
        })



        rc_view.layoutManager = LinearLayoutManager(
                this, LinearLayout.VERTICAL, false)

        mainAdapter = MainAdapter(this@MainActivity, myList)

        rc_view.adapter = mainAdapter

    }
}

