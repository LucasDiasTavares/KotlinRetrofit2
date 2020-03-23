package br.com.lucasdiastavares.testandoretrofit

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import br.com.lucasdiastavares.testandoretrofit.Model.Post
import br.com.lucasdiastavares.testandoretrofit.Utils.HackListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add__dialog_main.view.*
import kotlinx.android.synthetic.main.row_post.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), HackListener {

    private var mainAdapter: MainAdapter?=null
    private var myList = ArrayList<Post>()

    private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api-para-estudos-kotlin.herokuapp.com/api/")
            .build()
            .create(APIService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        getPosts()
    }

    private fun init() {

        toolbar.title = "CRUD Tavares"
        swipeRefreshLayout.setOnRefreshListener {
            getPosts()
            swipeRefreshLayout.setRefreshing(false)
        }

        btn_main_dialog.setOnClickListener{
            showDialogAdd()
        }

        rc_view.layoutManager = LinearLayoutManager(
                this, LinearLayout.VERTICAL, false)

        mainAdapter = MainAdapter(this@MainActivity, myList, this)

        rc_view.adapter = mainAdapter
    }

    override fun onClickHack(view: View, position: Int) {
        when(view.id){
            R.id.btn_main_delete -> {
                deletePost((myList[position].id)!!)
                Toast.makeText(this, "Deletado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getPosts()
    }

    //GET posts
    private fun getPosts() {
        retrofit.getPosts().enqueue(object: Callback<List<Post>>{
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

    //POST post
    private fun createPost(title: String, price: Float, image: String, description: String){
        retrofit.createPost(
                Post(title = title, price = price, image = image, description = description))
                .enqueue(object: Callback<Post>{
            override fun onResponse(call: Call<Post>?, response: Response<Post>?) {
                getPosts()
                Toast.makeText(this@MainActivity, "Adicionado", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Post>?, t: Throwable?) {
                Log.e("ERROR", t?.message.toString())
            }
        })

    }

    //DELETE post
    private fun deletePost(id: Int){
        retrofit.deletePost(id).enqueue(object: Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                getPosts()
                Toast.makeText(this@MainActivity, "Deletado", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                Log.e("ERROR", t?.message.toString())
            }

        })
    }

    fun showDialogAdd(
            context: Context = this) {

        var dialog: AlertDialog? = null
        val alertBuilder = AlertDialog.Builder(context, R.style.CustomDialogTransParent)
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.add__dialog_main, null)
        alertBuilder.setView(dialogView)

        dialogView.dialog_save_button.setOnClickListener {
            createPost(title = dialogView.dialog_edit_title.text.toString(),
                       price = dialogView.dialog_edit_price.text.toString().toFloat(),
                       image = dialogView.dialog_edit_img.text.toString(),
                       description = dialogView.dialog_edit_description.text.toString())
            dialog?.dismiss()
        }

        dialogView.dialog_cancel_button.setOnClickListener {
            dialog?.dismiss()
        }

        dialog = alertBuilder.create()
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        try {
            if (!dialog?.isShowing!! && !(context as AppCompatActivity).isFinishing) dialog.show()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}

