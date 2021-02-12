package com.github.kubode.playground.ksp.dev.experimental.processor

import com.github.kubode.playground.ksp.dev.experimental.annotation.Mock
import com.google.auto.service.AutoService
import com.google.devtools.ksp.closestClassDeclaration
import com.google.devtools.ksp.isAbstract
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSTypeReference
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
        logger.info("process")
        val symbols = resolver.getSymbolsWithAnnotation(Mock::class.qualifiedName!!)
        logger.info("symbols: ${symbols.size}")
        val (validSymbols, invalidSymbols) = symbols.partition { it.validate() }
        logger.info("validSymbols: $validSymbols, invalidSymbols: $invalidSymbols")
        val props = validSymbols.filterIsInstance<KSPropertyDeclaration>()
        logger.info("props: $props")
        val types = props.map { it.type }.toSet()
        logger.info("types: $types")
        types.forEach { generateMockByType(it) }
        val parents = props.mapNotNull { it.parentDeclaration }.toSet()
        logger.info("parents: $parents")
        return invalidSymbols
    }

    private fun generateMockByType(typeRef: KSTypeReference) {
        val type = typeRef.resolve()
        val packageName = type.declaration.packageName.getQualifier()
        val targetFqn = type.declaration.qualifiedName?.asString()!!
        val targetClassName = type.declaration.simpleName.getShortName()
        val fileName = "${targetClassName}Mock"
        val className = "${targetClassName}Mock"
        val abstractFunctions = type.declaration.closestClassDeclaration()!!.getAllFunctions().filter { it.isAbstract }
        val abstractProps = type.declaration.closestClassDeclaration()!!.getAllProperties().filter { it.isAbstract() }
        val output = codeGenerator.createNewFile(
            dependencies = Dependencies(true),
            packageName = packageName,
            fileName = fileName,
        )
        // TODO: Improve readability.
        output.write(
            """
                package $packageName
                
                import $targetFqn
                
                class $className : $targetClassName {
                    
                    ${
                abstractFunctions
                    .map { func ->
                        "override fun ${func.simpleName.asString()}(${func.parameters.map {"${it.name!!.asString()}: ${it.type.resolve().declaration.qualifiedName!!.asString()}"}.joinToString(", ")}) = TODO()"
                    }
                    .joinToString("\n")
            }
                }
            """.trimIndent().toByteArray()
        )
        output.close()
    }

    override fun finish() {
    }
}
