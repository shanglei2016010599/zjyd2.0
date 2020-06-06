package com.example.zjyd.util

import android.widget.Toast
import com.example.zjyd.ZjydApplication

fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(ZjydApplication.context, this, duration).show()
}

fun Int.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(ZjydApplication.context, this, duration).show()
}

