package uz.mobiler.dagger2.retrofit

import retrofit2.http.GET
import uz.mobiler.dagger2.models.User

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>
}