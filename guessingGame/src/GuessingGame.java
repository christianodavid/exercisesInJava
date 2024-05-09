import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int secretNumber = random.nextInt(100) + 1;
        int attempts = 0;
        boolean guessedCorrectly = false;

        System.out.println("Jogo de Adivinhação!");
        System.out.println("Tente adivinhar o número secreto entre 1 e 100.");

        while (attempts < 10) {
            System.out.print("Digite sua tentativa: ");
            int attempt = scanner.nextInt();
            attempts++;

            if (attempt == secretNumber) {
                guessedCorrectly = true;
                break;
            } else if (attempt < secretNumber) {
                System.out.println("Tente um número maior.");
            } else {
                System.out.println("Tente um número menor.");
            }
        }

        if (guessedCorrectly) {
            System.out.println("Parabéns! Você acertou o número secreto.");
        } else {
            System.out.println("Você perdeu! O número secreto era: " + secretNumber);
        }

        scanner.close();
    }
}
