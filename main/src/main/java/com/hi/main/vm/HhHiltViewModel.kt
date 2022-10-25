package com.hi.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.hi.common.data.*
import com.hi.common.data.response.Article
import com.hi.common.data.response.Banner
import com.hi.common.data.response.ListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HhHiltViewModel @Inject constructor(
    private val repository: HhInterfaceImpl,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _bannerData: MutableLiveData<HhResult<WanResponse<List<Banner>>>> =
        MutableLiveData()

    val bannerData: LiveData<HhResult<WanResponse<List<Banner>>>>
        get() = _bannerData

    private val _homeArticleLiveData: MutableLiveData<HhResult<WanResponse<PageListResult<Article>>>> =
        MutableLiveData()

    val homeArticleLiveData: LiveData<HhResult<WanResponse<PageListResult<Article>>>>
        get() = _homeArticleLiveData

    private val _articleLiveData: MutableLiveData<HhResult<WanResponse<List<ListResponse>>>> =
        MutableLiveData()

    val articleLiveData: LiveData<HhResult<WanResponse<List<ListResponse>>>>
        get() = _articleLiveData

    private suspend fun executeGetBanner(): Flow<HhResult<WanResponse<List<Banner>>>> = flow {
        val netData = repository.getBanner()
        emit(HhResult.Success(netData))
    }

    fun getBanner() {
        viewModelScope.launch {
            executeGetBanner().catch { e ->
                _bannerData.value = HhResult.Failure(e)
            }.onEach { data ->
                _bannerData.value = data
            }.launchIn(viewModelScope)
        }
    }

    private suspend fun executeHomeArticle(pageIndex: Int): Flow<HhResult<WanResponse<PageListResult<Article>>>> {
        return flow {
            val netData = repository.getHomeArticle(pageIndex)
            emit(HhResult.Success(netData))
        }
    }

    fun getHomeArticle(pageIndex: Int) {
        viewModelScope.launch {
            executeHomeArticle(pageIndex).catch { e ->
                _homeArticleLiveData.value = HhResult.Failure(e)
            }.onEach { data ->
                _homeArticleLiveData.value = data
            }.launchIn(viewModelScope)
        }
    }

    private suspend fun executeArticle(): Flow<HhResult<WanResponse<List<ListResponse>>>> = flow {
        val netData = repository.getList()
        emit(HhResult.Success(netData))
    }

    fun getArticle() {
        viewModelScope.launch {
            executeArticle().catch { e ->
                _articleLiveData.value = HhResult.Failure(e)
            }.onEach { data ->
                _articleLiveData.value = data
            }.launchIn(viewModelScope)
        }
    }
}