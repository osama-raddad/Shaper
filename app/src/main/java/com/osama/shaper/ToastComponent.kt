package com.osama.shaper

import android.os.Bundle
import android.widget.Toast

internal class ToastComponent : ActivityComponent<MainActivity>() {
    override fun onCreate(activity: MainActivity, mSavedInstanceState: Bundle?) {
        super.onCreate(activity, mSavedInstanceState)
        Toast.makeText(activity, "Cool", Toast.LENGTH_SHORT).show()
    }


}
