package uz.mobiler.dagger2.database

import androidx.lifecycle.LiveData
import uz.mobiler.dagger2.database.dao.UserDao
import uz.mobiler.dagger2.database.entity.UserEntity
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val userDao: UserDao) {

    suspend fun addUsers(list: List<UserEntity>) {
        userDao.insertUsers(list)
    }

    fun getUsers(): LiveData<List<UserEntity>> {
        return userDao.getUsers()
    }
}