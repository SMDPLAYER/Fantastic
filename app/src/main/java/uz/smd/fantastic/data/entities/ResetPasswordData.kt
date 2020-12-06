package uz.smd.fantastic.data.entities

data class ResetPasswordData(val phoneNumber:String,
val password:String,
val code:String)