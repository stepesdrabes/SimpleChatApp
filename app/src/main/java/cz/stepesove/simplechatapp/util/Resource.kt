package cz.stepesove.simplechatapp.util

enum class ResourceErrorState {
    NotFound,
    NetworkError,
    RateLimited
}

sealed class Resource<T>(val data: T? = null, val errorState: ResourceErrorState? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(errorState: ResourceErrorState, data: T? = null) : Resource<T>(data, errorState)
    class Loading<T> : Resource<T>()
}