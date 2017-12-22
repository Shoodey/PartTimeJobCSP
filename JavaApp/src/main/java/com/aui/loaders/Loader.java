package com.aui.loaders;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Loader {

    static Scanner scanner;

    /**
     * Initializes the scanner to read the csv file
     *
     * @param file The file's relative path and name
     * @return Scanner
     * @throws FileNotFoundException
     */
    static Scanner init(String file) throws FileNotFoundException {
        scanner = new Scanner(new File(file)).useDelimiter(",");
        return scanner;
    }

    /**
     * Skips the first header line
     */
    static void skipHeaders() {
        scanner.nextLine();
    }

}
