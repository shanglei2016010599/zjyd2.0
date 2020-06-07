package com.example.zjyd.logic.network


import com.example.zjyd.logic.model.MachineTypeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 机器类别查询接口
 */
interface MachineTypeService {

    /**
     * GET请求
     * @param account 目标用户的账号
     * @return Call<MachineTypeResponse> 返回类型转换为MachineTypeResponse
     */
    @GET("MachineTypeServlet")
    fun getMachineType(@Query("account") account: String): Call<MachineTypeResponse>
}