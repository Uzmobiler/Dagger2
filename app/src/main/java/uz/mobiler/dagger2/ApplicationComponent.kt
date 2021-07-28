package uz.mobiler.dagger2

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import uz.mobiler.dagger2.module.DatabaseModule
import uz.mobiler.dagger2.module.NetworkModule
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent

        @BindsInstance
        fun application(context: Context): Builder
    }
    fun inject(mainActivity: MainActivity)
}