package com.interview.athletesapplication

sealed class UIState<out T : Any> {
    object Loading : UIState<Nothing>()
    data class Content<out T : Any>(val viewData: T) : UIState<T>()
    object Error : UIState<Nothing>()
}