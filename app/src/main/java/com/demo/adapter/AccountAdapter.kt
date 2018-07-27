package com.demo.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.R
import com.demo.entity.Result
import kotlinx.android.synthetic.main.animal_list_item.view.*


class AccountAdapter(val items : ArrayList<Result>) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.animal_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items[position],position+1)
    }

    override fun getItemCount(): Int {
        return items.size
    }


}

class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
      @SuppressLint("SetTextI18n")
      fun bindItems(result: Result, i: Int) {

          itemView.tv_ser.text= " Sr No : $i"
          itemView.tv_acc.text=" Account No :  "+result.acountNo
          itemView.tv_name.text=" Name      :  "+ result.accountName
          itemView.tv_mov.text=" Mobile No  :  " +result.mobile
          itemView.tv_address.text=" Address :  "+result.address

      }
}