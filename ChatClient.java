import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class ChatClient {
    private final String SERVER_ADRESS = "127.0.0.1";
    private Socket clientSocket;
    private Scanner scanner;
    private PrintWriter out;

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
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        System.out.println("Cliente conectado ao servidor " + SERVER_ADRESS + ":" + ChatServer.PORT);
        messageLoop();
    }

    private void messageLoop() throws IOException {
        String message = null;

        do {
            System.out.print("Digite uma mensagem (ou sair para finalizar): ");
            message = scanner.nextLine();
            out.println(message);
        } while (!message.equalsIgnoreCase("sair"));

    }

}
