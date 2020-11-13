package com.hi.main.vm

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hi.common.data.HhRepository
import com.hi.common.data.HhResult
import com.hi.common.data.WanResponse
import com.hi.common.data.response.Banner
import com.hi.common.data.response.ListResponse
import com.hi.common.ktx.sameAs
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

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