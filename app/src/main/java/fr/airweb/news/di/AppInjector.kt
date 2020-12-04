package fr.airweb.news.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dagger.android.AndroidInjection
import fr.airweb.news.NewsApplication

object AppInjector {
    fun init(application: NewsApplication) {
            DaggerAppComponent.builder()
                .application(application)
                .Build()
                .inject(application)

        application.registerActivityLifecycleCallbacks(object: Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
                AndroidInjection.inject(activity)
            }

            override fun onActivityStarted(p0: Activity) { }

            override fun onActivityDestroyed(p0: Activity) { }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) { }

            override fun onActivityStopped(p0: Activity) {}

            override fun onActivityCreated(p0: Activity, p1: Bundle?) {}

            override fun onActivityResumed(p0: Activity) {}

        })
    }
}