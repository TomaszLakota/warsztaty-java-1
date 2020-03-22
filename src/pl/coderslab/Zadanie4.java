package pl.coderslab;

import java.util.Random;
import java.util.Scanner;

public class Zadanie4 {

    public static void main(String[] args) {
        while (true) {
            roll();
        }

    }

    public static void roll() {
        Scanner sn = new Scanner(System.in);
        int diceThrows, diceSides, modifier = 0;
        System.out.println("Gib string in format like 2D12-1 or D6");

        String input = sn.next();
        try {
            if (Character.isLetter(input.charAt(0))) {
                if (Character.toString(input.charAt(0)).equals("D")) {
                    diceThrows = 1;
                } else {
                    wrongFormat();
                    return;
                }
            } else {
                try {
                    String beforeD = input.split("D")[0];
                    Scanner scn = new Scanner(beforeD);
                    diceThrows = scn.nextInt();
                } catch (Exception e) {
                    wrongFormat();
                    return;
                }
            }
            String numberOfThrowsPart = input.split("D")[1].split("[+-]")[0];
            try {
                Scanner scn = new Scanner(numberOfThrowsPart);
                diceSides = scn.nextInt();
            } catch (Exception e) {
                wrongFormat();
                return;
            }

            if (!correctDiceSides(diceSides)) {
                System.out.println("incorrect number of sides");
                return;
            }

            if (input.split("D")[1].split("[+-]").length > 1) {
                String modifierPart = input.split("D")[1].split("[+-]")[1];
                try {
                    Scanner scn = new Scanner(modifierPart);
                    modifier = scn.nextInt();
                    if (input.contains("-")) {
                        if (input.contains("+")) {
                            wrongFormat();
                            return;
                        } else {
                            modifier *= -1;
                        }
                    }
                } catch (Exception e) {
                    wrongFormat();
                    return;
                }
            }

            Random random = new Random();
            int result = modifier;
            for (int i = 0; i < diceThrows; i++) {
                result += random.nextInt(diceSides) + 1;
            }

            System.out.printf("Rolled %d\n", result);

        } catch (Exception e) {
            wrongFormat();
        }
    }

    private static void wrongFormat() {
        System.out.println("bad format");
    }

    public static boolean correctDiceSides(int diceSides) {
        int[] sides = {3, 4, 6, 8, 10, 12, 20, 100};
        for (int side : sides) {
            if (side == diceSides) {
                return true;
            }
        }
        return false;
    }
}
