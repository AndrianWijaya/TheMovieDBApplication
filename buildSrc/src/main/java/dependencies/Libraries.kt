package dependencies

import dependencies.retrofit_okhttp.*
import dependencies.retrofit_okhttp.retrofit
import dependencies.android.*
import dependencies.kotlin.*

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.appLibraries() {
    androidCore()
    materialDesign()
    testUnit()
    androidX()
    vmLifeCycle()
    coroutine()
    gson()
    okHttp()
    retrofit()
    glide()
//    koinKotlin("3.2.0-beta-1")
    gander()
    dagger()
    androidPaging()
}