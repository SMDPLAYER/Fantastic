package uz.smd.fantastic.data.entities

data class VerifyCodeData(
    val status: String,
    val message: String = "Successful",
    val data: Any
)