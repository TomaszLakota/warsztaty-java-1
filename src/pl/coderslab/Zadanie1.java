package pl.coderslab;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Zadanie1 {

    public static void playGame(){
        Scanner sn = new Scanner(System.in);
        int n=-1, random;


        random = ThreadLocalRandom.current().nextInt(1, 101);
        while(n!=random){
            boolean correct = false;
            while(!correct){
                try{
                    System.out.println("Zgadnij liczbę");
                    n = sn.nextInt();
                    correct = true;
                }catch(Exception e){
                    sn.next();
                    System.out.println("To nie jest liczba");
                }
            }
            if(n>random){
                System.out.println("Za dużo");
            }
            if(n<random){
                System.out.println("Za mało");
            }
        }
        System.out.println("Zgadłeś");

    }
    public static void main(String[] args) {
	// write your code here
        playGame();
    }
}
