package br.com.lucasdiastavares.testandoretrofit

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.lucasdiastavares.testandoretrofit.Model.Post
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
        viewHolder.itemView.txt_post.text = list[position].title
    }

    inner class MyViewHolder(itemViewHolder: View) : RecyclerView.ViewHolder(itemViewHolder), View.OnClickListener {
        init {
            itemViewHolder.btn_edit.setOnClickListener(this)
            itemViewHolder.btn_delete.setOnClickListener(this)
        }
        override fun onClick(view: View) {
            //hackListener.onClickHack(itemView,view, adapterPosition)
        }
    }


}