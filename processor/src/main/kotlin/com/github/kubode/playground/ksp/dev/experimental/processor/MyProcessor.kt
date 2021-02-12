package com.github.kubode.playground.ksp.dev.experimental.processor

import com.github.kubode.playground.ksp.dev.experimental.annotation.Mock
import com.google.auto.service.AutoService
import com.google.devtools.ksp.getClassDeclarationByName
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType

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
        logger.info("process start")
        val symbols = resolver.getSymbolsWithAnnotation(Mock::class.qualifiedName!!)
        if (symbols.isEmpty()) {
            logger.info("Mock not configured.")
            return
        }

        logger.info("$symbols")
        symbols.forEach { it.process(resolver) }
    }

    private fun KSAnnotated.process(resolver: Resolver) {
        val mockTypeDef = checkNotNull(resolver.getClassDeclarationByName<Mock>()).qualifiedName!!
        logger.info("${this.annotations} $mockTypeDef")
        val mockDef = annotations.firstOrNull { it.apply { logger.info("$this")}.annotationType.resolve() == mockTypeDef }
//        val classes = mockDef.arguments.first { it.name?.asString() == Mock::classesToGenerateMock.name }

    }

    override fun finish() {
    }
}
