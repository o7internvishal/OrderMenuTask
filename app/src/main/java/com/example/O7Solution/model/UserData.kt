package com.example.O7Solution.model

class UserData(
    var userName: String,

    var Price: Double,
    var userprice: Int? = 0,
    var quantity: Int = 1,
    var description: String? = null

)
{

    override fun toString(): String {
        return "$userName"
    }

}
