package uz.smd.fantastic.data.retrofit

import retrofit2.Call
import retrofit2.http.*
import uz.smd.fantastic.data.entities.ContactData
import uz.smd.fantastic.data.entities.ContactReturnedData
import uz.smd.fantastic.data.entities.ResponseData

interface ContactApi {
    /**
     * 1. Get all contacts
     * */
    @GET("contact")
//    @Headers("lang:uz")
    fun getAll(): Call<ResponseData<List<ContactReturnedData>>>

    /**
     * 2. add new a contact
     * */
    @POST("contact")
    fun add(@Body contactData: ContactData): Call<ResponseData<ContactReturnedData>>

    /**
     * 3. remove a contact
     * */
    @HTTP(method = "DELETE", path = "contact", hasBody = true)
    fun remove(@Body contactData: ContactData): Call<ResponseData<ContactReturnedData>>
/**
 * 4 Update contact
 * */
    @PUT("contact")
    fun update(@Body contactData: ContactData): Call<ResponseData<ContactReturnedData>>
}