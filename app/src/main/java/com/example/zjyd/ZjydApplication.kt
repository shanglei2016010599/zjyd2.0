package com.example.zjyd

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * 全局获取Context
 * 每当应用程序启动的时候Android会自动将Application类初始化，
 * 定制自己的Application类，以便于管理程序内一些全局的状态信
 * 息，比如全局Context。
 */
@SuppressLint("Registered")
class ZjydApplication : Application() {

    companion object {
        lateinit var context: Context
    }

    /**
     * 重写父类的onCreate()方法，并将调用getApplication()方法得到的
     * 返回值赋值给context，这样既可以静态变量的形式获取Context对象。
     */
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}