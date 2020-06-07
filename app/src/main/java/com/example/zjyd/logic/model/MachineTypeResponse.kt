package com.example.zjyd.logic.model

/**
 * 机器类别接口返回的JSON数据
 * @param result 查询结果，success表示查询成功，failure表示查询失败
 * @param machineType 机器类别，封装在List中
 */
data class MachineTypeResponse(val result: String, val machineType: List<String>)