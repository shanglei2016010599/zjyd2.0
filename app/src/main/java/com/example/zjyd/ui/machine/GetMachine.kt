package com.example.zjyd.ui.machine

/**
 * machineLiveData的观察对象，用于封装用户账号和所选的机器类别
 */
data class GetMachine(val account: String, val machineType: String)