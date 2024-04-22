//2024.03.29 mip24
import services.DataServices;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientManager implements Runnable{
    private final static List<ClientManager> clients = new ArrayList<>();
    private final static List<ClientManager> streamClients = new ArrayList<>();
    private final Socket socket;
    private static final DataServices dataServices = Server.getDataServices();
    private BusinessLogicHandler businessLogicHandler;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    private int clientID;
    private static int count_ClientID = 0;


    public ClientManager(Socket socket) {
        this.socket = socket;
        setClientID();
        businessLogicHandler = new BusinessLogicHandler(dataServices, this);
    }

    public void setClientID(){
        this.clientID = ++count_ClientID;
        if (count_ClientID == Integer.MAX_VALUE){
            count_ClientID = 0;
        }
    }

    @Override
    public void run() {
        try{
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            clients.add(this);
            String clientAddress = socket.getInetAddress().getHostAddress();
            name = bufferedReader.readLine();
            //Обрезаем анонимное имя до 100 символов
            if(name.length() > 100){
                name = name.substring(0, 100);
            }
            StringBuilder nameBuilder = new StringBuilder("ClientID: " + clientID);
            nameBuilder.append(", name: " + name);
            nameBuilder.append(" - ip: " + clientAddress);

            name = nameBuilder.toString();

            printServerLog("User: " + name + " is connected.");

            //Начинаем слушать все запросы клиента
            listenMessagesFromClient();

        } catch (IOException e){
        } finally {
            closeEverything();
        }
    }

    private void listenMessagesFromClient() throws IOException{
        String messagesFromClient;
        while (socket.isConnected()){
            messagesFromClient = bufferedReader.readLine();
            printServerLog(String.format("[%s] -> Server: %s", this.name, messagesFromClient));
            businessLogicHandler.messagesFromClientHandler(messagesFromClient);
        }
    }

    void broadcastMessageToAll(String message) throws IOException{
        printServerLog(String.format("Server -> All Client [from %s]: %s", this.name, message));
        broadcastMessage(clients, message);
    }

    void broadcastMessageToStream(String message) throws IOException{
        printServerLog(String.format("Server -> Stream Client [from %s]: %s", this.name, message));
        broadcastMessage(streamClients, message);
    }
    void broadcastMessage(List<ClientManager> clientsList, String message) throws IOException{
        for(ClientManager client : clientsList){
            client.bufferedWriter.write(message);
            client.bufferedWriter.newLine();
            client.bufferedWriter.flush();
        }
    }

    void messageToClient(String message) throws IOException{
        printServerLog(String.format("Server -> Client [%s]: %s", this.name, message));
        this.bufferedWriter.write(message);
        this.bufferedWriter.newLine();
        this.bufferedWriter.flush();
    }

    private void exit() throws IOException {
        clients.remove(this);
        String prompt = "User: " + name + " is disconnected.";
        printServerLog(prompt);
        broadcastMessageToStream(prompt);
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

    public void printServerLog(String logMessage){
        System.out.println(logMessage);
    }
}
