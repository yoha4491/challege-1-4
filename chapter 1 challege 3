import java.util.Random;
import java.util.Scanner;
public class C3 {
    public static void main(String[] args) {
        int health = 100;
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        for (int room = 1; room <= 5; room++) {
            System.out.println("Entering room " + room + "...");
            int event = rand.nextInt(3) + 1;

            switch (event) {
                case 1:
                    health -= 20;
                    System.out.println("A trap sprung! Health is now " + health + ".");
                    break;
                case 2:
                    int oldHealth = health;
                    health += 15;
                    if (health > 100) {
                        health = 100;
                        System.out.println("You found a healing potion! Health is now " + oldHealth + " -> capped to 100.");
                    } else {
                        System.out.println("You found a healing potion! Health is now " + health + ".");
                    }
                    break;
                case 3:
                    System.out.println("A monster appears!");
                    int monsterNumber = rand.nextInt(5) + 1;
                    int guess;
                    do {
                        System.out.print("Guess a number (1-5) to defeat it: ");
                        guess = scanner.nextInt();
                        if (guess != monsterNumber) {
                            System.out.println("Wrong! Try again.");
                        }
                    } while (guess != monsterNumber);
                    System.out.println("You defeated the monster!");
                    break;
            }

            if (health <= 0) {
                System.out.println("You have been defeated in room " + room + ".");
                break;
            }
        }

        if (health > 0) {
            System.out.println("You cleared the dungeon! Victorious with " + health + " health!");
        }

        scanner.close();
    }
}
