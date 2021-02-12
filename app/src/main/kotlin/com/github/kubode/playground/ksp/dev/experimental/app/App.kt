package com.github.kubode.playground.ksp.dev.experimental.app

class App

interface UseCase {
    operator fun invoke()
}

interface WithGenerics<T> {
    fun doSomething(): T
}

data class DataClass(val value: Int)
