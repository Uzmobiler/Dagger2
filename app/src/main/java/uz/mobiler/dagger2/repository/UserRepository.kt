package uz.mobiler.dagger2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import uz.mobiler.dagger2.database.UserLocalDataSource
import uz.mobiler.dagger2.database.entity.UserEntity
import uz.mobiler.dagger2.retrofit.UserRemoteDataSource
import uz.mobiler.dagger2.utils.NetworkHelper
import uz.mobiler.dagger2.utils.Resource
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource,
    private val networkHelper: NetworkHelper
) {
    private val users = MutableLiveData<Resource<List<UserEntity>>>()

    init {
        getUsers()
    }

    private fun getUsers() {
        if (networkHelper.isNetworkConnected()) {
            GlobalScope.launch {
                users.postValue(Resource.loading(null))
                try {
                    coroutineScope {
                        val async = async { remoteDataSource.getUsers() }
                        val await = async.await()
                        val allUsersFromApi = ArrayList<UserEntity>()
                        await.forEach {
                            allUsersFromApi.add(UserEntity(it.id, it.name, it.username, it.email))
                        }
                        localDataSource.addUsers(allUsersFromApi)
                        users.postValue(Resource.success(allUsersFromApi))
                    }
                } catch (e: Exception) {
                    users.postValue(Resource.error(e.toString(), null))
                }
            }
        } else {
            users.postValue(Resource.error("No internet connection", null))
        }
    }

    fun getUsersFromDb(): LiveData<Resource<List<UserEntity>>> {
        return users
    }
}