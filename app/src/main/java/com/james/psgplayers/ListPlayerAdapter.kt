package com.james.psgplayers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListPlayerAdapter (private val listPlayer : ArrayList<Player>):RecyclerView.Adapter<ListPlayerAdapter.ListViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback : OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val imgItemPhoto : ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvItemName : TextView = itemView.findViewById(R.id.tv_item_name)
        val tvItemPosition : TextView = itemView.findViewById(R.id.tv_item_position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view:View= LayoutInflater.from(parent.context).inflate(R.layout.item_row_player, parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPlayer.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, index: Int) {
        val (name,position, photo) = listPlayer[index]
        holder.tvItemName.text = name
        holder.tvItemPosition.text = position
        Glide.with(holder.itemView.context)
            .load(photo)
            .circleCrop()
            .into(holder.imgItemPhoto)
        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listPlayer[holder.adapterPosition])}
    }

    interface OnItemClickCallback{
        fun onItemClicked(data:Player)
    }
}