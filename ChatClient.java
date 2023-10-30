import java.io.IOException;
import java.net.ServerSocket;

public class ChatClient {


    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        try {
            client.start();
        } catch (IOException e) {
            System.out.println("Erro ao startar o cliente: " + e.getMessage());
        }
    }

    public void start() throws IOException {
        ChatServer server = new ChatServer();
        server.start();
    }
}
