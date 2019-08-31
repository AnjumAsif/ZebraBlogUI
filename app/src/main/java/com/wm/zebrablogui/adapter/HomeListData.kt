package com.wm.zebrablogui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wm.zebrablogui.R
import com.wm.zebrablogui.utility.OnItemClickListener

class HomeListData(var context: Context, var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<HomeListData.ViewHolderMessage>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMessage {

        return ViewHolderMessage(LayoutInflater.from(context).inflate(R.layout.item_home_list, parent, false))

    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolderMessage, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(position)
        }
    }


    class ViewHolderMessage(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*var title: TextView = itemView.findViewById(R.id.textView1)
        var productName: TextView = itemView.findViewById(R.id.textProduct)
        var price: TextView = itemView.findViewById(R.id.textPrice)*/
    }

}