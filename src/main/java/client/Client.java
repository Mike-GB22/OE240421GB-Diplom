//2024.03.29 mip24
package client;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private String name;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    boolean isClientRun = false;

    public Client(String address, int port){
        try {
            System.out.format("Open %s :%d ...%n", address, port);
            socket = new Socket(address, port);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input your name: ");
            isClientRun = true;
            sendMessage(name = scanner.nextLine());
            listenForMessagesFromServer();
            listenForMessagesFromClient();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeEverything("exit");
        }
    }

    public void sendMessage(String message) throws IOException{
        bufferedWriter.write(message);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    public void listenForMessagesFromServer() throws IOException{
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromServer;
                System.out.println("Staring listener messages from Server...");
                while(isClientRun && socket.isConnected()){
                    try {
                        messageFromServer = bufferedReader.readLine();
                        System.out.println(messageFromServer);
                    } catch (IOException e) {
                        closeEverything("messageFromServer");

                    } catch (Exception e) {
                        closeEverything("общая ошибка");
                    }
                }
            }
        }).start();
    }

    public void listenForMessagesFromClient() throws IOException{
        Scanner scanner = new Scanner(System.in);
        String messagesToServer;
        while (socket.isConnected() && isClientRun){
            messagesToServer = scanner.nextLine();
            if(messagesToServer == null
                || messagesToServer == ""
                || messagesToServer == "\n"
                || messagesToServer == "\r"
                || messagesToServer == "\n\r") closeEverything("User selected EXIT.");

            sendMessage(messagesToServer);
        }
    }

    public void closeEverything(String text){
        isClientRun = false;
        System.out.print(text + " - ");
        closeEverything();
    }

    public void closeEverything(){
        isClientRun = false;
        System.out.println("Close everything...");
        try {
            if (bufferedReader != null) bufferedReader.close();
            if (bufferedWriter != null) bufferedWriter.close();
            if (socket != null) socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
