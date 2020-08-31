package com.example.zjyd.ui.machine

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.zjyd.R
import com.example.zjyd.ZjydApplication
import com.example.zjyd.logic.model.Level
import com.example.zjyd.logic.model.Machine
import com.example.zjyd.util.showToast
import kotlinx.android.synthetic.main.fragment_machine.*

class MachineFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(MachineViewModel::class.java) }

    private lateinit var adapter: ArrayAdapter<String>

    /**
     * 所选择的机器类别/机器
     */
    private lateinit var selectedMachineType: String
    private lateinit var selectedMachine: Machine

    /**
     * 当前层次等级，有Level.MachineType和Level.Machine，初始为0
     */
    private var currentLevel: Int = 0

    /**
     * 数据源
     */
    private var dataList: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_machine, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /**
         * 利用getIntent()获取用户的账号
         * 再获取机器类别列表，进行初始化
         */
        val account = activity?.intent?.getStringExtra("account")
        account?.let { viewModel.getMachineType(it) }

        /**
         * List列表的Adapter初始化，使用简单的文字布局
         */
        adapter = ArrayAdapter(ZjydApplication.context, android.R.layout.simple_list_item_1,
            dataList)
        list_view.adapter = adapter

        /**
         * List列表item的点击事件，Lambda表达式有4个参数
         * parent, view, position, id
         * Kotlin允许不使用的参数用_替换
         */
        list_view.setOnItemClickListener { _, _, position, _ ->
            /**
             * 如果当前在机器类别层级，则点击事件是利用用户账号和选中的机器类别
             * 去获取所符合合条件的机器
             *
             * 如果当前在机器层级，则点击事件是利用machineID这个机器独一无二的
             * 识别符来进行访问该机器的详细信息
             */
            if (currentLevel == Level.MachineType) {
                selectedMachineType = viewModel.machineTypeList[position]
                account?.let { viewModel.getMachine(it, selectedMachineType) }
            } else if (currentLevel == Level.Machine) {
                selectedMachine = viewModel.machineList[position]
                selectedMachine.machineID.showToast()
            }
        }

        /**
         * 访问服务器获取机器类别结束后，回调到此处
         * 机器类别获取的观察对象
         */
        viewModel.machineTypeLiveData.observe(viewLifecycleOwner, Observer { result ->
            val machineType = result.getOrNull()
            /**
             * 如果返回的数据不为空
             * 修改标题，将返回按钮设为不可见
             */
            if (machineType != null) {
                title_text.text = "浙江易锻"
                back_button.visibility = View.GONE
                /**
                 * 将得到的机器类别列表缓存到ViewModel中
                 */
                viewModel.machineTypeList.clear()
                viewModel.machineTypeList.addAll(machineType)
                /**
                 * 将得到的机器类别添加到数据源dataList中，进行列表的更新
                 */
                dataList.clear()
                dataList.addAll(viewModel.machineTypeList)
                adapter.notifyDataSetChanged()
                list_view.setSelection(0)
                /**
                 * 将当前层级设置为Level.MachineType
                 */
                currentLevel = Level.MachineType
            } else {
                "访问服务器失败".showToast()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        /**
         * 访问服务器获取机器结束后，回调到此处
         * 机器列表获取的观察对象
         */
        viewModel.machineLiveData.observe(viewLifecycleOwner, Observer { result ->
            val machineList = result.getOrNull()
            /**
             * 如果返回的数据不为空
             * 将标题修改为用户之前选中的机器类别
             * 并将返回按钮设置为可见
             */
            if (machineList != null) {
                title_text.text = selectedMachineType
                back_button.visibility = View.VISIBLE
                /**
                 * 将得到的机器列表缓存到ViewModel中
                 */
                viewModel.machineList.clear()
                viewModel.machineList.addAll(machineList)
                /**
                 * 将机器列表更新到数据源dataList中，并刷新列表
                 */
                dataList.clear()
                for (machine in machineList) {
                    dataList.add(machine.machineName)
                }
                adapter.notifyDataSetChanged()
                list_view.setSelection(0)
                /**
                 * 将当前层级设置为Level.Machine
                 */
                currentLevel = Level.Machine
            } else {
                "访问服务器失败".showToast()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        /**
         * 返回按钮的点击事件
         */
        back_button.setOnClickListener {
            /**
             * 修改标题，并将返回按钮设置为不可见
             */
            title_text.text = "浙江易锻"
            back_button.visibility = View.GONE
            /**
             * 清除数据源
             * 从ViewModel从获取之前缓存的机器类别数据
             * 更新List列表
             */
            dataList.clear()
            dataList.addAll(viewModel.machineTypeList)
            adapter.notifyDataSetChanged()
            list_view.setSelection(0)
            /**
             * 将当前的层级设置为Level.MachineType
             */
            currentLevel = Level.MachineType
        }
    }
}