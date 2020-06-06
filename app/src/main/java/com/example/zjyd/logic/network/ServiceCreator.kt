package com.example.zjyd.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit构建器
 */
object ServiceCreator {

    /**
     * Retrofit的根路径
     */
//    private const val BASE_URL = "http://47.100.242.80:8080/web/landService"
    private const val BASE_URL = "http://192.168.31.184:8080/test1_war_exploded/"

    /**
     * 封装retrofit的构建
     */
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * 提供了一个外部可见的create()方法，并接受一个Class类型的参数
     */
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    /**
     * 利用泛型实化，使得构建更加简洁
     */
    inline fun <reified T> create(): T = create(T::class.java)
}