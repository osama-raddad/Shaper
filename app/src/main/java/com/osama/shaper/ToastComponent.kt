package com.osama.shaper

import android.os.Bundle
import android.widget.Toast

internal class ToastComponent : ActivityComponent<MainActivity>() {
    override fun onCreate(activity: MainActivity, mSavedInstanceState: Bundle?) {
        if (mSavedInstanceState == null)
            Toast.makeText(activity, "Cool", Toast.LENGTH_SHORT).show()
    }

}
