import java.util.Scanner;

public class CoffeeMachine {

    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;

    static Scanner scanner = new Scanner(System.in);

    enum Status {
        ESCOLHER, COMPRAR, ABASTECER, RESTANTE, SAIR
    }

    static Status curStatus = Status.ESCOLHER;

    public static void main(String[] args) {

        do {
            System.out.println();
            System.out.println(curStatus);
            System.out.println();
            System.out.println("1. Comprar");
            System.out.println("2. Abastecer");
            System.out.println("3. Restante");
            System.out.println("0. Sair");
            System.out.print("Escolha uma ação: ");
            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    curStatus = Status.COMPRAR;
                    System.out.println();
                    System.out.println(curStatus);
                    buy();
                    break;
                case 2:
                    curStatus = Status.ABASTECER;
                    System.out.println();
                    System.out.println(curStatus);
                    fill();
                    break;
                case 3:
                    curStatus = Status.RESTANTE;
                    System.out.println();
                    System.out.println(curStatus);
                    remaining();
                    break;
                case 0:
                    exit();
                    System.out.println(curStatus);
                    break;
            }
        } while (curStatus != Status.SAIR);

        scanner.close();
    }

    static void buy() {
        System.out.println();
        System.out.println("1. Espresso");
        System.out.println("2. Latte");
        System.out.println("3. Cappuccino");
        System.out.println("0. Voltar ao menu principal");
        System.out.print("Selecione uma opção: ");
        int chosenDrink = scanner.nextInt();

        switch (chosenDrink) {
            case 1:
                if (canMakeCoffee(250, 0, 16)) {
                    water -= 250;
                    beans -= 16;
                    cups--;
                }
                break;
            case 2:
                if (canMakeCoffee(350, 75, 20)) {
                    water -= 350;
                    milk -= 75;
                    beans -= 20;
                    cups--;
                }
                break;
            case 3:
                if (canMakeCoffee(200, 100, 12)) {
                    water -= 200;
                    milk -= 100;
                    beans -= 12;
                    cups--;
                }
                break;
            case 0:
                break;
        }
        curStatus = Status.ESCOLHER;
    }

    static boolean canMakeCoffee(int waterNeeded, int milkNeeded, int beansNeeded) {
        if (water >= waterNeeded && milk >= milkNeeded && beans >= beansNeeded) {
            System.out.println("Eu tenho ingredientes suficientes. Café sendo preparado...");
            System.out.println("Retire teu café!");
            return true;
        } else {
            System.out.println("Desculpe, não tenho ingredientes suficientes.");
            System.out.println("Por favor, abasteça a máquina!");
            return false;
        }
    }

    static void fill() {
        System.out.println();
        System.out.print("Digite quantos mL de água tu desejas adicionar: ");
        int waterAdded = scanner.nextInt();
        System.out.print("Digite quantos mL de leite tu desejas adicionar: ");
        int milkAdded = scanner.nextInt();
        System.out.print("Digite quantos gramas de grãos de café tu desejas adicionar: ");
        int beansAdded = scanner.nextInt();
        System.out.print("Digite quantos copos tu desejas adicionar: ");
        int cupsAdded = scanner.nextInt();

        water += waterAdded;
        milk += milkAdded;
        beans += beansAdded;
        cups += cupsAdded;

        curStatus = Status.ESCOLHER;
    }

    static void remaining() {
        System.out.println();
        System.out.println("A máquina possui:");
        System.out.println(water + "mL de água");
        System.out.println(milk + "mL de leite");
        System.out.println(beans + "g de grãos de café");
        System.out.println(cups + " copos");

        curStatus = Status.ESCOLHER;
    }

    static void exit() {
        System.out.println();
        curStatus = Status.SAIR;
    }

}
