import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;


public class ChatServer {
    public static final int PORT = 4000;
    private ServerSocket serverSocket;
    private String message;
    private final List<ClientSocket> clients = new LinkedList<>();

    public static void main(String[] args) {
            ChatServer server = new ChatServer();
            try {
                server.start();
            } catch (IOException e) {
                System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
            }

            System.out.println("Servidor finalizado");
        }

        public void start() throws IOException {
            System.out.println("Servidor iniciado na porta " + PORT);
            serverSocket = new ServerSocket(PORT);
            clientConnectionLoop();
        }

    private void clientConnectionLoop() throws IOException {
        while (true) {
            ClientSocket clientSocket = new ClientSocket(serverSocket.accept());
            clients.add(clientSocket);
            new Thread(() ->  clientMessageLoop(clientSocket)).start();
        }
    }

    private void clientMessageLoop(ClientSocket clientSocket) {
        String message;
        try {
            while ((message = clientSocket.getMessage()) != null) {
                if ("sair".equalsIgnoreCase(message))
                    return;

                System.out.printf("Mensagem recebida do cliente %s: %s\n"
                        , clientSocket.getRemoteSocketAddress(), message);
                sendMessageToAllClients(clientSocket, message);
            }
        } finally {
            clientSocket.close();
        }
    }

    private void sendMessageToAllClients(ClientSocket sender, String message) {
        for (ClientSocket clientSocket : clients) {
            if (!sender.equals(clientSocket)) {
                clientSocket.sendMessage(message);
            }
        }
    }


}
