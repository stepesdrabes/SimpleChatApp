package cz.stepesove.simplechatapp.data.remote

sealed class RequestResult<T>(val data: T? = null) {
    class Ok<T>(data: T? = null) : RequestResult<T>(data)
    class Unauthorized<T> : RequestResult<T>()
    class NotFound<T> : RequestResult<T>()
    class BadRequest<T> : RequestResult<T>()
    class UnknownError<T> : RequestResult<T>()
}