package uz.smd.fantastic.data.entities

data class ResponseData<T>(
    val status: String,
    val message: String = "Successful",
    val data: T? = null
)