package uz.mobiler.dagger2.retrofit

import uz.mobiler.dagger2.models.User
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getUsers(): List<User> = apiService.getUsers()
}