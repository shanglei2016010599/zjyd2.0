package com.example.zjyd.logic.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 网络数据访问接口
 */
object ZjydNetwork {

    /**
     * 获得LoginService接口的动态代理对象
     */
    private val loginService = ServiceCreator.create<LoginService>()

    /**
     * 获得MachineTypeService接口的动态代理对象
     */
    private val machineTypeService =
        ServiceCreator.create<MachineTypeService>()

    /**
     * 获得MachineService接口的动态代理对象
     */
    private val machineService = ServiceCreator.create<MachineService>()

    /**
     * 登录操作，调用LoginService接口中的login()方法
     */
    suspend fun login(account: String, password: String) =
        loginService.login(account, password).await()

    /**
     * 获得机器类别列表，调用MachineTypeService接口中的getMachineType()方法
     */
    suspend fun getMachineType(account: String) =
        machineTypeService.getMachineType(account).await()

    /**
     * 获得机器列表，调用MachineService接口中的getMachine()方法
     */
    suspend fun getMachine(account: String, machineType: String)
         = machineService.getMachine(account, machineType).await()

    /**
     * 利用协程，简化回调方法
     * await()是一个挂起函数，是一个泛型函数，是Call<T>的扩展函数。
     * 所有返回值是Call类型的Retrofit网络请求接口都可以直接调用。
     *
     * 使用suspendCoroutine函数挂起当前协程，await()拥有Call对象
     * 的上下文，因此可以直接调用enqueue()方法让Retrofit发起网络请求。
     *
     * 之后重写回调方法。
     */
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }
            })
        }
    }
}