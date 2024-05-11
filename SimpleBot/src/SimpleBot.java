import java.time.Year;
import java.util.Scanner;

public class SimpleBot {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        introducingBot();
        System.out.println();
        introducingPerson();
        System.out.println();
        guessAge();
        System.out.println();
        count();
        System.out.println();
        quiz();
        System.out.println();
        end();
        scanner.close();
    }

    static int getCurrentYear() {
        return Year.now().getValue();
    }

    static void introducingBot() {
        int currentYear = getCurrentYear();
        System.out.println("Olá! Meu nome é Marina");
        System.out.println("Eu fui criada em " + currentYear + ".");
    }

    static void introducingPerson() {
        System.out.println("Qual o teu nome?");
        String personName = scanner.nextLine();
        System.out.println("Prazer em te conhecer, " + personName + ".");
    }

    static void guessAge() {
        System.out.println("Deixe-me adivinhar tua idade!!!");
        System.out.println("Diga-me o resto da divisão da tua idade por 3, 5 e 7.");
        System.out.print("Por 3: ");
        int remainder3 = scanner.nextInt();
        System.out.print("Por 5: ");
        int remainder5 = scanner.nextInt();
        System.out.print("Por 7: ");
        int remainder7 = scanner.nextInt();
        int age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;
        System.out.println("Tua idade é " + age + "!");
    }

    static void count() {
        System.out.println("Eu posso contar até o número que tu quiseres!");
        System.out.print("Digite um número: ");
        int number = scanner.nextInt();
        for (int i = 0; i <= number; i++) {
            System.out.printf("%d\n", i);
        }
    }

    static void quiz() {
        System.out.println("Vamos testar seus conhecimentos de programação.");
        System.out.println("Qual afirmação é a mais completa sobre break e continue?");
        System.out.println("1. A palavra-chave break pode ser escrita no corpo de loops while e do-while.");
        System.out.println("2. A palavra-chave continue não pode ser escrita no corpo de loops while e do-while.");
        System.out.println("3. Ambas as palavras-chave podem ser escritas no corpo de loops for, while e do-while.");
        System.out.println("4. As palavras-chave break e continue podem ser escritas na declaração condicional if-else.");
        int answer = 3;
        System.out.print("Resposta: ");
        int guess = scanner.nextInt();
        while (guess != answer) {
            System.out.println("Por favor, tente novamente.");
            System.out.print("Resposta: ");
            guess = scanner.nextInt();
        }
    }

    static void end() {
        System.out.println("Parabéns! Tenha um ótimo dia.");
    }
}
