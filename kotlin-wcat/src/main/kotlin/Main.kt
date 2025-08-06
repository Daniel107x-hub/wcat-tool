package com.daniel107x

import java.io.File

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(args: Array<String>) {
    if(args.isEmpty()) throw IllegalArgumentException("No args provided")
    if(args.size < 2) throw IllegalArgumentException("Please provide an option and the file to process")
    val option = args[0]
    val fileName = args[1]
    val file = validateFile(fileName)
    val result: Int = when(option){
        "-c" -> countBytesInFile(file)
        "-l" -> countLinesInFile(file)
        "-w" -> countWordsInFile(file)
        else -> {
            println("No option found")
            return
        }
    }
    println("$result $fileName")
}

fun validateFile(fileName: String): File{
    val file = File(fileName)
    if(!file.exists()) throw IllegalArgumentException("The file doesn't exist")
    return file
}

fun countBytesInFile(file: File): Int {
    var bytes = 0
    file.forEachBlock { buffer, size -> bytes += size}
    return bytes
}

fun countLinesInFile(file: File): Int {
    var lines = 0
    file.forEachLine { line -> lines++ }
    return lines
}

fun countWordsInFile(file: File): Int {
    var words = 0
    file.forEachLine { line -> if(!line.isEmpty()) words += line.trim().split(" ").size }
    return words
}