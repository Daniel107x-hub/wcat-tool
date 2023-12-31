package src.main.java;

import java.io.*;
import java.util.Scanner;

/**
 * Class intended to emulate the functionality of the wc command
 */
public class CCWCTool {
    private static final String BYTE_COUNT = "-c";
    private static final String LINES_COUNT = "-l";
    private static final String WORD_COUNT = "-w";
    private static final String CHARACTER_COUNT = "-m";
    private static final String DEFAULT = "D";

    public static void main(String[] args) throws IOException {
        String option = DEFAULT;
        String filePath = null;
        String fileName = null;
        File file = null;
        InputStream stream;
        if(args.length == 1 && System.in.available() > 0){ // Only option provided and input comes from standard input
            option = args[0];
            stream = System.in;
        }else if(args.length > 1) { // Option and file are provided
            option = args[0];
            filePath = args[1];
            file = new File(filePath);
            stream = new FileInputStream(file);
            fileName = file.getName();
        }else if(args.length == 1){ // Only file is provided
            filePath = args[0];
            file = new File(filePath);
            stream = new FileInputStream(filePath);
            fileName = file.getName();
        }else{
            throw new UnsupportedOperationException("Please provide appropriate arguments");
        }
        InputStream bufferedStream = new BufferedInputStream(stream);
        switch (option)
        {
            case BYTE_COUNT:
                long byteCount = countBytes(bufferedStream);
                System.out.println(byteCount + (fileName != null ? " " + fileName : ""));
                break;

            case LINES_COUNT:
                long linesCount = countLines(bufferedStream);
                System.out.println(linesCount + (fileName != null ? " " + fileName : ""));
                break;

            case WORD_COUNT:
                long wordCount = countWords(bufferedStream);
                System.out.println(wordCount + (fileName != null ? " " + fileName : ""));
                break;

            case CHARACTER_COUNT:
                long charCount = countCharacters(bufferedStream);
                System.out.println(charCount + (fileName != null ? " " + fileName : ""));
                break;

            default:
                long bytes = countBytes(bufferedStream);
                long lines = countLines(bufferedStream);
                long words = countWords(bufferedStream);
                String result = bytes + " " + lines + " " + words + (fileName != null ? " " + fileName : "");
                System.out.println(result);
                break;
        }
    }

    private static long countBytes(InputStream stream) throws IOException {
        if(stream.markSupported()) stream.mark(Integer.MAX_VALUE);
        long count = 0;
        while(stream.read() != -1) count++;
        stream.reset();
        return count;
    }

    private static long countLines(InputStream stream) throws IOException {
        if(stream.markSupported()) stream.mark(Integer.MAX_VALUE);
        Scanner scanner = new Scanner(stream);
        long count = 0;
        while(scanner.hasNextLine()){
            count++;
            scanner.nextLine();
        }
        stream.reset();
        return count;
    }

    private static long countWords(InputStream stream) throws IOException {
        if(stream.markSupported()) stream.mark(Integer.MAX_VALUE);
        Scanner scanner = new Scanner(stream);
        long count = 0;
        while(scanner.hasNext()){
            count++;
            scanner.next();
        }
        stream.reset();
        return count;
    }

    private static long countCharacters(InputStream stream) throws IOException {
        if(stream.markSupported()) stream.mark(Integer.MAX_VALUE);
        Reader reader = new InputStreamReader(stream);
        long count = 0;
        while(reader.read() != -1){
            count++;
        }
        stream.reset();
        return count;
    }
}
