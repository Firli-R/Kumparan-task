package com.example.task_kumparan.Model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task_kumparan.databinding.ListUserBinding

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
   
    private val list = ArrayList<ModelData>()
    private var itemCallback: OnItemClickCallback? = null
    
    fun setClickCallback(ItemClickCallback: OnItemClickCallback){
        this.itemCallback = ItemClickCallback
    }
    fun clearUsers() {
        this.list.clear()
        notifyDataSetChanged()
    }
    
    fun setList(users:ArrayList<ModelData>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }
    inner class UserViewHolder(val binding: ListUserBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(modelData: ModelData) {
            binding.root.setOnClickListener{
                itemCallback?.onItemClicked(modelData)
            }
            binding.apply {
                title.text = "name : ${modelData.title}"
                deskripsi.text ="body : ${modelData.deskripsi}"
                userName.text = modelData.name
                companyName.text = modelData.companyName

            }
            
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ListUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  UserViewHolder((view))
    }
    
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
        
    }
    
    override fun getItemCount(): Int = list.size
    
    interface OnItemClickCallback{
        fun onItemClicked(data:ModelData)
    }
}