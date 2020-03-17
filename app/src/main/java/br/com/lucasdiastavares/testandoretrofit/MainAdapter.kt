package br.com.lucasdiastavares.testandoretrofit

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.lucasdiastavares.testandoretrofit.Model.Hits
import br.com.lucasdiastavares.testandoretrofit.Model.Post
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.row_post.view.*

class MainAdapter(private var context: Context,
                  private var list: ArrayList<Post>) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(root: ViewGroup, p1: Int): MyViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.row_post, root, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.bind(list[position], context)
    }

    class MyViewHolder(itemViewHolder: View) : RecyclerView.ViewHolder(itemViewHolder), View.OnClickListener {
        init {

        }

        fun bind(post: Post, context: Context){
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_launcher_foreground)
            requestOptions.error(R.drawable.ic_launcher_background)

            Glide.with(context)
                    .load(post.image)
                    .apply(requestOptions)
                    .into(itemView.image_view)

            itemView.txt_post.text = post.title

        }
        override fun onClick(view: View) {}
    }


}