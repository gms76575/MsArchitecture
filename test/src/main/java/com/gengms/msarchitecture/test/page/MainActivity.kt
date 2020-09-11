package com.gengms.msarchitecture.test.page

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gengms.msarchitecture.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val heapSize = manager.memoryClass
        Log.e("MainActivity", "heapSize=$heapSize")
    }
}