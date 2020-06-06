package com.example.zjyd.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.zjyd.R
import com.example.zjyd.ui.machine.MachineActivity
import com.example.zjyd.ui.map.MapActivity
import com.example.zjyd.util.showToast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val tag = "LoginActivity"

    /**
     * 使用懒加载技术来初始化ViewModel
     */
    private val viewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java) }

    lateinit var account: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        /**
         * 登录按钮的点击事件
         */
        login.setOnClickListener {
            /**
             * 获得用户输入的账号密码
             */
            account = login_account.text.toString()
            val password = password.text.toString()
            /**
             * 如果勾选记住密码复选框，则进行保存账号密码操作
             */
            if (check.isChecked) {
                //TODO("进行保存账号密码操作")
            }
            /**
             * 调用封装的login方法
             */
            viewModel.login(account, password)
        }

        /**
         *  登录操作访问服务器结束后，会回调到此处
         *  添加liveData的观察对象
         */
        viewModel.loginLiveData.observe(this, Observer { result ->
            /**
             * 取出用户类别，如果不为空，缓存至viewModel
             * 并根据用户类别进行相应的界面跳转
             */
            val userType = result.getOrNull()
            if (userType != null) {
                viewModel.userType = userType
                //TODO("根据用户等级进行相应的界面跳转")
                when (userType) {
//                    UserType.USER -> "user".showToast()
//                    UserType.ADMINISTRATOR -> "administrator".showToast()
//                    UserType.BOSS -> "boss".showToast()
//                    else -> userType.showToast()
                    "student" -> MachineActivity.actionStart(this, account)
                    "teacher" -> MapActivity.actionStart(this)
                    "error" -> "账号或密码错误".showToast()
                }
            } else {
                "数据返回为空".showToast()
                Log.e(tag, "数据返回为空")
            }
        })
    }
}
