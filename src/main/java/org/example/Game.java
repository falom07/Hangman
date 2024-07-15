package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    static int currentHangman; //state hangman
    static String word;        //Current word for game

    public static void play() throws FileNotFoundException{
        char symbol = 34;   // it is symbol -> "
        int winGame = 0;    //how much win game
        int loseGame = 0;   //how much lose game
        while (true) {
            Dictionary dictionary = new Dictionary();
            word = dictionary.getOneWord();    //take word for game

            Scanner scanner = new Scanner(System.in);
            System.out.print("\nDo you want play or not? If yes write "
                            + symbol + "YES" + symbol + " if not write anything else: ");
            String answer = scanner.nextLine().toLowerCase();    //take answer for begin or end game

            if (answer.equals("yes")) {            //check answer
                boolean beginGame= beginGame();    //begin game
                if (beginGame) {                   //text for win
                    System.out.println("\n\n=========================\nYou win!!!\nYour word: " + word);
                    winGame++;
                } else {                           //text for lose
                    System.out.println("\n\n=========================\nYou lose...\nYour word was: " + word);
                    loseGame++;
                }
            } else {                               //end game
                scanner.close();
                System.out.println("\nWin game: " + winGame + "\nLose game: " + loseGame);
                break;                             //end game
            }
        }
    }

    public static boolean beginGame(){
        List<String>openLetters = takeTwoRandomLetter(); //take two random letter
        List<String>wrongLetter = new ArrayList<>();     //list for wrong letter,when user write two the same wrong letter we don't think it like mistake
        int mistake = 0;
        Scanner scanner = new Scanner(System.in);
        while (mistake < 6) {   //work until user makes 6 mistakes
            System.out.print("\nEnter possible letter: ");
            String userLetter = scanner.nextLine().toLowerCase(); //enter letter

            if (!"abcdefghijklmnopqrstuvwxyz".contains(userLetter)) {   //if you write letter not from english alphabet,then just try to write again
                System.out.println("Invalid letter!!!");
                continue;
            }
            if (wrongLetter.contains(userLetter)) {
                System.out.println("This is wrong letter,but you have already written it ");
                continue;
            }

            if (word.contains(userLetter)) {    // if this letter exist than add to list with have already guessed letter and call hangman
                openLetters.add(userLetter);
                callHangman(false);
            } else {    // if this letter does not exist than ++mistake and call hangman
                mistake++;
                callHangman(true);
                wrongLetter.add(userLetter);
            }
            System.out.println("\nMistake: " + mistake + "\n=========================\n");
            int end = writeLetter(openLetters); //write letters
            if (end == 0) {                     //if end = 0 than your have already guessed all words
                return true;
            }
        }
        return false;
    }

    public static List<String> takeTwoRandomLetter(){   //add two random letter for begin
        Random random = new Random();
        List <String> listLetters = new ArrayList<>();  //create List for 2 letter
        listLetters.add("");
        listLetters.add("");

        for (int i = 0; i < 2; i++) {
            int index = random.nextInt(word.length());              //random index for letter
            listLetters.set(i,word.substring(index,index+1));       //take letter with our random index
            if (listLetters.get(1).equals(listLetters.get(0)) ){    //if letters the same,do one more iteration
                i--;
            }
        }
        return listLetters;
    }
    public static int writeLetter(List<String> open_letters){   //write letters
        System.out.println();
        int end = 0;
        for(int i = 0; i < word.length(); i++) {   //check all word
            if (open_letters.contains(word.substring(i,i+1))) {    //check if we have already guessed letter,if yes output
                System.out.print(" " + word.substring(i,i+1) + " ");
            }else {
                end = 1;
                System.out.print(" _ ");
            }
        }
        return end;    //if end remain 0 it means that we have already guessed all letter
    }

    public static void callHangman(boolean nextHangman){    //write current hang man
        if (nextHangman) {
            ++currentHangman;
        }
        switch (currentHangman) {
            case 0:
                startHangman();
                break;
            case 1:
                firstHangman();
                break;
            case 2:
                secondHangman();
                break;
            case 3:
                thirdHangman();
                break;
            case 4:
                fourthHangman();
                break;
            case 5:
                fifthHangman();
                break;
            case 6:
                deadHangman();
                currentHangman = 0;
                break;
            default:
                startHangman();
        }
    }
    public static void startHangman(){  //get ready hangman
        System.out.println("______\n|    |\n     |\n     |\n     |\n ____|__");
    }
    public static void firstHangman(){  //first hangman
        System.out.println("______\n\\    |\n ()  |\n     |\n     |\n ____|__");
    }
    public static void secondHangman(){
        System.out.println("______\n\\    |\n ()  |\n []  |\n     |\n ____|__");
    }
    public static void thirdHangman(){
        System.out.println("______\n\\    |\n ()  |\n/[]  |\n     |\n ____|__");
    }
    public static void fourthHangman(){
        System.out.println("______\n\\    |\n ()  |\n/[]\\ |\n     |\n ____|__");
    }
    public static void fifthHangman(){
        System.out.println("______\n\\    |\n ()  |\n/[]\\ |\n /   |\n ____|__");
    }
    public static void deadHangman(){
        System.out.println("______\n\\    |\n ()  |\n/[]\\ |\n /\\  |\n ____|__");
    }
}
