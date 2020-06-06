package com.example.zjyd.ui.machine

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zjyd.R

class MachineActivity : AppCompatActivity() {

    companion object {
        /**
         * MachineActivity的启动方法，需要传入用户账号用户查询目标
         * 用户所拥有的机器类别及机器
         * @param context 上下文对象
         * @param account 用户的账号
         */
        fun actionStart(context: Context, account: String) {
            val intent = Intent(context, MachineActivity::class.java)
            intent.putExtra("account", account)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_machine)
    }
}
