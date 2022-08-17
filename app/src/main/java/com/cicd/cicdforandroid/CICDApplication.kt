package com.cicd.cicdforandroid

import android.app.Application
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.AppCenter


class CICDApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppCenter.start(
            this, "5ee244d9-c5ba-4707-aa5a-97e9c9a21998",
            Analytics::class.java, Crashes::class.java
        )
    }
}