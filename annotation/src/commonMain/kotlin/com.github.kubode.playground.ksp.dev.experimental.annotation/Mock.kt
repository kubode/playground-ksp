package com.github.kubode.playground.ksp.dev.experimental.annotation

import kotlin.reflect.KClass

annotation class Mock(vararg val classesToGenerateMock: KClass<*>)
