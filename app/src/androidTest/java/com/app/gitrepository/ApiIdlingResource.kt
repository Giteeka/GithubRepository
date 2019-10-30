package com.app.gitrepository

import android.view.View
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.matcher.ViewMatchers

class ApiIdlingResource(private val view: View, private val expectedVisibility: Int) :
    IdlingResource {

    private var callback: IdlingResource.ResourceCallback? = null
    private var isIdle: Boolean = true
        set(value) {
            field = value
        }

    override fun getName(): String {
        return "ApiIdlingResource"
    }

    override fun isIdleNow(): Boolean {
        if (isIdle) return true
        isIdle = view.visibility == expectedVisibility
        if (isIdle)
            callback?.onTransitionToIdle()

        return isIdle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }

}