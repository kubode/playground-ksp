package com.github.kubode.playground.ksp.dev.experimental.processor

import com.google.auto.service.AutoService
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor

@AutoService(SymbolProcessor::class)
class MyProcessor : SymbolProcessor {

    lateinit var codeGenerator: CodeGenerator
    lateinit var logger: KSPLogger

    override fun init(
        options: Map<String, String>,
        kotlinVersion: KotlinVersion,
        codeGenerator: CodeGenerator,
        logger: KSPLogger
    ) {
        logger.info("options: $options, kotlinVersion: $kotlinVersion")
        this.codeGenerator = codeGenerator
        this.logger = logger
    }

    override fun process(resolver: Resolver) {
        // TODO
    }

    override fun finish() {
    }
}
