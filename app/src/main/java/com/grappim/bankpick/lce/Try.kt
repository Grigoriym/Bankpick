package com.grappim.bankpick.lce

import kotlinx.coroutines.CancellationException
import timber.log.Timber

sealed class Try<out S, out E> {

    data class Success<out S>(val result: S) : Try<S, Nothing>()

    data class Error<out E>(val result: E) : Try<Nothing, E>()
}

typealias VoidTry<E> = Try<Unit, E>

inline fun <S, R> S.runOperationCatching(block: S.() -> R): Try<R, Throwable> {
    return try {
        Try.Success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        Timber.e(e)
        Try.Error(e)
    }
}

inline fun <S, E> Try<S, E>.doOnSuccess(block: (S) -> Unit): Try<S, E> {
    if (this is Try.Success) {
        block(this.result)
    }
    return this
}

inline fun <S, E> Try<S, E>.doOnError(block: (E) -> Unit): Try<S, E> {
    if (this is Try.Error) {
        block(this.result)
    }
    return this
}

inline fun <S, E, R> Try<S, E>.mapSuccess(block: (S) -> R): Try<R, E> =
    when (this) {
        is Try.Success -> Try.Success(result = block(this.result))
        is Try.Error -> Try.Error(result = this.result)
    }

inline fun <S, E, R> Try<S, E>.mapError(block: (E) -> R): Try<S, R> =
    when (this) {
        is Try.Success -> Try.Success(result = this.result)
        is Try.Error -> Try.Error(result = block(this.result))
    }

inline fun <S, E, R> Try<S, E>.mapNestedSuccess(
    block: (S) -> Try<R, E>,
): Try<R, E> =
    when (this) {
        is Try.Success -> block(this.result)
        is Try.Error -> Try.Error(result = this.result)
    }