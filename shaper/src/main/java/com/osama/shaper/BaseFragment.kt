package com.osama.shaper

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

open class BaseFragment : Fragment() {
    protected lateinit var fragmentComponentManager: FragmentComponentManager<*>
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponentManager = FragmentComponentManager.getInstance(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentComponentManager.triggerOnCreateView(inflater, savedInstanceState)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponentManager.triggerOnCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        fragmentComponentManager.triggerOnResume()
    }
}
