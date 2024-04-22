//2024.03.29 mip24
import services.DataServices;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class Server {
    private ServerSocket serverSocket = null;
    private static final DataServices dataServices = DataServices.getInstance();

    public Server(int port){
        startDelay(1);
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

    public static DataServices getDataServices(){
        return dataServices;
    }

    public void closeServerSocket(){
        try {
            System.out.println("Close listener...");
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startDelay(int seconds){
        try {
            sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
