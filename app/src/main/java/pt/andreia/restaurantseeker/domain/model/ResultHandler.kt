package pt.andreia.restaurantseeker.domain.model

sealed class ResultHandler <out T: Any> {
    data class Success<out T: Any>(val value: T): ResultHandler<T>()
    data class Failure<out T: Any>(val throwable: Throwable) : ResultHandler<T>()
}