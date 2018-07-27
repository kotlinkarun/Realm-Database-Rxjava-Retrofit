package com.demo.restservices

object ApiUtils {
   const val BASE_URL="https://raw.githubusercontent.com"
    val apiService: APIService
        get() = RetrofitClient.getClient(BASE_URL).create(APIService::class.java)
}