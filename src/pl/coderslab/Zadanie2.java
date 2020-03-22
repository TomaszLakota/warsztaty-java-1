package pl.coderslab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Zadanie2 {

    public static void playGame() {
        int n, random;
        List<Integer> userNumbers = new ArrayList<>();
        List<Integer> randomNumbers = new ArrayList<>();

        while (randomNumbers.size() < 6) {
            random = ThreadLocalRandom.current().nextInt(1, 49 + 1);
            if (!randomNumbers.contains(random)) {
                randomNumbers.add(random);
            }
        }

        while (userNumbers.size() < 6) {
            n = getIntFromUser(1, 49);
            if (userNumbers.contains(n)) {
                System.out.println("Ta liczba już była");
            } else {
                userNumbers.add(n);
            }
        }

        Collections.sort(userNumbers);
        Collections.sort(randomNumbers);

        System.out.println("Twoje liczby:");
        System.out.println(userNumbers.toString());
        System.out.println("Wylosowane liczby:");
        System.out.println(randomNumbers.toString());
        int correctGuesses = 0;
        for (int i = 0; i < 6; i++) {
            if (userNumbers.contains(randomNumbers.get(i))) {
                correctGuesses++;
            }
        }
        if (correctGuesses < 3) {
            System.out.println("Przegrałeś");
        } else {
            System.out.println("Trafiłeś " + correctGuesses);
        }
    }

    public static int getIntFromUser(int min, int max) {
        Scanner sn = new Scanner(System.in);
        boolean correct = false;
        int n = 0;
        while (!correct) {
            try {
                System.out.println("Podaj liczbę z zakresu od " + min + " do " + max);
                n = sn.nextInt();
                if (n >= min && n <= max) {
                    correct = true;
                } else {
                    System.out.println("nie zawiera się w przedziale");
                }
            } catch (Exception e) {
                sn.next();
                System.out.println("To nie jest liczba");
            }
        }
        return n;
    }

    public static void main(String[] args) {
        playGame();
    }
}
