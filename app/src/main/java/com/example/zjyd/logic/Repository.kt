package com.example.zjyd.logic

import androidx.lifecycle.liveData
import com.example.zjyd.logic.network.LoginService
import com.example.zjyd.logic.network.ZjydNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

/**
 * 仓库层
 */
object Repository {
    /**
     * 返回一个LiveData对象给ViewModel
     *
     * 将liveData()函数的线程参数类型指定成Dispatchers.IO，表明代码
     * 在子线程中运行。
     */
    fun login(account: String, password: String) = fire(Dispatchers.IO) {
        val loginResponse = ZjydNetwork.login(account, password)
        if (loginResponse.result == "success") {
            val userType = loginResponse.userType
            Result.success(userType)
        } else {
            Result.failure(RuntimeException("response status is ${loginResponse.result}"))
        }
    }

    /**
     * fire()函数是一个高阶函数，在此处统一进行try-catch处理，并在
     * try语句中调用传入的Lambda表达式中的代码，最终获取Lambda表达式
     * 的执行结果并调用emit()方法发射出去。
     *
     * 利用lifecycle-livedata-ktx库提供的liveData()函数，
     * 动构建并返回一个liveData对象。
     */
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }

}