package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Dictionary {
    private List<String> listWords = new ArrayList<>();

    public Dictionary() throws FileNotFoundException {      //create words for game
        File file = new File("dictionary");       //take all words from file
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            listWords.add(scanner.nextLine());
        }
        scanner.close();
    }

    public String getOneWord() {
        Random random = new Random();
        int index = random.nextInt(listWords.size());
        return listWords.get(index);
    }

}
