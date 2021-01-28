package com.example.petgalleryapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val _loadingState: MutableLiveData<LoadState> = MutableLiveData(LoadState.None)
    val loadingState: LiveData<LoadState> = _loadingState

    protected val _pagingLoadState: MutableLiveData<PagingLoadState> = MutableLiveData(PagingLoadState.None)
    val pagingLoadState: LiveData<PagingLoadState> = _pagingLoadState

    sealed class LoadState {
        object None: LoadState()
        object Loading: LoadState()
        object LoadingNonCancelable: LoadState()
        object Loaded: LoadState()
        object Error: LoadState()

        fun isLoading(): Boolean = this is Loading
        fun isError(): Boolean = this is Error
    }

    sealed class PagingLoadState {
        object None: PagingLoadState()

        // first load
        object Loading: PagingLoadState()
        object Loaded: PagingLoadState()
        object Empty: PagingLoadState()
        object Error: PagingLoadState()

        // add more
        object AddMoreLoading: PagingLoadState()
        object AddMoreLoaded: PagingLoadState()
        object AddMoreError: PagingLoadState()

        fun isLoading(): Boolean = this is Loading
        fun isError(): Boolean = this is Error
        fun isEmpty(): Boolean = this is Empty
        fun isAddMoreLoading(): Boolean = this is AddMoreLoading
        fun isAddMoreError(): Boolean = this is AddMoreError
    }

    companion object {
        // prevent click event by a delay time, default value is 300L
        // and can be changed as needed
        const val CLICK_TIME_DELAY = 300L
    }
}
