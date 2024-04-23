package DAO;

import java.util.List;

public class MessageRepositoryCurrent implements MessageRepository{
    MessageDAO dao;
    //DAO по умолчанию ArrayList
    public MessageRepositoryCurrent(){
        dao = new MessageDAOArrayList();
    }

    public MessageRepositoryCurrent(MessageDAO dao){
        this.dao = dao;
    }

    @Override
    public Message newMessage(int user_id, String topic, String message) {
        return dao.newMessage(user_id, topic, message);
    }

    @Override
    public void addMessage(Message message) {
        dao.addMessage(message);
    }

    @Override
    public Message newPrivateMessage(int user_id, String topic, String message, int for_user_id) {
        return dao.newPrivateMessage(user_id, topic, message, for_user_id);
    }

    @Override
    public Message getMessage(String message_id) {
        return dao.getMessage(message_id);
    }

    @Override
    public List<Message> getMessages(String[] messages_id) {
        return dao.getMessages(messages_id);
    }

    @Override
    public List<Message> getAllMessages() {
        return dao.getAllMessages();
    }

    @Override
    public List<Message> getAllMessagesFromUser(int user_id) {
        return dao.getAllMessagesFromUser(user_id);
    }

    @Override
    public Message deleteMessage(String message_id) {
        return dao.deleteMessage(message_id);
    }

    @Override
    public List<Message> deleteMessages(String[] messages_id) {
        return dao.deleteMessages(messages_id);
    }

    @Override
    public List<Message> deleteAllMessages() {
        return dao.deleteAllMessages();
    }

    @Override
    public List<Message> deleteAllMessagesFromUser(int user_id) {
        return dao.deleteAllMessagesFromUser(user_id);
    }

    @Override
    public Message editMessage(String message_id, String newTopic, String newMessage) {
        return dao.editMessage(message_id, newTopic, newMessage);
    }

    @Override
    public int size() {
        return dao.size();
    }

    @Override
    public void print() {
        dao.print();
    }

    @Override
    public List<Message> getLast100Messages() {
        return dao.getLast100Messages();
    }

    @Override
    public List<Message> getLastMessagesForm(String messageID) {
        return dao.getLastMessagesForm(messageID);
    }
}
