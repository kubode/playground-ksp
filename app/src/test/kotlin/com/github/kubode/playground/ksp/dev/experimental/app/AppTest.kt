package com.github.kubode.playground.ksp.dev.experimental.app

import com.github.kubode.playground.ksp.dev.experimental.annotation.Mock
import kotlin.test.BeforeTest
import kotlin.test.Test

class AppTest {

    @Mock
    lateinit var useCase: UseCase

    @Mock
    lateinit var useCase2: UseCase

    // TODO: Support generics
//    @Mock
//    lateinit var withGenerics: WithGenerics<Int>

    @BeforeTest
    fun setUp() {
        // TODO: initMock()
    }

    @Test
    fun test() {
        every { useCase() } just runs
        verify { useCase() }
    }
}

class Wrapper<T>(block: () -> T)
fun <T> every(block: () -> T): Wrapper<T> {
    return Wrapper(block)
}
infix fun <T> Wrapper<T>.just(runs: runs) {}
object runs
fun verify(block: () -> Unit) {}
