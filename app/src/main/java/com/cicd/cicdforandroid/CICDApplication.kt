package com.cicd.cicdforandroid

import android.app.Application
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.AppCenter


class CICDApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppCenter.start(
            this, "89abec65-4217-403c-b68b-a1f8a660539a",
            Analytics::class.java, Crashes::class.java
        )
    }
}
