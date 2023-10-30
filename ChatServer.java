import java.io.IOException;
import java.net.ServerSocket;

public class ChatServer {
        private final int PORT = 4000;
        private ServerSocket serverSocket;


        public static void main(String[] args) {
            ChatServer server = new ChatServer();
            try {
                server.start();
            } catch (IOException e) {
                System.out.println("Erro ao startar o servidor: " + e.getMessage());
            }

            System.out.println("Servidor finalizado");
        }

        public void start() throws IOException {
            System.out.println("Servidor iniciado na porta " + PORT);
            serverSocket = new ServerSocket(PORT);
        }
}
