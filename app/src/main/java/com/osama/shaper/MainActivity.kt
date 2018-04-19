package com.osama.shaper

import android.os.Bundle
import android.widget.Toast
import com.osama.shaper.dependencies.network.GithubService
import javax.inject.Inject

class MainActivity : BaseActivity() {

    override fun onCreate(componentManager: ActivityComponentManager<*>, savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        componentManager
                .add(ToastComponent())
                .add(FABComponent())
    }


}
