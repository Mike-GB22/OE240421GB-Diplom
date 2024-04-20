//2024.03.29 mip24
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket = null;
    public Server(int port){
        try {
            System.out.format("Start listener on port: %d... %n", port);
            serverSocket = new ServerSocket(port);
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientManager clientManager = new ClientManager(socket);
                Thread thread = new Thread(clientManager);
                thread.start();
            }
        } catch (IOException e) {
        } finally {
            closeServerSocket();
        }
    }

    public void closeServerSocket(){
        try {
            System.out.println("Close listener...");
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
