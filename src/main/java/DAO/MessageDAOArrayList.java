package DAO;

import java.util.ArrayList;
import java.util.List;

public class MessageDAOArrayList implements MessageDAO{
    List<Message> list = new ArrayList<>();
    @Override
    public Message newMessage(int user_id, String topic, String message) {
        Message msg = new Message(user_id, topic, message);
        list.add(msg);
        return msg;
    }

    @Override
    public void addMessage(Message message) {
        list.add(message);
    }

    @Override
    public Message newPrivateMessage(int user_id, String topic, String message, int for_user_id) {
        Message msg = new Message(user_id, topic, message, for_user_id);
        list.add(msg);
        return msg;

    }

    @Override
    public Message getMessage(String message_id) {
        for(Message msg : list){
            if(msg.getMessage_id().equals(message_id)){
                return msg;
            }
        }
        return null;
    }
    @Override
    public List<Message> getMessages(String[] messages_id){
        List<Message> msgs = new ArrayList<>();
        for(Message msg : list){
            String message_id = msg.getMessage_id();
            for(String weAreLookingForMessage_id : messages_id){
                if (message_id.equals(weAreLookingForMessage_id)){
                    msgs.add(msg);
                    break;
                }
            }
        }
        return msgs;
    }

    @Override
    public List<Message> getAllMessages() {
        return list;
    }

    @Override
    public List<Message> getAllMessagesFromUser(int user_id) {
        List<Message> listAllMessagesFromUser = new ArrayList<>();
        for(Message msg : list){
            if(msg.getUser_id() == user_id){
                listAllMessagesFromUser.add(msg);
            }
        }
        return listAllMessagesFromUser;
    }

    @Override
    public Message deleteMessage(String message_id) {
        Message msg = getMessage(message_id);
        if(msg != null) {
            list.remove(msg);
            return msg;
        }
        return null;
    }

    @Override
    public List<Message> deleteMessages(String[] messages_id) {
        List<Message> msgs = getMessages(messages_id);
        for(Message msg : msgs){
            list.remove(msg);
        }
        return msgs;
    }

    @Override
    public List<Message> deleteAllMessages() {
        return new ArrayList<>();
    }

    @Override
    public List<Message> deleteAllMessagesFromUser(int user_id) {
        List<Message> msgs = getAllMessagesFromUser(user_id);
        for(Message msg : msgs){
            list.remove(msg);
        }
        return msgs;
    }

    @Override
    public Message editMessage(String message_id, String newTopic, String newMessage) {
        Message msg = getMessage(message_id);
        if(msg != null) {
            msg.editMessage(newTopic, newMessage);
            return msg;
        }
        return null;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public  void print(){
        for(Message msg : list){
            System.out.println(msg);
        }
    }

    @Override
    public List<Message> getLast100Messages() {
        List<Message> result = new ArrayList<>();
        List<Message> tempResult = new ArrayList<>();
        for(int i = list.size() - 1, j = 0; i >= 0 && j < 100; i--, j++){
            tempResult.add(list.get(i));
        }
        int size = tempResult.size();
        for(int i = 1; i <= size; i++){
            result.add(tempResult.get(size - i));
        }

        return result;
    }

    @Override
    public List<Message> getLastMessagesForm(String messageID) {
        List<Message> result = new ArrayList<>();
        boolean flagIsMessageIDwasGefunden = false;
        for(int i = 0; i < list.size(); i++){
            if(!flagIsMessageIDwasGefunden) {
                if (list.get(i).getMessage_id().equals(messageID)) {
                    flagIsMessageIDwasGefunden = true;
                } else {
                    continue;
                }
            } else {
                result.add(list.get(i));
            }

        }
        return result;
    }

}

