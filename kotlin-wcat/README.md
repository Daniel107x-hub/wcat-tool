# WCAT Tool

A lightweight command-line tool written in Kotlin that replicates some functionality of the Unix `wc` command. This project is currently in its early stages and supports the following options:

- `-c`: Prints the number of bytes in the file.
- `-w`: Prints the number of words in the file.
- `-l`: Prints the number of lines in the file.

## Usage

```bash
./wcat [options] <filename>
````

### Options

| Option | Description          |
| ------ | -------------------- |
| `-c`   | Print the byte count |
| `-w`   | Print the word count |
| `-l`   | Print the line count |

You can combine options:

```bash
./wcat -c -w myfile.txt
```

## Example

```bash
./wcat -l -w example.txt
12  87
```

Output order follows the order of arguments passed.

## Build & Run

Ensure you have [Kotlin](https://kotlinlang.org/docs/command-line.html) installed.

To build and run:

```bash
kotlinc Wcat.kt -include-runtime -d wcat.jar
java -jar wcat.jar -l -w sample.txt
```

## Project Structure

```
.
├── Wcat.kt
└── README.md
```

## License

This project is licensed under the MIT License.