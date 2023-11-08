package eu.tutorial.restapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ServiceInterface {

    @Headers("Content-Type:application/json")
    @GET("/products")
    fun getAllProduct():Call<ApiResponse>
}