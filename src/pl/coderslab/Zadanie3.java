package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Scanner;

public class Zadanie3 {

    public static void main(String[] args) {
        playADumbGame();
    }

    static void playADumbGame() {
        Scanner sn = new Scanner(System.in);
        int min = 0, max = 1000, round = 1;
        String answer = "", high = "high", low = "low", bingo = "bingo";


        System.out.println("Pick a number from 0 to 1000 and Ill guess it in 10 tries");
        while (true) {
            int guess = ((max - min) / 2) + min;

            System.out.println("Round " + round++);
            System.out.println("My guess is: " + guess);
            String[] options = {high, low, bingo};

            while (!ArrayUtils.contains(options, answer)) {
                System.out.println("enter '" + high + "' if my guess was too high, '" + low + "' if it was too low or '" + bingo + "' if i guessed:\n");
                answer = sn.next();
            }

            if (answer.equals(bingo)) {
                System.out.println("I win!");
                return;
            }
            if (answer.equals(high)) {
                max = guess-1;
            } else {
                min = guess+1;
            }
            if (min > max) {
                System.out.println("you cheated!");
                return;
            }
            answer = "";
        }
    }
}
