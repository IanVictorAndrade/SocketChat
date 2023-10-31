import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private final String SERVER_ADRESS = "127.0.0.1";
    private Socket clientSocket;
    private Scanner scanner;

    public ChatClient() {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        try {
            ChatClient client = new ChatClient();
            client.start();
        } catch (IOException e) {
            System.out.println("Erro ao iniciar o cliente: " + e.getMessage());
        }
        System.out.println("Cliente finalizado");
    }

    public void start() throws IOException {
        clientSocket = new Socket(SERVER_ADRESS, ChatServer.PORT);
        System.out.println("Cliente conectado ao servidor " + SERVER_ADRESS + ":" + ChatServer.PORT);
        messageLoop();
    }

    private void messageLoop() {
        String message = null;

        do {
            System.out.print("Digite uma mensagem (ou sair para finalizar): ");
            message = scanner.nextLine();
        } while (!message.equalsIgnoreCase("sair"));

    }

}
