package com.example.O7Solution

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.example.O7Solution.model.UserData
import com.example.O7Solution.view.UserAdapter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavroiteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavroiteFragment : Fragment(), ClickInterface {
    lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    lateinit var btAdd: Button
    lateinit var etItem: EditText
    lateinit var etPrice: EditText
    lateinit var mainActivity: MainActivity
    lateinit var btnAdd: Button
    lateinit var btnCancel: Button
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favroite, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        btAdd = view.findViewById(R.id.btAdd)
        userAdapter = UserAdapter(mainActivity.userList, this)
        recyclerView.layoutManager = LinearLayoutManager(mainActivity)
        recyclerView.adapter = userAdapter
        btAdd.setOnClickListener {
            val inflater = LayoutInflater.from(mainActivity)
            val v = inflater.inflate(R.layout.custom_dialog_box, null)
            val addDialog = Dialog(mainActivity)
            addDialog.create()
            addDialog.setContentView(v)
            etItem = v.findViewById(R.id.etMenu)
            etPrice = v.findViewById(R.id.etDescription)
            btnAdd = v.findViewById(R.id.btnAdd)
            btnCancel = v.findViewById(R.id.btnCancel)
            btnAdd.setOnClickListener {
                val item = etItem.text.toString()
                val des = etPrice.text.toString()
                if (etItem.text.isEmpty()) {
                    etItem.error = "Required"
                    return@setOnClickListener
                } else if (etPrice.text.isEmpty()) {
                    etPrice.error = "Required"
                    return@setOnClickListener
                } else {
                    Toast.makeText(mainActivity, "Add Data", Toast.LENGTH_SHORT).show()
                    mainActivity.userList.add(UserData(item, des.toDouble()))
                    userAdapter.notifyDataSetChanged()
                    addDialog.dismiss()
                }
            }
            btnCancel.setOnClickListener {
                Toast.makeText(mainActivity, "Cancel", Toast.LENGTH_SHORT).show()
            }
            addDialog.show()
        }
        return view
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavroiteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavroiteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun editClicked(position: Int) {
        val inflater = LayoutInflater.from(mainActivity)
        val v = inflater.inflate(R.layout.custom_dialog_box, null)
        val addDialog = AlertDialog.Builder(mainActivity)
        addDialog.create()
        addDialog.setView(v)
        etItem = v.findViewById(R.id.etMenu)
        etPrice = v.findViewById(R.id.etDescription)
        etItem.setText(mainActivity.userList[position].userName)
        etPrice.setText(mainActivity.userList[position].Price.toString())
        addDialog.setPositiveButton("update") { dialog, _ ->
            val item = etItem.text.toString()
            val des = etPrice.text.toString()
            Toast.makeText(mainActivity, "Update", Toast.LENGTH_SHORT).show()
            mainActivity.userList.set(position, UserData(item, des.toDouble()))
            userAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") { dialog, _ ->
            Toast.makeText(mainActivity, "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.show()
    }
    override fun deleteClicked(position: Int) {
        mainActivity.userList.removeAt(position)
        userAdapter.notifyDataSetChanged()
    }
}