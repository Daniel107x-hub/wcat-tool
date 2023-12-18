package src.main.java;

import java.io.File;

/**
 * Class intended to emulate the functionality of the wc command
 */
public class CCWCTool {
    public static void main(String[] args) {
        if("-c".equals(args[0])){
            String filePath = args[1];
            File file = new File(filePath);
            if(!file.canRead()) return;
            String result = countBytes(file) + " " + file.getName();
            System.out.println(result);
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
}
