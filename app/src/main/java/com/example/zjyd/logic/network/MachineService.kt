package com.example.zjyd.logic.network

import com.example.zjyd.logic.model.MachineResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 机器查询接口
 */
interface MachineService {

    /**
     * GET请求
     * @param account 目标用户的账号
     * @param machineType 机器类别
     * @return Call<MachineResponse> 返回类型为MachineResponse
     */
    @GET("MachineServlet")
    fun getMachine(@Query("account") account: String,
                   @Query("machineType") machineType: String): Call<MachineResponse>

}