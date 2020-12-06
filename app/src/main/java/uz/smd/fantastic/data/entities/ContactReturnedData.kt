package uz.smd.fantastic.data.entities

import androidx.recyclerview.widget.DiffUtil

data class ContactReturnedData(
    val id: Int = 0,
    var phoneNumber: String,
    var lastName: String,
    var firstName: String
)