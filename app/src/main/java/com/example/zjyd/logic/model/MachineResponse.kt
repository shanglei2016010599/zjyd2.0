package com.example.zjyd.logic.model

import com.google.gson.annotations.SerializedName

/**
 * 机器接口返回的JSON数据
 * @param result 查询结果，success表示查询成功，failure表示查询失败
 * @param machineList 机器列表
 */
data class MachineResponse(val result: String,
       @SerializedName("machine") val machineList: List<Machine>)

/**
 * 封装的机器类
 * @param machineName 机器昵称
 * @param machineID 机器ID
 */
data class Machine(val machineName: String, val machineID: String)