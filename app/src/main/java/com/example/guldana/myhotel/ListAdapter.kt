package com.example.guldana.myhotel

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.guldana.myhotel.Model.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_list_item.view.*

class ListAdapter(var news: List<News>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = news[position].title
        holder.desc.text = news[position].desc
        Picasso.get().load(news[position].imgUrl).into(holder.img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.news_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        var title: TextView = itemView!!.title
        var desc: TextView = itemView!!.desc
        var img: ImageView = itemView!!.news_image

    }
}