package com.tobery.livedata.call.adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.tobery.livedata.call.livedatalib.Status

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GitHubService.create().getBanner(2)
            .observe(this, Observer {
                if (it.status == Status.SUCCESS){
                    Log.e("数据",it.data?.banners.toString())
                }
            })
    }
}