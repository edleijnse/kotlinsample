import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {

    val current = java.io.File(".").canonicalPath
    println("Current dir:" + current)

    val inputStream: InputStream = File(current + "/src/main/kotlin/" + "kotlination.txt").inputStream()

    val inputString = inputStream.bufferedReader().use { it.readText() }

    println(inputString)
}