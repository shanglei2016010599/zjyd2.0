package com.example.zjyd.ui.map

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import com.example.zjyd.R
import com.example.zjyd.ZjydApplication

class MapActivity : AppCompatActivity() {

    companion object {
        /**
         * MapActivity的启动方法
         * @param context 上下文对象
         */
        fun actionStart(context: Context) {
            val intent = Intent(context, MapActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(ZjydApplication.context)
        setContentView(R.layout.activity_map)
    }
}
