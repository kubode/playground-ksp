package com.github.kubode.playground.ksp.dev.experimental.app

import com.github.kubode.playground.ksp.dev.experimental.annotation.Mock

@Mock(
    UseCase::class,
    UseCaseImpl::class,
    DataClass::class,
)
annotation class Mocks
