package com.example.O7Solution.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.O7Solution.ClickInterface
import com.example.O7Solution.FavroiteFragment
import com.example.O7Solution.R
import com.example.O7Solution.model.UserData

//import com.example.O7Solution.model.serData

class UserAdapter(val userList: ArrayList<UserData>, var clickInterface: ClickInterface) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    inner class UserViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        var name: TextView
        var mbNum: TextView
        var mMenus: ImageButton
        var mdlt: ImageButton
        init {
            name = v.findViewById<TextView>(R.id.tvItem)
            mbNum = v.findViewById<TextView>(R.id.tvDescription)
            mMenus = v.findViewById<ImageButton>(R.id.imgplus)
            mdlt = v.findViewById<ImageButton>(R.id.imdlt)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.itemlist, parent, false)
        return UserViewHolder(v)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = userList[position]
        holder.name.text = newList.userName
        holder.mbNum.text = newList.Price.toString()
        holder.mMenus.setOnClickListener {
            clickInterface.editClicked(position)
        }
        holder.mdlt.setOnClickListener {
            clickInterface.deleteClicked(position)
        }
    }
    override fun getItemCount(): Int {
        return userList.size
    }
}