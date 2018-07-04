package com.osama.shaper


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.NonNull
import android.view.LayoutInflater
import android.view.View


open class ActivityComponent<T : Activity> {
    private var componentView: ComponentView? = null

    protected lateinit var activity: T
        private set

    protected val context: Context?
        get() = activity

    open fun onCreate( activity: T, mSavedInstanceState: Bundle?) {
        this.activity = activity
    }


    open fun onResume(@NonNull activity: T) {

    }

    open fun onStop(activity: T) {

    }

    open fun getComponentView(): ComponentView? {
        return componentView
    }

    protected fun setContentView(@LayoutRes layout: Int): View? {
        return if (componentView != null) {
            LayoutInflater.from(activity).inflate(layout, componentView)
        } else
            null
    }

    fun setComponentView(componentView: ComponentView): ActivityComponent<T> {
        if (this.componentView == null) this.componentView = componentView
        return this
    }
}
