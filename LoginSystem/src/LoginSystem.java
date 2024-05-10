import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginSystem {

    private static final Map<String, String> users = new HashMap<>();

    public static void main(String[] args) {
        users.put("christiano", "12345");
        users.put("marina", "67890");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Login");

        while (true) {
            System.out.print("Usuário: ");
            String username = scanner.nextLine();

            System.out.print("Senha: ");
            String password = scanner.nextLine();

            if (login(username, password)) {
                System.out.println("Usuário logado!");
                break;
            } else {
                System.out.println("Nome de usuário ou senha incorretos. Tente novamente!");
            }
        }

        scanner.close();
    }

    private static boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
