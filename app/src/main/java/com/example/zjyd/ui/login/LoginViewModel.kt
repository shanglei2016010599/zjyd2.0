package com.example.zjyd.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.zjyd.logic.Repository

class LoginViewModel : ViewModel() {

    private val liveData = MutableLiveData<Login>()

    /**
     * 用户类别的缓存
     */
    lateinit var userType : String

    val loginLiveData = Transformations.switchMap(liveData) { login ->
        Repository.login(login.account, login.password)
    }

    fun login(account: String, password: String) {
        liveData.value = Login(account, password)
    }
}