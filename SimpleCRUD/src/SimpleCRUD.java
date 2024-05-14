import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SimpleCRUD {

    public static final String URL = "jdbc:mysql://localhost:3306/client_database";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if (connection != null) {
                System.out.println("Conexão estabelecida com sucesso!");
                Scanner scanner = new Scanner(System.in);
                int option;
                do {
                    System.out.println();
                    System.out.println("=== MENU ===");
                    System.out.println("1. Cadastrar cliente");
                    System.out.println("2. Listar clientes");
                    System.out.println("3. Atualizar cliente");
                    System.out.println("4. Excluir cliente");
                    System.out.println("0. Sair");
                    System.out.print("Escolha uma opção: ");
                    option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        case 1:
                            System.out.println();
                            insertTableData(connection, scanner);
                            break;
                        case 2:
                            System.out.println();
                            readTable(connection);
                            break;
                        case 3:
                            System.out.println();
                            updateTableData(connection, scanner);
                            break;
                        case 4:
                            System.out.println();
                            deleteClientData(connection, scanner);
                            break;
                        case 0:
                            System.out.println("Saindo do programa...");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente!");
                    }
                } while (option != 0);
                scanner.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    private static void insertTableData(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Insira os detalhes do cliente:");

        String[] fields = {"Nome", "Sobrenome", "Email", "Telefone", "Endereço", "Cidade", "Estado", "País", "CEP"};
        String[] values = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            System.out.print(fields[i] + ": ");
            values[i] = scanner.nextLine();

            if (values[i].isEmpty()) {
                System.out.println("Um ou mais campos estão vazios. Tente novamente!");
                return;
            }
        }

        insertTable(connection, values);
    }

    private static void insertTable(Connection connection, String[] values) throws SQLException {
        String SQL = "INSERT INTO clients (first_name, last_name, email, phone, address, city, state, country, postal_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(SQL)){
            for (int i = 0; i < values.length; i++) {
                statement.setString(i + 1, values[i]);
            }
            statement.executeUpdate();
            System.out.println("Registro inserido com sucesso.");
        }
    }

    private static void readTable(Connection connection) throws SQLException {
        String SQL = "SELECT * FROM clients";
        try (PreparedStatement statement = connection.prepareStatement(SQL); ResultSet resultSet = statement.executeQuery()) {
            System.out.println("Listagem de Clientes:");
            System.out.printf("%-5s %-15s %-15s %-30s %-15s %-30s %-15s %-10s %-15s %-10s%n", "ID", "Nome", "Sobrenome", "Email", "Telefone", "Endereço", "Cidade", "Estado", "País", "CEP");
            while (resultSet.next()) {
                System.out.printf("%-5s %-15s %-15s %-30s %-15s %-30s %-15s %-10s %-15s %-10s%n",
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getString("country"),
                        resultSet.getString("postal_code"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir registro: " + e.getMessage());
            throw e;
        }
    }

    private static void updateTableData(Connection connection, Scanner scanner) throws SQLException {
        try {
            System.out.println("Insira os dados solicitados:");
            System.out.print("Qual o ID do Cliente que será atualizado? ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (id < 0) {
                System.out.println("O ID do Cliente não pode ser negativo. Tente novamente!");
                return;
            }

            String[] fields = {"Nome", "Sobrenome", "Email", "Telefone", "Endereço", "Cidade", "Estado", "País", "CEP"};
            String[] values = new String[fields.length];

            for (int i = 0; i < fields.length; i++) {
                System.out.print(fields[i] + ": ");
                values[i] = scanner.nextLine();

                if (values[i].isEmpty()) {
                    System.out.println("Um ou mais campos estão vazios. Tente novamente!");
                    return;
                }
            }

            updateTable(connection, id, values);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um ID de Cliente válido.");
        }
    }

    private static void updateTable(Connection connection, int id, String[] values) throws SQLException {
        String SQL = "UPDATE clients SET first_name=?, last_name=?, email=?, phone=?, address=?, city=?, state=?, country=?, postal_code=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(SQL)) {
            for (int i = 0; i < values.length; i++) {
                statement.setString(i + 1, values[i]);
            }
            statement.setInt(values.length + 1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registro atualizado com sucesso.");
            } else {
                System.out.println("Nenhum registro foi atualizado.");
            }
        }
    }

    private static void deleteClientData(Connection connection, Scanner scanner) throws SQLException {
        try {
            System.out.println("Insira os dados solicitados:");
            System.out.print("Qual o ID do Cliente que será atualizado? ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (id < 0) {
                System.out.println("O ID do Cliente não pode ser negativo. Tente novamente!");
                return;
            }

            deleteClient(connection, id);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um ID de Cliente válido.");
        }
    }

    private static void deleteClient(Connection connection, int id) throws SQLException {
        String SQL = "DELETE FROM clients WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registro apagado com sucesso.");
            } else {
                System.out.println("Nenhum registro foi apagado.");
            }
        }
    }
}

