package com.example.O7Solution.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.O7Solution.AddInterface
import com.example.O7Solution.R
import com.example.O7Solution.model.UserData


class UserorderAdapter(val userList: ArrayList<UserData>, var AddInterface: AddInterface) :
    RecyclerView.Adapter<UserorderAdapter.UserHolder>() {
    lateinit var tvAdd: TextView
    var num = 0;
    inner class UserHolder(val v: View) : RecyclerView.ViewHolder(v) {
        var tvItem: TextView
        var imgAdd: ImageButton
        var imgMinus: ImageButton
        init {
            tvItem = v.findViewById(R.id.tvItem)
            imgAdd = v.findViewById(R.id.imgAdd)
            tvAdd = v.findViewById(R.id.tvAdd)
            imgMinus = v.findViewById(R.id.imgMinus)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.orderitem, parent, false)
        return UserHolder(v)
    }
    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val newList = userList[position]
        holder.tvItem.text = newList.userName
        newList.quantity
        holder.imgAdd.setOnClickListener {
            num++
            tvAdd.text = num.toString()
            AddInterface.addClicked(position)
        }
        holder.imgMinus.setOnClickListener {
            if (num >= 1) {
                num--
            } else {
            }
            tvAdd.text = num.toString()
            AddInterface.minusClicked(position)
        }
    }
    override fun getItemCount(): Int {
        return userList.size
    }
}