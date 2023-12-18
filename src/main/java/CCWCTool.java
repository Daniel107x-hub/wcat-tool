package src.main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class intended to emulate the functionality of the wc command
 */
public class CCWCTool {
    private static final String BYTE_COUNT = "-c";
    private static final String LINES_COUNT = "-l";
    private static final String WORD_COUNT = "-w";

    public static void main(String[] args) throws FileNotFoundException {
        String option = args[0];
        String filePath = args[1];
        File file = null;
        switch (option)
        {
            case BYTE_COUNT:
                file = new File(filePath);
                if(!file.canRead()) throw new FileNotFoundException();
                System.out.println(buildResponse(countBytes(file), file));
                break;

            case LINES_COUNT:
                file = new File(filePath);
                if(!file.canRead()) throw new FileNotFoundException();
                System.out.println(buildResponse(countLines(file), file));
                break;

            case WORD_COUNT:
                file = new File(filePath);
                if(!file.canRead()) throw new FileNotFoundException();
                System.out.println(buildResponse(countWords(file), file));
                break;

            default:
                System.out.println("Hello world");
        }
    }

    /**
     * This method will return the number of bytes in a file
     * @param file
     * @return Number of bytes in file
     */
    private static long countBytes(File file){
        return file.length();
    }

    /**
     * Method used to count the number of lines in a file
     * @param file
     * @return Number of lines in file
     * @throws FileNotFoundException
     */
    private static long countLines(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        long count = 0;
        while(scanner.hasNextLine()){
            count++;
            scanner.nextLine();
        }
        return count;
    }

    /**
     * this method will return the number of words ina  file
     * @param file
     * @return Number of words in file
     * @throws FileNotFoundException
     */
    private static final long countWords(File file) throws FileNotFoundException{
        Scanner scanner = new Scanner(file);
        long count = 0;
        while(scanner.hasNext()){
            count++;
            scanner.next();
        }
        return count;
    }

    private static String buildResponse(long count, File file){
        return count + " " + file.getName();
    }
}
