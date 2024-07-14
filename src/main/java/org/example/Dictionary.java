package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dictionary {
    private  List<String> listWords = new ArrayList<>();//list with words for game
    public Dictionary() {//create words for game
        listWords.add("elephant");
        listWords.add("computer");
        listWords.add("building");
        listWords.add("backpack");
        listWords.add("umbrella");
        listWords.add("telescope");
        listWords.add("furniture");
        listWords.add("airplane");
        listWords.add("waterfall");
        listWords.add("headphones");
        listWords.add("microphone");
        listWords.add("landscape");
        listWords.add("butterfly");
        listWords.add("generation");
        listWords.add("telephone");
        listWords.add("adventure");
        listWords.add("watermelon");
    }
    public String getOneWord(){//take one word for game
        Random random = new Random();
        int index = random.nextInt(listWords.size());
        return listWords.get(index);
    }

}
