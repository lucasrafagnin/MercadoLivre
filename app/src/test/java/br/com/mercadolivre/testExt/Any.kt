package br.com.mercadolivre.testExt

fun Any.readJson(name: String) = readFile("json/$name.json")

fun Any.readFile(path: String) = javaClass.classLoader!!
        .getResourceAsStream(path)
        .bufferedReader()
        .use { it.readText() }

inline fun <reified T> assert(actual: T?, expected: T?) = kotlin.assert(actual == expected) {
    "Assertion failed. the value:\n$actual\ndoes not match the:\n$expected\nclass ${T::class}"
}
