package com.daniel107x

import java.io.File
import kotlin.io.path.fileSize

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(args: Array<String>) {
    if(args.isEmpty()) throw IllegalArgumentException("No args provided")
    if(args.size < 2) throw IllegalArgumentException("Please provide an option and the file to process")
    val option = args[0]
    val fileName = args[1]
    val file = validateFile(fileName)
    val result = when(option){
        "-c" -> countBytesInFile(file)
        "-l" -> countLinesInFile(file)
        "-w" -> countWordsInFile(file)
        "-m" -> countCharsInFile(file)
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

fun countBytesInFile(file: File): Long {
    return file.toPath().fileSize()
}

fun countLinesInFile(file: File): Int {
    var lines = 0
    file.forEachLine { line -> lines++ }
    return lines
}

fun countWordsInFile(file: File): Int {
    val text = file.readText();
    val words = text.split("\\s+".toRegex()).filter { word -> word.isNotBlank() } // We use the regex \\s+ as space separator
    return words.size
}

fun countCharsInFile(file: File): Int {
    var text = file.readText()
    return text.length
}