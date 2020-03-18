package br.com.lucasdiastavares.testandoretrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import br.com.lucasdiastavares.testandoretrofit.Model.Hits
import br.com.lucasdiastavares.testandoretrofit.Model.Post
import com.baoyz.widget.PullRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var mainAdapter: MainAdapter?=null
    private var myList = ArrayList<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        retrofit2()
    }

    private fun retrofit2() {
        //Retrofit Builder
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api-para-estudos-kotlin.herokuapp.com/api/")
                .build()

        val jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi::class.java)

        jsonPlaceholderApi.getPosts().enqueue(object: Callback<List<Post>>{

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                myList.addAll(mainAdapter?.diffUtilList(
                        response.body() as ArrayList<Post>)?:ArrayList())
                mainAdapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable?) {
                Log.e("ERROR", t?.message.toString())
            }
        })
    }

    private fun init() {

        swipeRefreshLayout.setOnRefreshListener {
            retrofit2()
            swipeRefreshLayout.setRefreshing(false)
        }

        rc_view.layoutManager = LinearLayoutManager(
                this, LinearLayout.VERTICAL, false)

        mainAdapter = MainAdapter(this@MainActivity, myList)

        rc_view.adapter = mainAdapter
    }
}

