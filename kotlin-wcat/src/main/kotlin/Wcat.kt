package com.daniel107x

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.optional
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import java.io.File

class Wcat: CliktCommand() {
    val lines: Boolean by option("-l").flag()
    val words: Boolean by option("-w").flag()
    val bytes: Boolean by option("-c").flag()
    val chars: Boolean by option("-m").flag()
    val fileName: String? by argument(help = "File to be processed").optional()

    override fun run() {
        val text: String
        if(fileName != null) {
            val file = validateFile(fileName.toString())
            text = file.readText()
        }else{
            val lineReader = StringBuilder()
            for(line in generateSequence(::readLine)) lineReader.appendLine(line)
            text = lineReader.toString()
        }
        val isNoOptionSelected = !(lines || words || bytes || chars)
        val builder = StringBuilder()
        if(lines) builder.append(countLines(text)).append(" ")
        if(words) builder.append(countWords(text)).append(" ")
        if(bytes) builder.append(countBytes(text)).append(" ")
        if(chars) builder.append(countChars(text)).append(" ")
        if(isNoOptionSelected) builder.append(countEverything(text)).append(" ")
        if(fileName != null) builder.append(fileName)
        println(builder)
    }

    fun validateFile(fileName: String): File{
        val file = File(fileName)
        if(!file.exists()) throw IllegalArgumentException("The file doesn't exist")
        return file
    }

    fun countEverything(text: String): String{
        val builder = StringBuilder()
        builder.append(countLines(text)).append(" ")
        builder.append(countWords(text)).append(" ")
        builder.append(countBytes(text)).append(" ")
        return builder.toString()
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
}