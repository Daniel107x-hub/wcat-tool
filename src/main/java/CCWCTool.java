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
    private static final String CHARACTER_COUNT = "-m";
    private static final String DEFAULT = "D";

    public static void main(String[] args) throws FileNotFoundException {
        String option = DEFAULT;
        String filePath = null;
        if(args.length > 1){
            option = args[0];
            filePath = args[1];
        }else{
            filePath = args[0];
            Scanner scanner = new Scanner(System.in);
            String text = scanner.toString();
        }
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

            case CHARACTER_COUNT:
                file = new File(filePath);
                if(!file.canRead()) throw new FileNotFoundException();
                System.out.println(buildResponse(countCharacters(file), file));
                break;

            default:
                file = new File(filePath);
                if(!file.canRead()) throw new FileNotFoundException();
                long bytes = countBytes(file);
                long lines = countLines(file);
                long words = countWords(file);
                String result = bytes + " " + lines + " " + words + " " + file.getName();
                System.out.println(result);
                break;
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
    private static long countWords(File file) throws FileNotFoundException{
        Scanner scanner = new Scanner(file);
        long count = 0;
        while(scanner.hasNext()){
            count++;
            scanner.next();
        }
        return count;
    }

    /**
     * Method to count number of characters in a file
     * @param file
     * @return Number of characters
     * @throws FileNotFoundException
     */
    private static long countCharacters(File file) throws FileNotFoundException{
        Scanner scanner = new Scanner(file);
        long count = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            count += line.length();
            count += 2; // 2 characters for new line
        }
        return count;
    }

    private static String buildResponse(long count, File file){
        return count + " " + file.getName();
    }
}
