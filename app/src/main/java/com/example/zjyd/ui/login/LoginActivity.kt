package com.example.zjyd.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.zjyd.R
import com.example.zjyd.logic.model.UserType
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    /**
     * 使用懒加载技术来初始化ViewModel
     */
    private val viewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java) }

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
            val account = login_account.text.toString()
            val password = password.text.toString()
            /**
             * 如果勾选记住密码复选框，则进行保存账号密码操作
             */
            if (check.isChecked) {
                TODO("进行保存账号密码操作")
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
                when (userType) {
                    UserType.USER -> TODO("根据用户等级进行相应的界面跳转")
                    UserType.ADMINISTRATOR -> TODO("根据用户等级进行相应的界面跳转")
                    UserType.BOSS -> TODO("根据用户等级进行相应的界面跳转")
                }
            } else {
                TODO("进行异常处理")
            }
        })
    }
}
