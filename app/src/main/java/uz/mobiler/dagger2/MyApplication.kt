package uz.mobiler.dagger2

import android.app.Application
import uz.mobiler.dagger2.module.DatabaseModule
import javax.inject.Inject

class MyApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()
    }
}