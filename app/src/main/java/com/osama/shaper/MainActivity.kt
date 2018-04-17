package com.osama.shaper

import android.os.Bundle

class MainActivity : BaseActivity() {
    override fun onCreate(componentManager: ActivityComponentManager<*>, savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        componentManager
                .add(ToastComponent())
                .add(FABComponent())
    }


}
