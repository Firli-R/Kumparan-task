package com.example.task_kumparan.Model

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task_kumparan.databinding.ListAlbumBinding
import com.squareup.picasso.Picasso

class UserAdapterAlbum: RecyclerView.Adapter<UserAdapterAlbum.UserViewHolder>() {
    private val TAG = "user"
    private val list = ArrayList<ModelAlbum>()
    private var itemCallback: OnItemClickCallback? = null
    
    fun setClickCallback(ItemClickCallback: OnItemClickCallback){
        this.itemCallback = ItemClickCallback
    }
    fun clearUsers() {
        this.list.clear()
        notifyDataSetChanged()
    }
    
    fun setList(users:ArrayList<ModelAlbum>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }
    inner class UserViewHolder(val binding: ListAlbumBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(modelData: ModelAlbum) {
            binding.root.setOnClickListener{
                itemCallback?.onItemClicked(modelData)
            }
            Log.d(TAG, "gambar:${modelData.thumbnailUrl} ")
            Picasso.get().load(modelData.thumbnailUrl).into(binding.myImage)
            binding.apply {
                title.text = "title : ${modelData.title}"
            }
            
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ListAlbumBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  UserViewHolder((view))
    }
    
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
        
    }
    
    override fun getItemCount(): Int = list.size
    
    interface OnItemClickCallback{
        fun onItemClicked(data:ModelAlbum)
    }
}