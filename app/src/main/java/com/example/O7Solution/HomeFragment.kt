package com.example.O7Solution

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.O7Solution.model.UserData
import com.example.O7Solution.view.UserorderAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), AddInterface {
    //    val num:TextView
    lateinit var textView: TextView
    lateinit var tvValue: TextView
    lateinit var recyclerVieworder: RecyclerView
    lateinit var spinnerAdapter: ArrayAdapter<UserData>
    lateinit var btnAdd: Button
    lateinit var userorderAdapter: UserorderAdapter
    lateinit var mainActivity: MainActivity
    lateinit var spinner: Spinner
    var orderList = ArrayList<UserData>()
    private val TAG = "HomeFragment"
    //    lateinit var binding: FragmentHomeBinding
    var selectedPosition = -1
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerVieworder = view.findViewById(R.id.recyclerViewOrder)
        textView = view.findViewById(R.id.tvTextView)
        spinner = view.findViewById(R.id.spinner)
        btnAdd = view.findViewById(R.id.btnAdd)
        spinnerAdapter =
            ArrayAdapter(mainActivity, android.R.layout.simple_list_item_1, mainActivity.userList)
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
//        binding.spinner.adapter = spinnerAdapter
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                selectedPosition = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedPosition = 0
            }
        }
        btnAdd.setOnClickListener {
            userorderAdapter = UserorderAdapter(mainActivity.userList, this)
            recyclerVieworder.layoutManager = LinearLayoutManager(mainActivity)
            recyclerVieworder.adapter = userorderAdapter
            orderList.add(mainActivity.userList.get(selectedPosition))
            userorderAdapter.notifyDataSetChanged()
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
         * @return A new instance of fragment HomeFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) = HomeFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
    override fun addClicked(userData: Int) {
        orderList[selectedPosition].quantity = orderList[selectedPosition].quantity + 1
        userorderAdapter.notifyDataSetChanged()
        calulatePrice()
    }
    override fun minusClicked(userData: Int) {
        if (mainActivity.userList[selectedPosition].quantity == 0) {
            mainActivity.userList.removeAt(userData)
        } else {
            orderList[selectedPosition].quantity = orderList[selectedPosition].quantity - 1
        }
        userorderAdapter.notifyDataSetChanged()
        calulatePrice()
    }
    fun calulatePrice() {
        var totalPrice = 0.0
        for (orders in orderList) {
            Log.e(TAG, " userprice ${orders.Price}")
            totalPrice = totalPrice + orders.quantity * (orders.Price ?: 0.0)
        }
        textView.setText(totalPrice.toString())
    }
}




