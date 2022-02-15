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
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Singleton

@Singleton
@Suppress("unused")
class HiltViewModel @ViewModelInject constructor(
    private val repository: HhRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun getArticle() = liveData {
        repository.getArticle().catch { e ->
            emit(HhResult.Failure(e))
        }.collect {
            emit(HhResult.Success(it))
        }
    }

    fun getHomeArticle(pageIndex: Int) = liveData {
        repository.getHomeArticle(pageIndex).catch { e ->
            emit(HhResult.Failure(e))
        }.collectLatest {
            emit(HhResult.Success(it))
        }
    }

    fun getBanner() = liveData {
        repository.getBanner().catch { e ->
            emit(HhResult.Failure(e))
        }.collectLatest {
            emit(HhResult.Success(it))
        }
    }
}