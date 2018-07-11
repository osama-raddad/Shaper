package com.osama.shaper

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import io.reactivex.disposables.CompositeDisposable


abstract class FragmentComponent<T : Fragment> {

    private var isContentViewSet: Boolean = false


    protected lateinit var fragment: T

    private var inflater: LayoutInflater? = null

    protected val context: Context?
        get() = fragment.context

    protected val activity: Activity?
        get() = fragment.activity

    protected val disposables = CompositeDisposable()

    open fun onCreate(mSavedInstanceState: Bundle?) {}

    open fun onCreateView(fragment: T, inflater: LayoutInflater, mSavedInstanceState: Bundle?) {
        this.fragment = fragment
        this.inflater = inflater
    }

    open fun onViewCreated(fragment: T, view: View, mSavedInstanceState: Bundle?) {}

    open fun onResume(fragment: T) {

    }

    open fun onStop(fragment: T) {
        disposables.clear()
        disposables.dispose()
    }

    open fun onStart(fragment: T) {

    }

    open fun onDestroy(fragment: T) {

    }

    protected fun setContentView(@LayoutRes layout: Int, @IdRes hostLayout: Int): View? {
        return if (!isContentViewSet) {
            isContentViewSet = true
            LayoutInflater.from(context).inflate(layout, activity?.findViewById(hostLayout))
        } else null
    }
}
