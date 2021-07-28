package uz.mobiler.dagger2.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import uz.mobiler.dagger2.database.entity.UserEntity
import uz.mobiler.dagger2.repository.UserRepository
import uz.mobiler.dagger2.utils.Resource
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    fun users(): LiveData<Resource<List<UserEntity>>> {
        return repository.getUsersFromDb()
    }
}