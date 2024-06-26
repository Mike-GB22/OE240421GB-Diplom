import DAO.Message;
import DAO.User;
import DAO.UserSession;
import services.DataServices;

import java.io.IOException;
import java.util.List;

public class BusinessLogicHandler {
    private final ClientManager clientManager;
    private final DataServices dataServices;
    public BusinessLogicHandler(DataServices dataServices, ClientManager clientManager) {
        this.clientManager = clientManager;
        this.dataServices = dataServices;
    }

    public void messagesFromClientHandler (String messagesFromClient) throws IOException {
        //��������� ��������, � �������� �� ����������
        if(messagesFromClient.charAt(0) != '@'){
            clientManager.messageToClient(
                    BusinessLogicAnswers.badFirstSymbol());
            return;
        }

        //����� ��� ������� ���� ������, � �� ��� ���� ���������, ����������� :
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


            case "@endsession":
                handlerEndSession();
                break;

            case "@onlyexit":
                handlerEndSession();
                handlerExit();
                break;

            case "@exit":
                handlerExit();
                break;

            case "@streamon":
                handlerStreamOn();
                break;
            case "@streamoff":
                handlerStreamOff();
                break;

            case "@msg":
                handlerMsg(arguments);
                break;
            case "@private":
                handlerMsgPrivate(arguments);
                break;
            case "@edit":
                handlerMsgEdit(arguments);
                break;
            case "@del":
                handlerMsgDel(arguments);
                break;

                
            case "@get":
                handlerGetMsg(arguments);
                break;
            case "@getarray":
                handlerGetManyMsgs(arguments);
                break;
            case "@getlast100":
                handlerGetLast100Msgs();
                break;
            case "@getlastform":
                handlerGetLastMsgsFrom(arguments);
                break;
            case "@getbetween":
                break;


            case "@adduser":
                handlerAddNewUser(arguments);
                break;
            case "@renameuser":
                handlerRenameUser(arguments);
                break;
            case "@newpass":
                handlerNewPass(arguments);
                break;
                
            case "@printusers":
                dataServices.userService.print();
                break;
            case "@printsessions":
                dataServices.userSessionService.print();
                break;
            case "@printmessages":
                dataServices.messageService.print();
                break;
            case "@printsize":
                dataServices.printSize();
                break;

            case "@":
            default:
                clientManager.messageToClient(
                        BusinessLogicAnswers.commandNotFound());
                break;
        }
        //clientManager.broadcastMessageToAll("return : " + messagesFromClient);
    }

    //���������� ������ ������������
    private void handlerAddNewUser(String argumentsString) throws IOException {
        String[] arguments = splitArguments(argumentsString,3);
        if(arguments.length < 2) {
            clientManager.messageToClient(BusinessLogicAnswers.needArguments());
            return;
        }
        String name = arguments[0];
        String pass = arguments[1];
        boolean isAdminFlag = false;
        if(arguments.length == 3){
            isAdminFlag = Boolean.parseBoolean(arguments[2]);
        }
        User user = dataServices.userService.newUser(name, pass, isAdminFlag, clientManager.getUserID());
        if(user == null){
            clientManager.messageToClient(BusinessLogicAnswers.bad());
        } else {
            clientManager.messageToClient(user.toJson());
        }
    }

    //��������� ����� ������������
    private void handlerRenameUser(String argumentsString) throws IOException {
        String[] arguments = splitArguments(argumentsString,2);
        if(arguments.length < 2) {
            clientManager.messageToClient(BusinessLogicAnswers.needArguments());
            return;
        }
        int userIdForChangeName = Integer.parseInt(arguments[0]);
        String newUserName = arguments[1];

        User user = dataServices.userService.renameUser(userIdForChangeName, newUserName, clientManager.getUserID());
        if(user == null){
            clientManager.messageToClient(BusinessLogicAnswers.bad());
        } else {
            clientManager.messageToClient(user.toJson());
        }
    }

    private void handlerNewPass(String argumentsString) throws IOException {
        String[] arguments = splitArguments(argumentsString,2);
        if(arguments.length < 2) {
            clientManager.messageToClient(BusinessLogicAnswers.needArguments());
            return;
        }
        User user = dataServices.userService.newPass(Integer.parseInt(arguments[0]), arguments[1], clientManager.getUserID());
        if(user == null){
            clientManager.messageToClient(BusinessLogicAnswers.bad());
        } else {
            clientManager.messageToClient(user.toJson());
        }
    }


    //�������������� ���������
    private void handlerMsgEdit(String argumentsString) throws IOException{
        String[] arguments = splitArguments(argumentsString,2);
        if(arguments.length < 2) {
            clientManager.messageToClient(BusinessLogicAnswers.needArguments());
            return;
        }
        String msgIDForEdit = arguments[0];
        String[] topicAndMsg = splitTopicMsg(arguments[1]);
        if(topicAndMsg.length < 2) {
            clientManager.messageToClient(BusinessLogicAnswers.needArguments());
            return;
        } try {
            Message msg = dataServices.messageService.editMessage(
                    msgIDForEdit
                    , topicAndMsg[0], topicAndMsg[1]);

            //clientManager.messageToClient(msg.toJson());
            clientManager.privateMessageToStream(clientManager.getUserID(),msg.toJson());
        } catch (Exception e) {
            clientManager.messageToClient(BusinessLogicAnswers.bad());
        }
    }


    //�������� ��������� �� ��� ��
    private void handlerMsgDel(String messageID) throws IOException{
        try {
            Message msg = dataServices.messageService.deleteMessage(messageID);
            if(msg == null) {
                clientManager.messageToClient(BusinessLogicAnswers.badMessageID());
            }
        } catch (Exception e){
            clientManager.messageToClient(BusinessLogicAnswers.badMessageNotDeleted());
        }
    }


    //��������� ������ ��������� �� ��� �������������
    private void handlerGetMsg(String messageID) throws IOException{
        Message msg = dataServices.messageService.getMessages(messageID);
        try {
            clientManager.messageToClient(msg.toJson());
        } catch (Exception e){
            clientManager.messageToClient(BusinessLogicAnswers.badMessageID());
        }
    }

    //��������� ������ ��������� �� ������ ��������������
    private void handlerGetManyMsgs(String argumentsString) throws IOException{
        String[] messageIDs = splitArguments(argumentsString,1001);
        List<Message> msgs = dataServices.messageService.getMessages(messageIDs);
        for (Message msg : msgs) {
            try {
                clientManager.messageToClient(msg.toJson());
            } catch (Exception e) {
                clientManager.messageToClient(BusinessLogicAnswers.badMessageID());
            }
        }
    }

    //��������� 100 ��������� ���������
    private void handlerGetLast100Msgs() throws IOException {
        List<Message> msgs = dataServices.messageService.getLast100Messages();
        for (Message msg : msgs) {
            try {
                clientManager.messageToClient(msg.toJson());
            } catch (Exception e) {
                clientManager.messageToClient(BusinessLogicAnswers.badMessageID());
            }
        }
    }

    //��������� ���� ��������� ��������� � ���������� �������
    private void handlerGetLastMsgsFrom(String messageID) throws IOException{
        List<Message> msgs = dataServices.messageService.getLastMessagesForm(messageID);
        for (Message msg : msgs) {
            try {
                clientManager.messageToClient(msg.toJson());
            } catch (Exception e) {
                clientManager.messageToClient(BusinessLogicAnswers.badMessageID());
            }
        }
    }

    ///���������� ���������� ���������
    private void handlerMsgPrivate(String argumentsString) throws IOException{
        String[] arguments = splitArguments(argumentsString,2);
        if(arguments.length < 2) {
            clientManager.messageToClient(BusinessLogicAnswers.needArguments());
            return;
        }
        int thisMsgIsFurUserID = Integer.parseInt(arguments[0]);
        String[] topicAndMsg = splitTopicMsg(arguments[1]);
        if(topicAndMsg.length < 2) {
            clientManager.messageToClient(BusinessLogicAnswers.needArguments());
            return;
        } try {
            Message msg = dataServices.messageService.newPrivateMessage(
                    clientManager.getUserSession().getUser_id()
                    , topicAndMsg[0], topicAndMsg[1]
                    , thisMsgIsFurUserID);

            //clientManager.messageToClient(msg.toJson());
            clientManager.privateMessageToStream(thisMsgIsFurUserID,msg.toJson());
            clientManager.privateMessageToStream(clientManager.getUserID(),msg.toJson());

        } catch (Exception e) {
            clientManager.messageToClient(BusinessLogicAnswers.bad());
        }
    }

    //���������� ���������
    private void handlerMsg(String argumentsString) throws IOException{
        String[] topicAndMsg = splitTopicMsg(argumentsString);
        if(topicAndMsg.length < 2){
            clientManager.messageToClient(BusinessLogicAnswers.needArguments());
            return;
        } try {
            Message msg = dataServices.messageService.newMessage(
                clientManager.getUserSession().getUser_id()
                , topicAndMsg[0], topicAndMsg[1]);

            //clientManager.messageToClient(msg.toJson());
            clientManager.broadcastMessageToStream(msg.toJson());

        } catch (Exception e) {
            clientManager.messageToClient(BusinessLogicAnswers.bad());
        }
    }

    //������ �������� � ������. ���������� �������� ������� � ������
    private void handlerStreamOn() throws IOException{
        if(clientManager.getStreamClients().add(clientManager)){
            clientManager.messageToClient(
                    BusinessLogicAnswers.ok());
        } else {
            clientManager.messageToClient(
                    BusinessLogicAnswers.bad());
        }
        return;
    }

    //������ �������� � ������. �������� �������� ������� �� �������
    private void handlerStreamOff() throws IOException {
        if(clientManager.getStreamClients().remove(clientManager)){
            clientManager.messageToClient(
                BusinessLogicAnswers.ok());
        } else {
            clientManager.messageToClient(
            BusinessLogicAnswers.bad());
        }
        return;
    }

    //��������� ������� ������ �� ������
    private void handlerEndSession() throws IOException {
        UserSession userSession = dataServices.userSessionService.endSession(clientManager.getSID());
        if(userSession != null) {
            clientManager.messageToClient(
                    BusinessLogicAnswers.okSessionEndet());
        } else {
            clientManager.messageToClient(BusinessLogicAnswers.badSession());
        }
    }

    //�������� ������ ��������.
    private void handlerExit() {
        try {
            clientManager.getSocket().close();
            clientManager.broadcastMessageToStream(BusinessLogicAnswers.warnUserExited(
                    clientManager.getSID()
                    , clientManager.getUserName()));
        } catch (IOException e) {

        }
    }

    //����������. ���� �� ������ � ������
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
            clientManager.broadcastMessageToStream(BusinessLogicAnswers.warnUserEntered(
                    clientManager.getSID()
                    , clientManager.getUserName()));
        } else {
            clientManager.messageToClient(BusinessLogicAnswers.badLogin());
        }
    }

    //����������. ���� �� ��� ��������� ������
    private void handlerLoginBySession(String askedSID) throws IOException{
        String SID = dataServices.userSessionService.checkSID(askedSID);
        if(SID != null){
            clientManager.setSID(SID);
            clientManager.messageToClient(SID);
            clientManager.broadcastMessageToStream(BusinessLogicAnswers.warnUserEntered(
                    clientManager.getSID()
                    , clientManager.getUserName()));

        } else {
            clientManager.messageToClient(BusinessLogicAnswers.badSession());
        }
    }

    //������� ��� ����������
    private String[] splitArguments(String argumentsString, int count){
        return argumentsString.split(":",count);
    }

    //������� ��� ���������: ����%%%:%%%���������
    private String[] splitTopicMsg(String argumentsString){
        return argumentsString.split("%%%:%%%",2);
    }

}
