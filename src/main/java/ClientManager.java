//2024.03.29 mip24
import services.DataServices;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientManager implements Runnable{
    private final static List<ClientManager> clients = new ArrayList<>();
    private final static List<ClientManager> streamClients = new ArrayList<>();
    private final Socket socket;
    public final String clientIPAddress;
    private static final DataServices dataServices = Server.getDataServices();
    private BusinessLogicHandler businessLogicHandler;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String nameOrSID;
    private int clientID;
    private static int count_ClientID = 0;


    public ClientManager(Socket socket) {
        this.socket = socket;
        clientIPAddress = socket.getInetAddress().getHostAddress();
        setClientID();

        businessLogicHandler = new BusinessLogicHandler(dataServices, this);
    }

    public void setClientID(){
        this.clientID = ++count_ClientID;
        if (count_ClientID == Integer.MAX_VALUE){
            count_ClientID = 0;
        }
    }

    public void setSID(String SID){
        this.nameOrSID = SID;
    }

    @Override
    public void run() {
        try{
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            clients.add(this);
            nameOrSID = bufferedReader.readLine();
            //Обрезаем анонимное имя до 100 символов
            if(nameOrSID.length() > 100){
                nameOrSID = nameOrSID.substring(0, 100);
            }
            StringBuilder nameBuilder = new StringBuilder("ClientID: " + clientID);
            nameBuilder.append(", name: " + nameOrSID);
            nameBuilder.append(" - ip: " + clientIPAddress);

            nameOrSID = nameBuilder.toString();

            printServerLog("User: " + nameOrSID + " is connected.");

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
            printServerLog(String.format("[%s] -> Server: %s", this.nameOrSID, messagesFromClient));
            businessLogicHandler.messagesFromClientHandler(messagesFromClient);
        }
    }

    void broadcastMessageToAll(String message) throws IOException{
        printServerLog(String.format("Server -> All Client [from %s]: %s", this.nameOrSID, message));
        broadcastMessage(clients, message);
    }

    void broadcastMessageToStream(String message) throws IOException{
        printServerLog(String.format("Server -> Stream Client [from %s]: %s", this.nameOrSID, message));
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
        printServerLog(String.format("Server -> Client [%s]: %s", this.nameOrSID, message));
        this.bufferedWriter.write(message);
        this.bufferedWriter.newLine();
        this.bufferedWriter.flush();
    }

    private void exit() throws IOException {
        clients.remove(this);
        String prompt = "User: " + nameOrSID + " is disconnected.";
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
