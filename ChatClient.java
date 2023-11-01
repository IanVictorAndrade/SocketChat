import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient implements Runnable {
    private final String SERVER_ADRESS = "127.0.0.1";
    private ClientSocket clientSocket;
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
        try {
            clientSocket = new ClientSocket(new Socket(SERVER_ADRESS, ChatServer.PORT));
            System.out.println("Cliente conectado ao servidor " + SERVER_ADRESS + ":" + ChatServer.PORT);
            new Thread(this).start();
            messageLoop();
        } finally {
            clientSocket.close();
        }
    }

    @Override
    public void run() {
        String message;
        while ((message = clientSocket.getMessage()) != null) {
            System.out.printf("Mensagem recebida de outro usuário: %s\n",
                    message);
        }
    }

    private void messageLoop() throws IOException {
        String message;

        do {
            System.out.print("Digite uma mensagem (ou sair para finalizar): ");
            message = scanner.nextLine();
            clientSocket.sendMessage(message);
        } while (!message.equalsIgnoreCase("sair"));

    }

}
