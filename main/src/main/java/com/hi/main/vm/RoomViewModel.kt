package com.hi.main.vm

import android.app.Application
import androidx.lifecycle.*
import com.hi.common.room.HhDataBase
import com.hi.common.room.entity.StudentEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Creatd by wbxuzhen on 2021/3/26 15:25
 * description:
 */
class RoomViewModel(application: Application) : AndroidViewModel(application) {

    fun subscribe() = liveDate

    private val studentFromDB: LiveData<List<StudentEntity>> by lazy {
        HhDataBase.getDataBase(getApplication()).studentDao().loadAllUser()
    }

    private val liveDate: MutableLiveData<List<StudentEntity>> by lazy {
        MutableLiveData<List<StudentEntity>>().also {
            studentFromDB.observeForever(observer)
        }
    }
    private val observer: Observer<List<StudentEntity>> by lazy {
        Observer<List<StudentEntity>> {
            if (it.isNotEmpty()) {
                liveDate.value = it
            }
        }
    }

    fun insertUpdate(list: List<StudentEntity>, block: () -> Unit) {
        viewModelScope.launch {
            withContext(IO) {
                HhDataBase.getDataBase(getApplication()).studentDao().insert(list)
                liveDate.postValue(list)
                block.invoke()
            }
        }
    }

    fun delete() {
        viewModelScope.launch {
            withContext(IO) {
                HhDataBase.getDataBase(getApplication()).studentDao().deleteAll()
                liveDate.postValue(null)
            }
        }
    }

    fun deleteById(id: Int) {
        viewModelScope.launch {
            withContext(IO) {
                HhDataBase.getDataBase(getApplication()).studentDao().delete(id)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        liveDate.removeObserver(observer)
    }
}