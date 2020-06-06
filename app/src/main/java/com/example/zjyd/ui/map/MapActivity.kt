package com.example.zjyd.ui.map

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zjyd.R
import com.example.zjyd.ui.machine.MachineActivity

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
        setContentView(R.layout.activity_map)
    }
}
