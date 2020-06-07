package com.example.zjyd.ui.machine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.zjyd.logic.Repository
import com.example.zjyd.logic.model.Machine

class MachineViewModel : ViewModel() {

    private val getMachineTypeLiveData = MutableLiveData<String>()

    private val getMachineLiveData = MutableLiveData<GetMachine>()

    /**
     * 机器类别列表的缓存
     */
    val machineTypeList = ArrayList<String>()

    /**
     * 机器列表的缓存
     */
    val machineList = ArrayList<Machine>()

    val machineTypeLiveData =
        Transformations.switchMap(getMachineTypeLiveData) { account ->
            Repository.getMachineType(account)
    }

    val machineLiveData =
        Transformations.switchMap(getMachineLiveData) {
            getMachine ->
                Repository.getMachine(getMachine.account, getMachine.machineType)
    }

    fun getMachineType(account: String) {
        getMachineTypeLiveData.value = account
    }

    fun getMachine(account: String, machineType: String) {
        getMachineLiveData.value = GetMachine(account, machineType)
    }

}