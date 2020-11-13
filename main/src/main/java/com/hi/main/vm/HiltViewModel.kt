package com.hi.main.vm

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

    fun getArticleAndBanner() = liveData<HhResult<Any>> {
        repository.geyArticleAndBanner().catch { e ->
            emit(HhResult.Failure(e))
        }.collectLatest {
            it.data.sameAs<WanResponse<List<Banner>>> { result ->
                emit(HhResult.Success(result.data))
            }
            it.data.sameAs<WanResponse<List<ListResponse>>> { result ->
                emit(HhResult.Success(result.data))
            }
        }
    }
}