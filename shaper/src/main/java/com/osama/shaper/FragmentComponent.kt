package com.osama.shaper

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View


abstract class FragmentComponent<T : Fragment> {
    private var componentView: ComponentView? = null
    private var isContentViewSet: Boolean = false


    protected lateinit var fragment: T
        private set
    private var inflater: LayoutInflater? = null

    protected val context: Context?
        get() = fragment.context

    protected val activity: Activity?
        get() = fragment.activity

    open fun onCreateView(fragment: T, inflater: LayoutInflater, mSavedInstanceState: Bundle?) {
        this.fragment = fragment
        this.inflater = inflater
    }

    open fun onViewCreated(fragment: T, view: View, mSavedInstanceState: Bundle?) {

    }


    open fun onResume(fragment: T) {

    }

    open fun onStop(fragment: T) {

    }

    open fun getComponentView(): ComponentView? {
        return componentView
    }

    protected fun setContentView(@LayoutRes layout: Int): View? {
        return if (componentView != null && !isContentViewSet) {
            isContentViewSet = true
            LayoutInflater.from(fragment.activity).inflate(layout, componentView)
        } else
            null
    }

    open fun setComponentView(componentView: ComponentView): FragmentComponent<T> {
        if (this.componentView == null) this.componentView = componentView
        return this
    }
}