package com.github.kubode.playground.ksp.dev.experimental.processor

import com.github.kubode.playground.ksp.dev.experimental.annotation.Mock
import com.google.auto.service.AutoService
import com.google.devtools.ksp.closestClassDeclaration
import com.google.devtools.ksp.isAbstract
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSTypeReference
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.*

class MySymbolProcessor(
    private val environment: SymbolProcessorEnvironment,
) : SymbolProcessor {

    private val codeGenerator: CodeGenerator = environment.codeGenerator
    private val logger: KSPLogger = environment.logger

    override fun process(resolver: Resolver): List<KSAnnotated> {
        logger.info("process")
        val symbols = resolver.getSymbolsWithAnnotation(Mock::class.qualifiedName!!)
        val (validSymbols, invalidSymbols) = symbols.partition { it.validate() }
        logger.info("validSymbols: $validSymbols, invalidSymbols: $invalidSymbols")
        val props = validSymbols.filterIsInstance<KSPropertyDeclaration>()
        logger.info("props: $props")
        val types = props.map { it.type.resolve() }.toSet()
        logger.info("types: $types")
        types.forEach { it.declaration.accept(TypeReferenceVisitor(), Unit) }
        val parents = props.mapNotNull { it.parentDeclaration }.toSet()
        logger.info("parents: $parents")
        return invalidSymbols
    }

    inner class TypeReferenceVisitor : KSVisitorVoid() {
        override fun visitTypeReference(typeReference: KSTypeReference, data: Unit) {
            logger.info("typeReference: ${typeReference.resolve()}")
            val type = typeReference.resolve()
            val packageName = type.declaration.packageName.asString()
            val targetFqn = type.declaration.qualifiedName?.asString()!!
            val targetClassName = type.declaration.simpleName.asString()
            val fileName = "${targetClassName}Mock"
            val className = "${targetClassName}Mock"
            val abstractFunctions =
                type.declaration.closestClassDeclaration()!!.getAllFunctions()
                    .filter { it.isAbstract }
            val abstractProps =
                type.declaration.closestClassDeclaration()!!.getAllProperties()
                    .filter { it.isAbstract() }
            codeGenerator.createNewFile(
                dependencies = Dependencies(true),
                packageName = packageName,
                fileName = fileName,
            ).writer().use { writer ->
                logger.info("packageName: $packageName")
                FileSpec.builder(packageName, fileName)
                    .addType(
                        TypeSpec.classBuilder(className)
                            .addSuperinterface(ClassName.bestGuess(targetFqn))
                            .addFunctions(
                                abstractFunctions
                                    .map { func ->
                                        FunSpec.builder(func.simpleName.asString())
                                            .addModifiers(KModifier.OVERRIDE)
                                            .addParameters(
                                                func.parameters.map { param ->
                                                    ParameterSpec(
                                                        param.name!!.asString(),
                                                        ClassName.bestGuess(param.type.resolve().declaration.qualifiedName!!.asString()),
                                                    )
                                                }
                                            )
                                            .build()
                                    }
                                    .asIterable()
                            )
                            .build()
                    )
                    .build()
                    .writeTo(writer)
            }
        }
    }
}
