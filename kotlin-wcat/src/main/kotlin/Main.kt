package com.daniel107x

import java.io.File

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(args: Array<String>) {
    val text: String
    val fileName = args.filter { it.contains(".txt") }
    if(!fileName.isEmpty()) {
        val file = validateFile(fileName[0])
        text = file.readText()
    }else{
        text = readln()
    }

    var options = args.filter { arg -> arg.contains("-") && arg.length == 2 }
    if(options.isEmpty()) options = listOf("-l", "-w", "-c")
    val builder = StringBuilder()
    options.forEach { option ->
        val result = when(option){
            "-c" -> countBytes(text)
            "-l" -> countLines(text)
            "-w" -> countWords(text)
            "-m" -> countChars(text)
            else -> {
                println("No option found")
                return
            }
        }
        builder
            .append(result)
            .append(" ")
    }
    if(!fileName.isEmpty()) builder.append(fileName)
    println(builder)
}

fun validateFile(fileName: String): File{
    val file = File(fileName)
    if(!file.exists()) throw IllegalArgumentException("The file doesn't exist")
    return file
}

fun countBytes(text: String): Int {
    return text.toByteArray().size
}

fun countLines(text: String): Int {
    return text.lines().size
}

fun countWords(text: String): Int {
    val words = text.split("\\s+".toRegex()).filter { word -> word.isNotBlank() } // We use the regex \\s+ as space separator
    return words.size
}

fun countChars(text: String): Int {
    return text.length
}