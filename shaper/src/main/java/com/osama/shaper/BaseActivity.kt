package com.osama.shaper

import android.animation.LayoutTransition
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.transition.Slide
import android.view.Gravity
import android.view.ViewGroup

import android.animation.LayoutTransition.APPEARING
import android.animation.LayoutTransition.CHANGE_APPEARING
import android.animation.LayoutTransition.CHANGE_DISAPPEARING
import android.animation.LayoutTransition.CHANGING
import android.animation.LayoutTransition.DISAPPEARING

abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var activityComponentManager: ActivityComponentManager<*>
        private set


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponentManager = ActivityComponentManager.getInstance(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            setupWindowAnimations()
        setupViewAnimation()

        val mContainerView = findViewById<ViewGroup>(android.R.id.content)
        val lt = LayoutTransition()
        lt.disableTransitionType(LayoutTransition.DISAPPEARING)
        mContainerView.layoutTransition = lt

        activityComponentManager.triggerOnCreate(savedInstanceState)
    }

    public override fun onResume() {
        super.onResume()
        activityComponentManager.triggerOnResume()
    }

    override fun onStop() {
        super.onStop()
        activityComponentManager.triggerOnStop()
    }

    private fun setupViewAnimation() {
        val viewGroup = window.decorView.rootView as ViewGroup

        val layoutTransition = LayoutTransition()

        layoutTransition.enableTransitionType(APPEARING)
        layoutTransition.enableTransitionType(CHANGE_APPEARING)
        layoutTransition.enableTransitionType(CHANGE_DISAPPEARING)
        layoutTransition.enableTransitionType(CHANGING)
        layoutTransition.enableTransitionType(DISAPPEARING)

        layoutTransition.setDuration(APPEARING, 200)
        layoutTransition.setDuration(CHANGE_APPEARING, 200)
        layoutTransition.setDuration(CHANGE_DISAPPEARING, 200)
        layoutTransition.setDuration(CHANGING, 200)
        layoutTransition.setDuration(DISAPPEARING, 200)

        viewGroup.layoutTransition = layoutTransition
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun setupWindowAnimations() {
        val slideStart = Slide(Gravity.START)
        slideStart.duration = 200
        window.enterTransition = slideStart
        window.returnTransition = slideStart

        val slideEnd = Slide(Gravity.END)
        slideEnd.duration = 200
        window.exitTransition = slideEnd
    }

}
