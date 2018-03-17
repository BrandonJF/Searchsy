package brandonjf.com.searchsy.ui

import android.app.Application
import brandonjf.com.searchsy.BuildConfig
import com.facebook.stetho.Stetho
import timber.log.Timber
import timber.log.Timber.DebugTree



/**
 * Created by Brandon on 3/11/18.
 */
class SearchsyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
    }
}