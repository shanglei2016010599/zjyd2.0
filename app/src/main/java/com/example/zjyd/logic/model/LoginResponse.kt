package com.example.zjyd.logic.model

/**
 * 登录接口返回的JSON数据
 * @param result 登录结果，success表示登录成功，failure表示登录失败
 * @param userType 用户类别，表示用户的权限等级
 */
data class LoginResponse(val result: String, val userType: String)