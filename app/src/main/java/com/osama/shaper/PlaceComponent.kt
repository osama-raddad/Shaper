package com.osama.shaper

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View

class PlaceComponent(private val hostLayout: Int) : FragmentComponent<MainFragment>() {
    override fun onCreate(mSavedInstanceState: Bundle?) {
        super.onCreate(mSavedInstanceState)
    }

    override fun onViewCreated(fragment: MainFragment, view: View, mSavedInstanceState: Bundle?) {
        super.onViewCreated(fragment, view, mSavedInstanceState)
        setContentView(R.layout.component_main, hostLayout)
    }
}
