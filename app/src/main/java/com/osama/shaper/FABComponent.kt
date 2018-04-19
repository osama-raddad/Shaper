package com.osama.shaper

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.Toast

class FABComponent : ActivityComponent<MainActivity>() {
    override fun onCreate(activity: MainActivity, mSavedInstanceState: Bundle?) {
        activity.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            Toast.makeText(activity, "Replace with your own action", Toast.LENGTH_LONG).show()
        }
    }
}