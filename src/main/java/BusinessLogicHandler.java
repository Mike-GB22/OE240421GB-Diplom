import services.DataServices;

import java.io.IOException;
import java.util.Locale;

public class BusinessLogicHandler {
    private final ClientManager clientManager;
    private final DataServices dataServices;
    public BusinessLogicHandler(DataServices dataServices, ClientManager clientManager) {
        this.clientManager = clientManager;
        this.dataServices = dataServices;
    }

    public void messagesFromClientHandler (String messagesFromClient) throws IOException {
        //ѕринимаем комманду, и пытаемс€ ее распознать
        if(messagesFromClient.charAt(0) != '@'){
            clientManager.messageToClient(BusinessLogicAnswers.badFirstSymbol());
            return;
        }

        //«наем что команда идет первой, а за ней идут аргументы, разделенные :
        String[] commandAndArguments = messagesFromClient.split(":", 2);
        String command = commandAndArguments[0].toLowerCase();
        String arguments = commandAndArguments.length > 1 ? commandAndArguments[1] : "";

        switch (command){
            case
        }
        clientManager.broadcastMessageToAll("command : " + command);
        clientManager.broadcastMessageToAll("arguments : " + arguments);
        clientManager.broadcastMessageToAll("return : " + messagesFromClient);
    }
}
