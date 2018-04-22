package com.osama.shaper

import android.os.Bundle

class MainActivity : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponentManager.add(ToastComponent(),FABComponent())


    }
}
