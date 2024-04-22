import services.DataServices;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class BusinessLogicHandler {
    private final ClientManager clientManager;
    private final DataServices dataServices;
    public BusinessLogicHandler(DataServices dataServices, ClientManager clientManager) {
        this.clientManager = clientManager;
        this.dataServices = dataServices;
    }

    public void messagesFromClientHandler (String messagesFromClient) throws IOException {
        //Принимаем комманду, и пытаемся ее распознать
        if(messagesFromClient.charAt(0) != '@'){
            clientManager.messageToClient(
                    BusinessLogicAnswers.badFirstSymbol());
            return;
        }

        //Знаем что команда идет первой, а за ней идут аргументы, разделенные :
        String[] commandAndArguments = splitArguments(messagesFromClient, 2);
        String command = commandAndArguments[0].toLowerCase();
        String arguments = commandAndArguments.length > 1 ? commandAndArguments[1] : "";

        switch (command){
            case "@login":
                handlerLogin(arguments);
                break;

            case "@session":
                handlerLoginBySession(arguments);
                break;

            case "@exit":
                break;

            case "@streamon":
                break;
            case "@streamoff":
                break;

            case "@msg":
                break;
            case "@private":
                break;

            case "@getlast":
                break;
            case "@getbetween":
                break;
            case "@get100":
                break;

            case "@edit":
                break;

            case "@":
            default:
                clientManager.messageToClient(
                        BusinessLogicAnswers.commandNotFound());
                break;
        }
        clientManager.broadcastMessageToAll("return : " + messagesFromClient);
    }

    //Обработчик. Вход по логину и паролю
    private void handlerLogin(String argumentsString) throws IOException{
        String[] arguments = splitArguments(argumentsString,2);
        if(arguments.length < 2){
            clientManager.messageToClient(BusinessLogicAnswers.needArguments());
            return;
        }
        String SID = dataServices.userSessionService.authentication(
                Integer.parseInt(arguments[0])
                , arguments[1]
                , clientManager.clientIPAddress);
        if(SID != null){
            clientManager.setSID(SID);
            clientManager.messageToClient(SID);
        } else {
            clientManager.messageToClient(BusinessLogicAnswers.badLogin());
        }
    }

    //Обработчик. Вход по уже созданной сессии
    private void handlerLoginBySession(String askedSID) throws IOException{
        String SID = dataServices.userSessionService.checkSID(askedSID);
        if(SID != null){
            clientManager.setSID(SID);
            clientManager.messageToClient(SID);
        } else {
            clientManager.messageToClient(BusinessLogicAnswers.badSession());
        }
    }

    //Сплитер для аргументов
    private String[] splitArguments(String argumentsString, int count){
        return argumentsString.split(":",2);
    }
}
