package com.github.kubode.playground.ksp.dev.experimental.app

import com.github.kubode.playground.ksp.dev.experimental.annotation.Mock
import kotlin.test.BeforeTest
import kotlin.test.Test

class AppTest {

    @Mock
    lateinit var useCase: UseCase

    @Mock
    lateinit var withGenerics: WithGenerics<Int>

    @BeforeTest
    fun setUp() {
        // TODO: initMock()
    }

    @Test
    fun test() {

    }
}
