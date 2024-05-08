import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double firstOperand;
        double secondOperand;
        char operator;

        while (true) {
            System.out.print("Digite o primeiro número: ");
            if (scanner.hasNextDouble()) {
                firstOperand = scanner.nextDouble();
                break;
            } else {
                System.out.println("Error: insira um número válido.");
                scanner.next();
            }
        }

        while (true) {
            System.out.print("Digite o segundo número: ");
            if (scanner.hasNextDouble()) {
                secondOperand = scanner.nextDouble();
                break;
            } else {
                System.out.println("Error: insira um número válido.");
                scanner.next();
            }

        }

        while (true) {
            System.out.print("Digite o operador (+, -, *, /): ");
            String input = scanner.next();
            if (input.length() == 1 && "+-*/".indexOf(input.charAt(0)) != -1) {
                operator = input.charAt(0);
                break;
            } else {
                System.out.println("Error: insira um operador válido.");
            }
        }

        scanner.close();

        double result;

        switch (operator) {
            case '+':
                result = firstOperand + secondOperand;
                break;
            case '-':
                result = firstOperand - secondOperand;
                break;
            case '*':
                result = firstOperand * secondOperand;
                break;
            case '/':
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    System.out.println("Error: divisão por zero.");
                    return;
                }
                break;
            default:
                System.out.println("Operação inválida.");
                return;
        }

        System.out.println(firstOperand + " " + operator + " " + secondOperand + " = " + result);
    }
}
