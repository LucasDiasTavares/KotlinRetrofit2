package br.com.lucasdiastavares.testandoretrofit

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.lucasdiastavares.testandoretrofit.Model.Post
import br.com.lucasdiastavares.testandoretrofit.Utils.HackListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.row_post.view.*

class MainAdapter(private var context: Context,
                  private var list: ArrayList<Post>,
                  private var hackListener: HackListener) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

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

    fun diffUtilList(postList: ArrayList<Post>): ArrayList<Post>{
        val oldList = list
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
                ItemDiffCallback(oldList, postList)
        )
        list = postList
        diffResult.dispatchUpdatesTo(this)
        return  list
    }

    class ItemDiffCallback(var oldItemList: ArrayList<Post>,
                           var newItemList: ArrayList<Post>):DiffUtil.Callback(){

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldItemList[oldItemPosition].id == newItemList[newItemPosition].id )
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItemList[oldItemPosition] == newItemList[newItemPosition]
        }

        override fun getOldListSize(): Int {
            return  oldItemList.size
        }

        override fun getNewListSize(): Int {
            return  newItemList.size
        }

    }

    inner class MyViewHolder(itemViewHolder: View) : RecyclerView.ViewHolder(itemViewHolder), View.OnClickListener {
        init {
            itemViewHolder.btn_main_delete.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            hackListener.onClickHack(view, adapterPosition)
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
            itemView.txt_price.text = post.price?.toString()

        }
    }


}