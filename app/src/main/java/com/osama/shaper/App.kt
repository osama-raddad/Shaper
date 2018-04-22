package com.osama.shaper

import android.app.Activity

import com.osama.shaper.dependencies.APIEndPointModule
import com.osama.shaper.dependencies.BaseApplication
import com.osama.shaper.dependencies.BaseApplicationComponent
import com.osama.shaper.dependencies.ContextModule
import com.osama.shaper.dependencies.DaggerBaseApplicationComponent
import com.squareup.picasso.Picasso

class App : BaseApplication() {

    private var component: BaseApplicationComponent? = null

    val service: GithubService
        get() = component!!.service as GithubService

    val picasso: Picasso
        get() = component!!.picasso

    override fun onCreate() {
        super.onCreate()
        component = DaggerBaseApplicationComponent.builder()
                .contextModule(ContextModule(this))
                .aPIEndPointModule(APIEndPointModule("https://api.github.com/", GithubService::class.java))
                .build()
    }

    fun component(): BaseApplicationComponent? {
        return component
    }

    companion object {

        operator fun get(activity: Activity): App {
            return activity.application as App
        }
    }
}
