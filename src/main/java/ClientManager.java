//2024.03.29 mip24
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientManager implements Runnable{
    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;

    private final static List<ClientManager> clients = new ArrayList<>();
    public ClientManager(Socket socket) {
        this.socket = socket;
    }

    private void listenMessagesFromClient() throws IOException{
        String messagesFromClient;
        while (socket.isConnected()){
            messagesFromClient = bufferedReader.readLine();
            broadcastMessage(name + ": "+ messagesFromClient);
        }
    }

    private void broadcastMessage(String message) throws IOException{
        System.out.println(message);
        for(ClientManager client : clients){
            if(client == this) continue;
            client.bufferedWriter.write(message);
            client.bufferedWriter.newLine();
            client.bufferedWriter.flush();
        }
    }
    private void exit() throws IOException {
        clients.remove(this);
        broadcastMessage("User: " + name + " is disconnected.");
    }

    private void closeEverything(){
        try {
            exit();
            if (bufferedWriter != null) bufferedWriter.close();
            if (bufferedReader != null) bufferedReader.close();
            if (socket != null) socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            clients.add(this);
            name = bufferedReader.readLine();
            broadcastMessage("User: " + name + " is connected.");

            listenMessagesFromClient();

        } catch (IOException e){
        } finally {
            closeEverything();
        }
    }
}
