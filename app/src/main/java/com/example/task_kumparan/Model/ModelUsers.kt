package com.example.task_kumparan.Model

import com.google.gson.annotations.SerializedName

data class ModelUsers(
    val name :String,
    val username: String,
    val email:String,
    @SerializedName("address")
    val address: Address,
    @SerializedName("company")
    val company:Company
)

data class Company(
    @SerializedName("name")
    val companyName : String
)

data class Address(
    val street : String,
    val suite :String
)