package com.example.zjyd.logic.network

import com.example.zjyd.logic.model.LoginResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 登录操作接口
 */
interface LoginService {
    /**
     * GET请求
     * @param account 账号
     * @param password 密码
     * @return Call<LoginResponse> 返回值是Retrofit内置的Call的类型，并将服务器
     * 响应的数据转换为LoginResponse
     */
    @GET("code=land")
    fun login(@Query("account") account: String,
              @Query("password") password: String): Call<LoginResponse>
}