import java.io.IOException;
import java.net.ServerSocket;


public class ChatServer {
    public static final int PORT = 4000;
    private ServerSocket serverSocket;
    private String message;

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
            new Thread(() ->  clientMessageLoop(clientSocket)).start();

            System.out.println("Mensagem recebida: " + clientSocket.getRemoteSocketAddress() + ":" + message);
        }
    }

    public void clientMessageLoop(ClientSocket clientSocket) {
        String message;
        try {
            while ((message = clientSocket.getMessage()) != null) {
                if ("sair".equalsIgnoreCase(message))
                    return;

                System.out.printf("Mensagem recebida do cliente %s: %s\n", clientSocket.getRemoteSocketAddress(), message);
            }
        } finally {
            clientSocket.close();
        }
    }


}
