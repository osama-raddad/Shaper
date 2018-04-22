package com.osama.shaper

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.Toast
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.disposables.CompositeDisposable

class FABComponent : ActivityComponent<MainActivity>() {
    private val disposables = CompositeDisposable()

    override fun onCreate(activity: MainActivity, mSavedInstanceState: Bundle?) {
        disposables.add(activity.findViewById<FloatingActionButton>(R.id.fab)
                .clicks()
                .subscribe {
                    App[activity].service.allRepos
                            .subscribeOn(rx.schedulers.Schedulers.newThread())
                            .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                            .subscribe {
                                Toast.makeText(activity, it[0].fullName, Toast.LENGTH_LONG).show()
                            }
                })
    }

    override fun onStop(activity: MainActivity?) {
        super.onStop(activity)
        disposables.clear()
    }
}