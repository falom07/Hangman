package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    static int current_hangman;
    public static void play() {
        char symbol = 34;// it is symbol -> "
        int winGame = 0;
        int loseGame = 0;
        while(true){
            Dictionary dictionary = new Dictionary();
            String word = dictionary.getOneWord();//take word for game

            Scanner scanner = new Scanner(System.in);
            System.out.print("\nDo you want play or not? If yes write " + symbol + "YES" + symbol + " if not write anything else: ");
            String answer = scanner.nextLine().toLowerCase();//take answer for begin or end game

            if(answer.equals("yes")){//check answer
                boolean win_game= begin_game(word);//begin game
                if(win_game){//text for win
                    System.out.println("\n\n=========================\nYou win!!!\nYour word: " + word);
                    winGame++;
                }else {//text for lose
                    System.out.println("\n\n=========================\nYou lose...\nYour word was: " + word);
                    loseGame++;
                }
            }else {//end game
                System.out.println("\nWin game: " + winGame + "\nLose game: " + loseGame);
                break;//end game
            }

        }
    }
    public static boolean begin_game(String word){
        List<String>open_letters = take_two_random_letter(word);//take two random letter
        int mistake = 0;
        while(mistake < 6){//work until user makes 6 mistakes
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter possible letter: ");
            String user_letter = scanner.nextLine().toLowerCase();//enter letter

            if(word.contains(user_letter)){// if this letter exist than add to list with have already guessed letter and call hangman
                open_letters.add(user_letter);
                call_hangman(false);
            }else {// if this letter does not exist than ++mistake and call hangman
                mistake++;
                call_hangman(true);
            }
            System.out.println("\nMistake: " + mistake + "\n=========================\n");
            int end = write_letter(open_letters,word);//write letters
            if(end == 0){//if end = 0 than your have already guessed all words
                return true;
            }
        }return false;

    }
    public static List<String> take_two_random_letter(String word) {//add two random letter for begin
        Random random = new Random();
        List <String> array_letter = new ArrayList<>();//create List for 2 letter
        array_letter.add("");
        array_letter.add("");

        for(int i = 0;i < 2;i++){
            int index = random.nextInt(word.length());//random index for letter
            array_letter.set(i,word.substring(index,index+1)) ;//take letter with our random index
            if(array_letter.get(1).equals(array_letter.get(0))){ //if letters the same,do one more iteration
                i--;
            }
        }
        return array_letter;
    }
    public static int write_letter(List<String> open_letters,String word){//write letters
        System.out.println();
        int end = 0;
        for(int i = 0;i < word.length();i++){//check all word
            if(open_letters.contains(word.substring(i,i+1))){//check if we have already guessed letter,if yes output
                System.out.print(" " + word.substring(i,i+1) + " ");
            }else {
                end = 1;
                System.out.print(" _ ");
            }
        }
        return end;//if end remain 0 it means that we have already guessed all letter
    }
    public static void call_hangman(boolean next_hangman){//write current hang man
        if(next_hangman){
            ++current_hangman;
        }
        switch (current_hangman){
            case 0:
                start_hangman();
                break;
            case 1:
                first_hangman();
                break;
            case 2:
                second_hangman();
                break;
            case 3:
                third_hangman();
                break;
            case 4:
                fourth_hangman();
                break;
            case 5:
                fifth_hangman();
                break;
            case 6:
                dead_hangman();
                current_hangman = 0;
                break;
            default:
                start_hangman();
        }
    }
    public static void start_hangman(){//get ready hangman
        System.out.println("______\n|    |\n     |\n     |\n     |\n ____|__");
    }
    public static void first_hangman(){//first hangman
        System.out.println("______\n\\    |\n ()  |\n     |\n     |\n ____|__");
    }
    public static void second_hangman(){
        System.out.println("______\n\\    |\n ()  |\n []  |\n     |\n ____|__");
    }
    public static void third_hangman(){
        System.out.println("______\n\\    |\n ()  |\n/[]  |\n     |\n ____|__");
    }
    public static void fourth_hangman(){
        System.out.println("______\n\\    |\n ()  |\n/[]\\ |\n     |\n ____|__");
    }
    public static void fifth_hangman(){
        System.out.println("______\n\\    |\n ()  |\n/[]\\ |\n /   |\n ____|__");
    }
    public static void dead_hangman(){
        System.out.println("______\n\\    |\n ()  |\n/[]\\ |\n /\\  |\n ____|__");
    }
}
