package com.github.kubode.playground.ksp.dev.experimental.processor

import com.github.kubode.playground.ksp.dev.experimental.annotation.Mock
import com.google.auto.service.AutoService
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.google.devtools.ksp.validate

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

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(Mock::class.qualifiedName!!)
        val (validSymbols, invalidSymbols) = symbols.partition { it.validate() }
        validSymbols
            .filterIsInstance<KSPropertyDeclaration>()
            .forEach { it.accept(Visitor(), Unit) }
        return invalidSymbols
    }

    private inner class Visitor : KSVisitorVoid() {

    }

    override fun finish() {
    }
}
