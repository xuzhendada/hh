package com.hi.main.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hi.common.data.HhRepository
import com.hi.common.data.HhResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

@Suppress("unused")
class HiltViewModel @ViewModelInject constructor(
    private val repository: HhRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun getArticle() = liveData {
        repository.getArticle()
            .catch { e ->
                emit(HhResult.Failure(e))
            }
            .collect { it ->
                emit(HhResult.Success(it))
            }
    }
}